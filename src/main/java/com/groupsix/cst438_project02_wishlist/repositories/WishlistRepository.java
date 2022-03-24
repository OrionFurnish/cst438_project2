package com.groupsix.cst438_project02_wishlist.repositories;

import com.groupsix.cst438_project02_wishlist.entities.Wishlist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WishlistRepository extends CrudRepository<Wishlist, Integer> {
    List<Wishlist> findByUserId(Integer userId);

}
