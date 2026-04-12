package com.example.foodsharing.repository;

import com.example.foodsharing.entity.Food;
import com.example.foodsharing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long> {


  List<Food> findAll();




  List<Food> findByDonor(User donor);
}