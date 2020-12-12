package com.snowman.touristspot.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.snowman.touristspot.dto.request.FavoriteTouristSpotRequest;
import com.snowman.touristspot.dto.response.FavouriteTouristResponse;
import com.snowman.touristspot.dto.response.TouristSpotResponse;
import com.snowman.touristspot.event.ResourceCreatedEvent;
import com.snowman.touristspot.mapper.FavouriteTouristSpotMapper;
import com.snowman.touristspot.model.FavoriteTouristSpot;
import com.snowman.touristspot.service.FavoriteTouristSpotService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/favorite")
@Api(value = "Favorite Tourist Spot")
public class FavoriteResource {

	@Autowired
	private FavoriteTouristSpotService favoriteService;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	@ApiOperation(value = "Create new Favorite.")
	public ResponseEntity<FavouriteTouristResponse> create(@Valid @RequestBody FavoriteTouristSpotRequest favoriteTouristSpotRequest,
			HttpServletResponse response, HttpServletRequest request) {
		FavoriteTouristSpot favoriteTouristSpot = favoriteService.save(favoriteTouristSpotRequest.getTouristSpotId(), request);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, favoriteTouristSpot.getId().getUserId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(FavouriteTouristSpotMapper.INSTANCE.toFavouriteTouristResponse(favoriteTouristSpot));
	}
	
	@GetMapping
	@ApiOperation(value = "See a list my favorites tourist spots.")
	public ResponseEntity<List<TouristSpotResponse>> search(HttpServletRequest request) {
		List<TouristSpotResponse> touristSpotListResponse = favoriteService.search(request);
		if(touristSpotListResponse.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(touristSpotListResponse);
		}
		return ResponseEntity.status(HttpStatus.OK).body(touristSpotListResponse);
	}

	@DeleteMapping("/{touristSpotId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Remove Tourist Spot Favorite.")
	public void remove(@PathVariable Long touristSpotId, HttpServletRequest request) {
		favoriteService.delete(touristSpotId, request);
	}
}
