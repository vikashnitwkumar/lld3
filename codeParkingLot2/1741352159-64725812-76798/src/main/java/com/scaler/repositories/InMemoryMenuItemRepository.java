package com.scaler.repositories;

import com.scaler.models.MenuItem;
import com.scaler.models.MenuItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryMenuItemRepository implements MenuItemRepository{
    Map<Long, MenuItem> menuItemMap;

    public InMemoryMenuItemRepository(Map<Long, MenuItem> menuItemMap) {
        this.menuItemMap = menuItemMap;
    }

    public InMemoryMenuItemRepository() {
        menuItemMap = new HashMap<>();
    }
   
    @Override
    public MenuItem add(MenuItem menuItem) {
        if (menuItem.getId() == 0) menuItem.setId(menuItemMap.size()+1);
        menuItemMap.put(menuItem.getId(), menuItem);
        return menuItem;
    }

    @Override
    public Optional<MenuItem> findById(long id) {
        return Optional.ofNullable(menuItemMap.get(id));
    }
}
