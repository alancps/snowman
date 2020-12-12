package com.snowman.touristspot.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.snowman.touristspot.dto.response.PictureTouristSpotResponse;
import com.snowman.touristspot.dto.response.TouristSpotResponse;
import com.snowman.touristspot.model.PictureTouristSpot;
import com.snowman.touristspot.model.TouristSpot;

@Mapper
public interface TouristSpotMapper {
	TouristSpotMapper INSTANCE = Mappers.getMapper(TouristSpotMapper.class);
	
	@Mapping(source = "pictureTouristSpot", target = "pictureTouristSpotResponse")
	TouristSpotResponse toTouristSpotResponse(TouristSpot touristSpot);
	
	@Mapping(source = "pictureTouristSpotResponse", target = "pictureTouristSpot")
	@Mapping(target = "favoriteTouristSpots", ignore = true)
	TouristSpot toTouristSpot(TouristSpotResponse touristSpot);

	List<TouristSpotResponse> toListTouristResponse(List<TouristSpot> touristSpot);

	@Mapping(source = "createdBy", target = "createdBy.name")
	@Mapping(target = "touristSpot", ignore = true)
	PictureTouristSpot PictureTouristSpotResponseToPictureTouristSpot(PictureTouristSpotResponse pictureTouristSpotResponse);

	@InheritInverseConfiguration
	@Mapping(target = "touristSpotId", ignore = true)
	PictureTouristSpotResponse PictureTouristSpotToPictureTouristSpotResponse(PictureTouristSpot model);
}
