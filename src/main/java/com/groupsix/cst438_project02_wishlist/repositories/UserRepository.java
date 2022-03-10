package com.groupsix.cst438_project02_wishlist.repositories;


import com.groupsix.cst438_project02_wishlist.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *  Used to access the User from the database.
 */

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query(value = "SELECT * FROM User u WHERE u.user_id like %:user_id%",
    countQuery = "Select count(*) from User", nativeQuery = true)

    User findUserById(@Param("user_id") Integer user_id);

    User findUserByUsername(String username);
}
