package com.snowman.touristspot.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.snowman.touristspot.dto.request.PictureTouristSpotRequest;
import com.snowman.touristspot.dto.response.PictureTouristSpotResponse;
import com.snowman.touristspot.exception.BadRequestException;
import com.snowman.touristspot.exception.DeletePictureNotAllowedException;
import com.snowman.touristspot.mapper.PictureTouristSpotMapper;
import com.snowman.touristspot.model.PictureTouristSpot;
import com.snowman.touristspot.model.TouristSpot;
import com.snowman.touristspot.model.User;
import com.snowman.touristspot.repository.PictureTouristSpotRepository;
import com.snowman.touristspot.repository.TouristSpotRepository;
import com.snowman.touristspot.repository.UserRepository;
import com.snowman.touristspot.token.TokenProvider;
import com.snowman.touristspot.util.JWTUtils;

@Service
public class PictureTouristSpotService {
	
	@Autowired
	private PictureTouristSpotRepository pictureTouristSpotRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TouristSpotRepository touristSpotRepository;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	public PictureTouristSpotResponse save(PictureTouristSpotRequest pictureTouristSpotRequest, HttpServletRequest request) {
		Long userIdFromRequestToken = getUserIdFromRequestToken(request);
		User createdBy = userRepository.findById(userIdFromRequestToken).orElseThrow(() -> new BadRequestException("User Not Found"));
		TouristSpot touristSpot = touristSpotRepository.findById(pictureTouristSpotRequest.getTouristSpotId()).orElseThrow(() -> new BadRequestException("Tourist Spot Not Found"));
		PictureTouristSpot pictureTouristSpot = new PictureTouristSpot();
		pictureTouristSpot.setPicture(pictureTouristSpotRequest.getPictureBase64().getBytes());
		pictureTouristSpot.setTouristSpot(touristSpot);
		pictureTouristSpot.setCreatedBy(createdBy);
		PictureTouristSpot pictureTouristSpotSaved = pictureTouristSpotRepository.save(pictureTouristSpot);
		PictureTouristSpotResponse pictureTouristResponse = PictureTouristSpotMapper.INSTANCE.toPictureTouristResponse(pictureTouristSpotSaved);
		return pictureTouristResponse;
	}

	public void delete(Long pictureTouristSpotId, HttpServletRequest request) {
		Long userIdFromRequestToken = getUserIdFromRequestToken(request);
		User user = userRepository.findById(userIdFromRequestToken).orElseThrow(() -> new BadRequestException("User Not Found"));
		validateDeletePictureTouristSpot(pictureTouristSpotId, user);
		pictureTouristSpotRepository.deleteById(pictureTouristSpotId);
	}
	
	private void validateDeletePictureTouristSpot(Long pictureTouristSpotId, User userRequested) {
		Optional<PictureTouristSpot> pictureTouristSpot = pictureTouristSpotRepository.findById(pictureTouristSpotId);
		if (!pictureTouristSpot.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		if(!userRequested.getId().equals(pictureTouristSpot.get().getCreatedBy().getId())) {
			throw new DeletePictureNotAllowedException();
		}
		
	}
	
	private Long getUserIdFromRequestToken(HttpServletRequest request) {
		String jwtFromRequest = JWTUtils.getJwtFromRequest(request);
		Long userIdFromToken = tokenProvider.getUserIdFromToken(jwtFromRequest);
		return userIdFromToken;
	}
}
