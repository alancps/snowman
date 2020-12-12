package com.snowman.touristspot.dto.request;


import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PictureTouristSpotRequest {
	
	@NotNull
	private String pictureBase64;
	
	@NotNull
	private Long touristSpotId;
}
