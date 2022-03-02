package com.groupsix.cst438_project02_wishlist.repositories;


import com.groupsix.cst438_project02_wishlist.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *  Used to access the User from the database.
 */

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT * FROM User u WHERE u.name like %name%",
    countQuery = "Select count(*) from User", nativeQuery = true)

    User findUserByName(
            @Param("name") String name
    );

    @Query(value = "SELECT * FROM User u WHERE u.userId like %userId%",
    countQuery = "Select count(*) from User", nativeQuery = true)

    User findUserById(
            @Param("userId") int userId
    );
}
