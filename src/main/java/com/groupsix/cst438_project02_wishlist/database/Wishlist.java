package com.groupsix.cst438_project02_wishlist.database;

import javax.persistence.*;
import java.util.Map;

@Entity
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int wishlistId;
    private int userId;
    private String listName;

    // TODO: Create db table for wishlist attribute: itemsPriority
    //       key = itemId, value = priority. Used to store all our items in current list.
    //       potential issues.... items may have same priority. Maybe change this.
    // https://www.baeldung.com/java-jpa-lazy-collections
    @ElementCollection
    @MapKeyColumn(name = "itemId")
    private Map<Integer, Integer> items;

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Integer, Integer> items) {
        this.items = items;
    }
}
