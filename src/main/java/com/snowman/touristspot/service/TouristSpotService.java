package com.snowman.touristspot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.snowman.touristspot.dto.request.TouristSpotRequest;
import com.snowman.touristspot.dto.response.TouristSpotResponse;
import com.snowman.touristspot.exception.BadRequestException;
import com.snowman.touristspot.exception.TouristicSpotNameAlreadyRegistredException;
import com.snowman.touristspot.mapper.TouristSpotMapper;
import com.snowman.touristspot.model.Category;
import com.snowman.touristspot.model.PictureTouristSpot;
import com.snowman.touristspot.model.TouristSpot;
import com.snowman.touristspot.repository.CategoryRepository;
import com.snowman.touristspot.repository.TouristSpotRepository;

@Service
@Transactional
public class TouristSpotService {
	
	@Autowired
	private TouristSpotRepository touristSpotRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public TouristSpot save(TouristSpotRequest touristSpotRequest) {
		TouristSpot touristSpot = new TouristSpot();
		touristSpot.setLatitude(touristSpotRequest.getLatitude());
		touristSpot.setLongitude(touristSpotRequest.getLongitude());
		touristSpot.setName(touristSpotRequest.getName());
		List<PictureTouristSpot> pictureList = new ArrayList<>();
		pictureList.add(new PictureTouristSpot(touristSpotRequest.getPictureBase64().getBytes()));
		touristSpot.setPictureTouristSpot(pictureList);
		Category category = categoryRepository.findById(touristSpotRequest.getCategoryId()).orElseThrow(() -> new BadRequestException("Category not found"));
		touristSpot.setCategory(category);
		
		validateTouristSpotName(touristSpot);
		return touristSpotRepository.save(touristSpot);
	}

	private void validateTouristSpotName(TouristSpot touristSpot) {
		Optional<TouristSpot> touristSpotOptional = touristSpotRepository.findByNameIgnoreCase(touristSpot.getName());
		if (touristSpotOptional.isPresent()) {
			throw new TouristicSpotNameAlreadyRegistredException();
		}
		
	}
	
	public  TouristSpotResponse getTouristSpotById(Long id) {
		TouristSpot touristSpot = touristSpotRepository.findById(id).orElseThrow(() -> new BadRequestException("Tourist Spot not found"));
		return TouristSpotMapper.INSTANCE.toTouristSpotResponse(touristSpot);
	}
	
	public Page<TouristSpotResponse> getListPageble(String name, PageRequest pageRequest) {
		Page<TouristSpot> pageTouristSpot;
		if(StringUtils.isBlank(name)) {
			pageTouristSpot = touristSpotRepository.findAll(pageRequest);
		}else {
			pageTouristSpot = touristSpotRepository.findByNameContainingIgnoreCaseOrderByNameAsc(name, pageRequest);
		}
		List<TouristSpotResponse> listTouristResponse = TouristSpotMapper.INSTANCE.toListTouristResponse(pageTouristSpot.getContent());
		return new PageImpl<>(listTouristResponse, pageRequest, pageTouristSpot.getTotalElements());
	}
	

	private double distance(double lat1, double lat2, double lon1, double lon2) {

		// The math module contains a function 
		// named toRadians which converts from 
		// degrees to radians. 
		lon1 = Math.toRadians(lon1);
		lon2 = Math.toRadians(lon2);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		// Haversine formula  
		double dlon = lon2 - lon1;
		double dlat = lat2 - lat1;
		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);

		double c = 2 * Math.asin(Math.sqrt(a));

		// Radius of earth in kilometers. Use 3956  
		// for miles 
		double r = 6371;

		// calculate the result 
		return (c * r);
	}

}
