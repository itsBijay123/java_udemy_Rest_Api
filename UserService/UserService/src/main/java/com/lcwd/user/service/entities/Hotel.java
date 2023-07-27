package com.lcwd.user.service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private String id;
    private String name;
    private String location;
    private String about;

}
