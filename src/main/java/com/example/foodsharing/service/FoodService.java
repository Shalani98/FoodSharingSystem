package com.example.foodsharing.service;

import com.example.foodsharing.entity.Food;
import com.example.foodsharing.entity.User;
import com.example.foodsharing.repository.FoodRepository;
import com.example.foodsharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private UserRepository userRepository;

    public String addFood(Food food) {
        try {
            // 1️⃣ Fetch donor from DB (managed entity)
            Long donorId = food.getDonor().getId();
            User donor = userRepository.findById(donorId)
                    .orElseThrow(() -> new RuntimeException("Donor not found"));

            // 2️⃣ Set managed donor
            food.setDonor(donor);

            // 3️⃣ Save food
            foodRepository.save(food);

            return "Food added successfully";
        } catch (Exception e) {
            e.printStackTrace(); // print exact error in console
            return "Error adding food: " + e.getMessage();
        }
    }

    public List<Food> getFood() {
        return foodRepository.findAll();
    }

    public Food getFoodById(Long id) {
        return foodRepository.findById(id).orElse(null);
    }

    public String claimFood(Long id) {
        Optional<Food> foody=foodRepository.findById(id);
        if(foody.isEmpty()){
            return "Food not found";
        }
        Food food=foody.get();
        if(food.isClaimed()){
            return "Food already claimed";
        }
        food.setClaimed(true);
        foodRepository.save(food);
        return "Food Claimed Successfully";
    }

    public String deleteFood(Long id) {
        if(!foodRepository.existsById(id)){
            return "Food not found";
        }
        foodRepository.deleteById(id);
        return"Food deleted successfully.";
    }

    public List<Food> getFoodsByDonor(Long donorId) {
        Optional<User> donor = userRepository.findById(donorId);

         if(donor.isEmpty()){
             return Collections.emptyList();
         }
        return foodRepository.findByDonor(donor.get());
    }
}
