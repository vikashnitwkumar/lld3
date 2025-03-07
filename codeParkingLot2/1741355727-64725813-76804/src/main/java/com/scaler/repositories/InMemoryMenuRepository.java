package com.scaler.repositories;

import com.scaler.models.DietaryRequirement;
import com.scaler.models.MenuItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryMenuRepository implements MenuRepository{
    Map<Long, MenuItem> menuItemMap;
    public InMemoryMenuRepository(){
        menuItemMap = new HashMap<>();
    }

    public InMemoryMenuRepository(Map<Long, MenuItem> menuItemMap) {
        this.menuItemMap = menuItemMap;
    }

    @Override
    public MenuItem add(MenuItem menuItem) {
        if (menuItem.getId() == 0) menuItem.setId(menuItemMap.size()+1);
        menuItemMap.put(menuItem.getId(), menuItem);
        return menuItem;
    }

    @Override
    public List<MenuItem> getAll() {
        return menuItemMap.values().stream().toList();
    }

    @Override
    public List<MenuItem> getByDietaryRequirement(DietaryRequirement dietaryRequirement) {
        return menuItemMap.
                values().
                stream().
                filter(menuItem ->
                        menuItem.getDietaryRequirement().equals(dietaryRequirement)
                ).toList();
    }

    @Override
    public MenuItem save(MenuItem menuItem) {
        return add(menuItem);
    }
}
