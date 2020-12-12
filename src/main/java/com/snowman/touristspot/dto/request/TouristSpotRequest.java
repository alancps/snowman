package com.snowman.touristspot.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TouristSpotRequest {
	
	@NotNull
	private String name;
	
	@NotNull
	private BigDecimal longitude;
	
	@NotNull
	private BigDecimal latitude;
	
	@NotNull
	private Long categoryId;
	
	@NotNull
	private String pictureBase64;
	
}
