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

//    // TODO: Create db table for wishlist attribute: itemsPriority
//    //       key = itemId, value = priority. Used to store all our items in current list.
//    //       potential issues.... items may have same priority. Maybe change this.
//    // https://www.baeldung.com/java-jpa-lazy-collections
//    @ElementCollection
//    @MapKeyColumn(name = "itemId")
//    private Map<Integer, Integer> items;

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

//    public Map<Integer, Integer> getItems() {
//        return items;
//    }

//    public void setItems(Map<Integer, Integer> items) {
//        this.items = items;
//    }
}
