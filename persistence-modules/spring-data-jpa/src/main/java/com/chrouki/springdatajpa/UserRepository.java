package com.chrouki.springdatajpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tarik Chrouki
 * Date: 13/04/2020
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("FROM User u WHERE u.firstName = ?1")
    List<User> findUserByFirstName(String firstName);

    @Query(value = "SELECT * FROM users u WHERE u.last_name = ?1", nativeQuery = true)
    List<User> findUserByLastName(String lastName);

    @Query("FROM User u WHERE u.age = ?1 ORDER BY u.lastName ASC")
    List<User> findUserByAgeOrderByLastNameAsc(Integer age);

    @Query("FROM User u WHERE u.age = ?1")
    List<User> findUserByAgeWithSort(Integer age, Sort sort);

    @Query(value = "SELECT u FROM User u ORDER BY u.id")
    Page<User> findAllUsersWithPagination(Pageable pageable);

    @Modifying
    @Query("update User u set u.firstName = ?1 where u.lastName = ?2")
    int setFixedFirstNameForLastName(String firstname, String lastname);
}
