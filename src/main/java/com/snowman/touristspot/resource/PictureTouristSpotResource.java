package com.snowman.touristspot.resource;

import java.util.List;
import java.util.Optional;

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

import com.snowman.touristspot.dto.request.PictureTouristSpotRequest;
import com.snowman.touristspot.dto.response.PictureTouristSpotResponse;
import com.snowman.touristspot.event.ResourceCreatedEvent;
import com.snowman.touristspot.mapper.PictureTouristSpotMapper;
import com.snowman.touristspot.model.PictureTouristSpot;
import com.snowman.touristspot.repository.PictureTouristSpotRepository;
import com.snowman.touristspot.service.PictureTouristSpotService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/picture")
@Api(value = "Picture Tourist Spot")
public class PictureTouristSpotResource {

	@Autowired
	private PictureTouristSpotRepository pictureTouristSpotRepository;
	
	@Autowired 
	private PictureTouristSpotService pictureTouristSpotService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	@ApiOperation(value = "Add new picture.")
	public ResponseEntity<PictureTouristSpotResponse> create(@Valid @RequestBody PictureTouristSpotRequest pictureTouristSpotRequest, HttpServletRequest request, HttpServletResponse response) {
		PictureTouristSpotResponse pictureTouristResponse = pictureTouristSpotService.save(pictureTouristSpotRequest, request);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, pictureTouristResponse.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pictureTouristResponse);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Get Picture by Id.")
	public ResponseEntity<PictureTouristSpotResponse> findById(@PathVariable Long id) {
		Optional<PictureTouristSpot> pictureTouristSpot = pictureTouristSpotRepository.findById(id);
		if (pictureTouristSpot.isPresent()) {
			return ResponseEntity.ok(PictureTouristSpotMapper.INSTANCE.toPictureTouristResponse(pictureTouristSpot.get()));
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/touristspot/{id}")
	@ApiOperation(value = "Get Pictures from a tourist spot.")
	public ResponseEntity<List<PictureTouristSpotResponse>> findByTouristicSpot(@PathVariable Long id) {
		List<PictureTouristSpot> pictureTouristSpot = pictureTouristSpotRepository.findByTouristSpotId(id);
		if(pictureTouristSpot.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		}
		return ResponseEntity.ok(PictureTouristSpotMapper.INSTANCE.toListPictureTouristResponse(pictureTouristSpot));
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Remove pictures I added to a tourist spot.")
	public void remove(@PathVariable Long id, HttpServletRequest request) {
		pictureTouristSpotService.delete(id, request);
	}
}
