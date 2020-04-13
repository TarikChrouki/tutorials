package com.chrouki.springdatajpa;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Tarik Chrouki
 * Date: 13/04/2020
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor(force = true)
public class User {
    private @Id
    @GeneratedValue
    Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private Integer age;

}