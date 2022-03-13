package com.groupsix.cst438_project02_wishlist.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer wishlistId;
    private Integer userId;
    private String listName;
    private Item items;

    public Integer getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Integer wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getItems() {
        return items.getItemName();
    }
}
