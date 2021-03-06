package com.cognizant.truYum.dao;

import com.cognizant.truYum.model.MenuItem;

import java.util.List;

public interface MenuItemDao {
    List<MenuItem> getMenuItemListAdmin();
    List<MenuItem> getMenuItemListCustomer();
    void modifyMenuItem(MenuItem menuItem);
    MenuItem getMenuItem(long menuItemId);
}
