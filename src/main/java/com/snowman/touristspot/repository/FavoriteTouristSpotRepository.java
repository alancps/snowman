package com.snowman.touristspot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snowman.touristspot.model.FavoriteTouristSpot;
import com.snowman.touristspot.model.FavoriteTouristSpotId;

public interface FavoriteTouristSpotRepository extends JpaRepository<FavoriteTouristSpot, FavoriteTouristSpotId>{

	List<FavoriteTouristSpot> findByIdUserId(Long userIdFromToken);
}
