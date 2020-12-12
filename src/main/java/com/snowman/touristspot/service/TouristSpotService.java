package com.snowman.touristspot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public TouristSpot salvar(@Valid TouristSpotRequest touristSpotRequest) {
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
		Optional<TouristSpot> touristSpotOptional = touristSpotRepository.findByName(touristSpot.getName());
		if (touristSpotOptional.isPresent()) {
			throw new TouristicSpotNameAlreadyRegistredException();
		}
		
	}
	
	public  TouristSpotResponse getTouristSpotById(Long id) {
		TouristSpot touristSpot = touristSpotRepository.findById(id).orElseThrow(() -> new BadRequestException("Tourist Spot not found"));
		return TouristSpotMapper.INSTANCE.toTouristSpotResponse(touristSpot);
	}
	
	public Page<TouristSpotResponse> getListPageble(String name, PageRequest pageRequest) {
		Page<TouristSpot> pageTouristSpot = touristSpotRepository.findByNameContainingOrderByNameAsc(name, pageRequest);
		List<TouristSpotResponse> listTouristResponse = TouristSpotMapper.INSTANCE.toListTouristResponse(pageTouristSpot.getContent());
		return new PageImpl<>(listTouristResponse, pageRequest, pageTouristSpot.getTotalElements());
	}

}
