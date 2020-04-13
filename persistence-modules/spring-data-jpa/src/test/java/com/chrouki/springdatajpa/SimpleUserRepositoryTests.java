package com.chrouki.springdatajpa;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

/**
 * @author Tarik Chrouki
 * Date: 13/04/2020
 */
@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
public class SimpleUserRepositoryTests {
    @Autowired
    UserRepository userRepository;
    User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setFirstName("firstname");
        user.setLastName("lastname");
        user.setAge(25);
    }

    @Test
    public void findSavedUserById() {

        user = userRepository.save(user);

        assertThat(userRepository.findById(user.getId())).hasValue(user);
    }

    @Test
    public void findUserByFirstName() {
        user = userRepository.save(user);

        assertThat(userRepository.findUserByFirstName(user.getFirstName())).contains(user);
    }

    @Test
    public void testSorting(){
        User user0 = new User();
        user0.setAge(25);
        user0.setLastName("lastname-0");

        User user1 = new User();
        user1.setAge(25);
        user1.setLastName("lastname-1");

        User user2 = new User();
        user2.setAge(25);
        user2.setLastName("lastname-2");

        // we deliberately save the items in reverse
        userRepository.saveAll(Arrays.asList(user2, user1, user0));

        List<User> result = userRepository.findUserByAgeOrderByLastNameAsc(25);

        MatcherAssert.assertThat(result.size(), is(3));
        MatcherAssert.assertThat(result, hasItems(user0, user1, user2));

        result = userRepository.findUserByAgeWithSort(25, Sort.by(Sort.Direction.DESC, "lastName"));
        MatcherAssert.assertThat(result, hasItems(user2, user1, user1));
    }


    @Test
    public void updateUser(){
        user = userRepository.save(user);
        userRepository.setFixedFirstNameForLastName("Joe", "lastname");
        assertThat(userRepository.findUserByFirstName("Joe")).contains(user);
    }

}

