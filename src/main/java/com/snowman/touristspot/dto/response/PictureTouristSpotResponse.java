package com.snowman.touristspot.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "picture" })
public class PictureTouristSpotResponse {
	private Long id;
	
	private byte[] picture;
	
	private Long touristSpotId;
	
	private String createdBy;
	
	public String getPictureBase64() {
		return new String(this.picture);
	}
}
