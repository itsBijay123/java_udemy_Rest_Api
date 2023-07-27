package com.lcwd.user.service.services.Impl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserRepository userRepository;

   private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        //generate unique userId


        String randomUserId= UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public User getUser(String userId) {

        //get user from database with the help of user repository
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with given id is not found on server !!:" + userId)
        );

        //http://localhost:8083/ratings/users/73999428-2139-4300-b773-7beaca44c6dfww
        //fetch rating of the above user from Rating Service
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);

        logger.info("{}", ratingsOfUser);

        List<Rating>ratings= Arrays.stream(ratingsOfUser).collect(Collectors.toList());


        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call hotel Service to get the hotel
         //http://localhost:8082/hotels/239fb350-ada8-4d57-ade3-2e228c807df8
            System.out.println(rating.getHotelId());
          //  ResponseEntity<Hotel>forEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(),Hotel.class);
            Hotel hotel=hotelService.getHotel(rating.getHotelId());
           // logger.info( "response status code:{}",forEntity.getStatusCode());


            //set the hotel to rating
            rating.setHotel(hotel);
            //return the rating
            return rating;

        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public List<User> getAllUser() {

      List<User> user= userRepository.findAll();


        return user;
    }
}
