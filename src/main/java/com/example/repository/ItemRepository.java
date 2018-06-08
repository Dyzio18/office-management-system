package com.example.repository;

import com.example.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<Item,Long> {
    Item findById(Integer Id);
}
