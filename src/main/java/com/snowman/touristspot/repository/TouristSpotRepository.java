package com.snowman.touristspot.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.snowman.touristspot.model.TouristSpot;

public interface TouristSpotRepository extends JpaRepository<TouristSpot, Long> {

	public Page<TouristSpot> findByNameContainingOrderByNameAsc(String name, Pageable pageable);
	public Optional<TouristSpot> findByName(String name);
	
}
