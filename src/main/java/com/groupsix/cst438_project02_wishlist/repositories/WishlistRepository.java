package com.groupsix.cst438_project02_wishlist.repositories;


import com.groupsix.cst438_project02_wishlist.entities.Wishlist;
import org.springframework.data.repository.CrudRepository;

public interface WishlistRepository extends CrudRepository<Wishlist, Integer> {
}
