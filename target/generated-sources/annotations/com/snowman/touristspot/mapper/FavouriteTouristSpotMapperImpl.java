package com.snowman.touristspot.mapper;

import com.snowman.touristspot.dto.response.FavouriteTouristResponse;
import com.snowman.touristspot.model.FavoriteTouristSpot;
import com.snowman.touristspot.model.FavoriteTouristSpotId;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-09T22:26:36-0300",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 3.21.0.v20200304-1404, environment: Java 1.8.0_201 (Oracle Corporation)"
)
public class FavouriteTouristSpotMapperImpl implements FavouriteTouristSpotMapper {

    @Override
    public FavouriteTouristResponse toFavouriteTouristResponse(FavoriteTouristSpot favoriteTouristSpot) {
        if ( favoriteTouristSpot == null ) {
            return null;
        }

        FavouriteTouristResponse favouriteTouristResponse = new FavouriteTouristResponse();

        favouriteTouristResponse.setUserId( favoriteTouristSpotIdUserId( favoriteTouristSpot ) );
        favouriteTouristResponse.setTouristSpotId( favoriteTouristSpotIdTouristSpotId( favoriteTouristSpot ) );

        return favouriteTouristResponse;
    }

    private Long favoriteTouristSpotIdUserId(FavoriteTouristSpot favoriteTouristSpot) {
        if ( favoriteTouristSpot == null ) {
            return null;
        }
        FavoriteTouristSpotId id = favoriteTouristSpot.getId();
        if ( id == null ) {
            return null;
        }
        Long userId = id.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private Long favoriteTouristSpotIdTouristSpotId(FavoriteTouristSpot favoriteTouristSpot) {
        if ( favoriteTouristSpot == null ) {
            return null;
        }
        FavoriteTouristSpotId id = favoriteTouristSpot.getId();
        if ( id == null ) {
            return null;
        }
        Long touristSpotId = id.getTouristSpotId();
        if ( touristSpotId == null ) {
            return null;
        }
        return touristSpotId;
    }
}
