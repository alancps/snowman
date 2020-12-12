package com.snowman.touristspot.dto.response;

import java.math.BigDecimal;
import java.util.List;

import com.snowman.touristspot.model.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TouristSpotResponse {
	private Long id;
	
	private String name;
	
	private BigDecimal longitude;
	
	private BigDecimal latitude;
	
	private Category category;
	
	//retirar se a quantidade de imagens para um ponto turístico for grande.
	private List<PictureTouristSpotResponse> pictureTouristSpotResponse;
	
}
