package com.snowman.touristspot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snowman.touristspot.dto.response.TouristSpotResponse;
import com.snowman.touristspot.exception.BadRequestException;
import com.snowman.touristspot.mapper.TouristSpotMapper;
import com.snowman.touristspot.model.FavoriteTouristSpot;
import com.snowman.touristspot.model.FavoriteTouristSpotId;
import com.snowman.touristspot.model.TouristSpot;
import com.snowman.touristspot.repository.FavoriteTouristSpotRepository;
import com.snowman.touristspot.token.TokenProvider;
import com.snowman.touristspot.util.JWTUtils;

@Service
public class FavoriteTouristSpotService {
	
	@Autowired
	private FavoriteTouristSpotRepository favoriteTouristSpotRepository;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	public FavoriteTouristSpot save(Long touristSpotId, HttpServletRequest request) {
		Long userIdFromToken = getUserIdFromRequestToken(request);
		FavoriteTouristSpotId id = new FavoriteTouristSpotId(userIdFromToken, touristSpotId);
		favoriteTouristSpotRepository.findById(id).orElseThrow(()->new BadRequestException("Favorite Duplicated"));
		FavoriteTouristSpot favoriteTouristSpot = new FavoriteTouristSpot();
		favoriteTouristSpot.setId(id);
		return favoriteTouristSpotRepository.save(favoriteTouristSpot);
	}

	public void delete(Long touristSpotId, HttpServletRequest request) {
		Long userIdFromToken = getUserIdFromRequestToken(request);
		FavoriteTouristSpotId id = new FavoriteTouristSpotId(userIdFromToken, touristSpotId);
		favoriteTouristSpotRepository.deleteById(id);
	}

	private Long getUserIdFromRequestToken(HttpServletRequest request) {
		String jwtFromRequest = JWTUtils.getJwtFromRequest(request);
		Long userIdFromToken = tokenProvider.getUserIdFromToken(jwtFromRequest);
		return userIdFromToken;
	}
	
	@Transactional(readOnly = true)
	public List<TouristSpotResponse> search(HttpServletRequest request) {
		Long userIdFromToken = getUserIdFromRequestToken(request);
		List<FavoriteTouristSpot> favoriteTouristSpotList = favoriteTouristSpotRepository.findByIdUserId(userIdFromToken);favoriteTouristSpotList.get(0);
		if(favoriteTouristSpotList!=null && !favoriteTouristSpotList.isEmpty()) {
			List<TouristSpot> touristSpotList = favoriteTouristSpotList.stream().map(FavoriteTouristSpot::getTouristSpot).collect(Collectors.toList());
			return TouristSpotMapper.INSTANCE.toListTouristResponse(touristSpotList);
		}else {
			return TouristSpotMapper.INSTANCE.toListTouristResponse(new ArrayList<TouristSpot>());
		}
	}
	
}
