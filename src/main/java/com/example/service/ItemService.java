package com.example.service;


import com.example.model.Item;

import java.util.List;

public interface ItemService {
    public List<Item> getAll();
    public  void saveItem(Item item);
    public Item findById(Integer item_id);
}
