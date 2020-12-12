package com.snowman.touristspot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snowman.touristspot.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findByDescription(String description);

}
