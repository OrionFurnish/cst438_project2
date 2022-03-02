package com.groupsix.cst438_project02_wishlist.repositories;

import com.groupsix.cst438_project02_wishlist.database.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
