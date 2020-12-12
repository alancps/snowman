package com.snowman.touristspot.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.snowman.touristspot.dto.response.PictureTouristSpotResponse;
import com.snowman.touristspot.model.PictureTouristSpot;

@Mapper
public interface PictureTouristSpotMapper {
	PictureTouristSpotMapper INSTANCE = Mappers.getMapper(PictureTouristSpotMapper.class);
	
	@Mappings({
	      @Mapping(source="pictureTouristSpot.createdBy.name", target="createdBy" ),
	      @Mapping(source="pictureTouristSpot.touristSpot.id", target="touristSpotId" ),
	    })
	PictureTouristSpotResponse toPictureTouristResponse(PictureTouristSpot pictureTouristSpot);
	
	@Mappings({
	      @Mapping(source="pictureTouristSpot.createdBy.name", target="createdBy" ),
	      @Mapping(source="pictureTouristSpot.touristSpot.id", target="touristSpotId" ),
	    })
	List<PictureTouristSpotResponse> toListPictureTouristResponse(List<PictureTouristSpot> pictureTouristSpot);
}
