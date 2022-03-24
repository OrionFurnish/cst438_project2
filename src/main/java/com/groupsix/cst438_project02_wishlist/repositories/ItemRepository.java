package com.groupsix.cst438_project02_wishlist.repositories;


import com.groupsix.cst438_project02_wishlist.entities.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Integer> {
    List<Item> findItemByWishlistId(Integer wishlistId);

}
