package com.snowman.touristspot.resource;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snowman.touristspot.event.ResourceCreatedEvent;
import com.snowman.touristspot.model.Category;
import com.snowman.touristspot.repository.CategoryRepository;
import com.snowman.touristspot.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/category")
@Api(value = "Categories")
public class CategoryResource {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired 
	private CategoryService categoryService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	@ApiOperation(value = "Create new categories.")
	public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response) {
		Category categorySaved = categoryService.save(category);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, categorySaved.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categorySaved);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Get Category by Id.")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			return ResponseEntity.ok(category.get());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
