package com.snowman.touristspot.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snowman.touristspot.dto.request.TouristSpotRequest;
import com.snowman.touristspot.dto.response.TouristSpotResponse;
import com.snowman.touristspot.event.ResourceCreatedEvent;
import com.snowman.touristspot.mapper.TouristSpotMapper;
import com.snowman.touristspot.model.TouristSpot;
import com.snowman.touristspot.service.TouristSpotService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/touristspot")
@Api(value = "Tourist Spot")
public class TouristSpotResource {

	@Autowired
	private TouristSpotService touristSpotService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	@ApiOperation(value = "Register a tourist spot.")
	public ResponseEntity<TouristSpotResponse> create(@Valid @RequestBody TouristSpotRequest touristSpotRequest, HttpServletResponse response) {
		TouristSpot touristSpotSaved = touristSpotService.save(touristSpotRequest);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, touristSpotSaved.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(TouristSpotMapper.INSTANCE.toTouristSpotResponse(touristSpotSaved));
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Get Tourist Spot by Id.")
	public ResponseEntity<TouristSpotResponse> findById(@PathVariable Long id) {
		TouristSpotResponse touristSpotResponse = touristSpotService.getTouristSpotById(id);
		return ResponseEntity.ok(touristSpotResponse);
	}

	@GetMapping
	@ApiOperation(value = "See a list of tourist spots.")
	public ResponseEntity<Page<TouristSpotResponse>> search(@RequestParam(required = false) String name, 
			@RequestParam("pageNumber") int pageNumber, 
            @RequestParam("pageSize") int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("name").descending());
		Page<TouristSpotResponse> listPageble = touristSpotService.getListPageble(name, pageRequest);
		
		if(listPageble.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(listPageble);
	}

}
