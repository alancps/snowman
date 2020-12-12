package com.snowman.touristspot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteTouristResponse {
	private Long userId;
	
	private Long touristSpotId;
}
