package com.snowman.touristspot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snowman.touristspot.model.PictureTouristSpot;

public interface PictureTouristSpotRepository extends JpaRepository<PictureTouristSpot, Long> {

	List<PictureTouristSpot> findByTouristSpotId(Long id);

}
