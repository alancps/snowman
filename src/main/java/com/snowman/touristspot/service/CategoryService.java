package com.snowman.touristspot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snowman.touristspot.exception.CategoryDescriptionAlreadyRegistredException;
import com.snowman.touristspot.model.Category;
import com.snowman.touristspot.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category save(Category category) {
		validateCategoryDescription(category);
		return categoryRepository.save(category);
	}

	private void validateCategoryDescription(Category category) {
		Optional<Category> categoryOptional = categoryRepository.findByDescription(category.getDescription());
		if (categoryOptional.isPresent()) {
			throw new CategoryDescriptionAlreadyRegistredException();
		}
	}

}
