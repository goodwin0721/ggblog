package com.goodwin.ggblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author goodwin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String telephone;
    private String creationDate;
    private String birthdate;
    private int gender;
    private String signature;
    private int fans;
    private int follow;
    private int dynamic;
    private String avatar;
}
