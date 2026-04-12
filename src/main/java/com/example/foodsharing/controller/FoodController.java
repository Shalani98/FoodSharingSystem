package com.example.foodsharing.controller;

import com.example.foodsharing.dto.foodDTO;
import com.example.foodsharing.entity.Food;
import com.example.foodsharing.entity.User;
import com.example.foodsharing.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    // ✅ Add Food with Image Upload
    @PostMapping("/addFood")
    public String addFood(
            @RequestPart("food") foodDTO foodDTO,
            @RequestPart(value = "image", required = false) MultipartFile image) {

        try {
            String imageUrl = null;

            // 🔥 HANDLE IMAGE UPLOAD
            if (image != null && !image.isEmpty()) {

                // Clean filename
                String originalName = image.getOriginalFilename();
                String cleanName = originalName.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");

                String fileName = System.currentTimeMillis() + "_" + cleanName;

                // Project root uploads folder
                String uploadDir = System.getProperty("user.dir") + "/uploads/";
                File dir = new File(uploadDir);

                // Create folder if not exists
                if (!dir.exists()) {
                    dir.mkdirs();
                    System.out.println("✅ Upload folder created: " + dir.getAbsolutePath());
                }

                // Save file
                File destination = new File(uploadDir + fileName);
                image.transferTo(destination);

                System.out.println("✅ Image saved at: " + destination.getAbsolutePath());

                // Set URL (important)
                imageUrl = "/uploads/" + fileName;
            }

            // 🔹 Convert DTO → Entity
            Food food = new Food();
            food.setTitle(foodDTO.getTitle());
            food.setDescription(foodDTO.getDescription());
            food.setQuantity(foodDTO.getQuantity());
            food.setLocation(foodDTO.getLocation());
            food.setLocation_lat(foodDTO.getLocation_lat());
            food.setLocation_lng(food.getLocation_lng());
            food.setAvailableFrom(LocalDateTime.parse(foodDTO.getAvailableFrom()));
            food.setAvailableUntil(LocalDateTime.parse(foodDTO.getAvailableUntil()));
            food.setImageUrl(imageUrl);

            // 🔹 Set donor
            User donor = new User();
            donor.setId(foodDTO.getDonorId());
            food.setDonor(donor);

            // 🔹 Save food
            return foodService.addFood(food);

        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Error: " + e.getMessage();
        }
    }
    // Get all foods
    @GetMapping("/getFood")
    public List<Food> getFood() {
        return foodService.getFood();
    }

    // Get food by ID
    @GetMapping("/getFood/{id}")
    public Food getFoodById(@PathVariable Long id) {
        return foodService.getFoodById(id);
    }

    // Claim a food
    @PutMapping("/claim/{id}")
    public String claimFood(@PathVariable Long id) {
        return foodService.claimFood(id);
    }

    // Delete a food
    @DeleteMapping("/delete/{id}")
    public String deleteFood(@PathVariable Long id) {
        return foodService.deleteFood(id);
    }

    // Get foods by donor
    @GetMapping("/donor/{donorId}")
    public List<Food> getFoodsByDonor(@PathVariable Long donorId) {
        return foodService.getFoodsByDonor(donorId);
    }
}