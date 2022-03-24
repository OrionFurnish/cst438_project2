package com.groupsix.cst438_project02_wishlist.repositories;


import com.groupsix.cst438_project02_wishlist.entities.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {
    @Query (value = "SELECT * FROM Item i WHERE i.wishlistId like %:wishlistId%",
    countQuery = "Select count(*) from Item", nativeQuery = true)

    List<Item> findItemById(
            @Param("wishlistId") Integer wishlistId
    );

}
