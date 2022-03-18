package com.groupsix.cst438_project02_wishlist.repositories;


import com.groupsix.cst438_project02_wishlist.entities.User;
import com.groupsix.cst438_project02_wishlist.entities.Wishlist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface WishlistRepository extends CrudRepository<Wishlist, Integer> {
    @Query(value = "SELECT * FROM User u WHERE w.user_id like %:user_id%",
            countQuery = "Select count(*) from User", nativeQuery = true)

    Wishlist findWishlistById(@Param("user_id") Integer user_id);



}
