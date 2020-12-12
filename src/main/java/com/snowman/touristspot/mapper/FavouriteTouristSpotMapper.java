package com.snowman.touristspot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.snowman.touristspot.dto.response.FavouriteTouristResponse;
import com.snowman.touristspot.model.FavoriteTouristSpot;

@Mapper
public interface FavouriteTouristSpotMapper {
	FavouriteTouristSpotMapper INSTANCE = Mappers.getMapper(FavouriteTouristSpotMapper.class);
	
	@Mappings({
	      @Mapping(source="favoriteTouristSpot.id.userId", target="userId" ),
	      @Mapping(source="favoriteTouristSpot.id.touristSpotId", target="touristSpotId")
	    })
	FavouriteTouristResponse toFavouriteTouristResponse(FavoriteTouristSpot favoriteTouristSpot);
}
