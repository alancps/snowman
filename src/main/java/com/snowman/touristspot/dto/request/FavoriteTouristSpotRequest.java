package com.snowman.touristspot.dto.request;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteTouristSpotRequest {
	@NotNull
    private Long touristSpotId;
}
