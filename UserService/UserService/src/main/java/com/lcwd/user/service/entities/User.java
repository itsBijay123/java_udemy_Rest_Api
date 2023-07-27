package com.lcwd.user.service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "micro_users")
public class User {
    @Id
   @Column(name = "ID")
    private String userId;

    @Column(name = "NAME",length = 20,nullable = false)
    private String name;

    @Column(name="EMAIL",length = 128,nullable = false)
    private String email;

    @Column(name = "ABOUT",length = 256,nullable = false)
    private String about;

    @Transient  //its does not store in database
    private List<Rating> ratings=new ArrayList<>();

}
