package com.snowman.touristspot.mapper;

import com.snowman.touristspot.dto.response.PictureTouristSpotResponse;
import com.snowman.touristspot.model.PictureTouristSpot;
import com.snowman.touristspot.model.TouristSpot;
import com.snowman.touristspot.model.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-10T02:24:13-0300",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 3.21.0.v20200304-1404, environment: Java 1.8.0_201 (Oracle Corporation)"
)
public class PictureTouristSpotMapperImpl implements PictureTouristSpotMapper {

    @Override
    public PictureTouristSpotResponse toPictureTouristResponse(PictureTouristSpot pictureTouristSpot) {
        if ( pictureTouristSpot == null ) {
            return null;
        }

        PictureTouristSpotResponse pictureTouristSpotResponse = new PictureTouristSpotResponse();

        pictureTouristSpotResponse.setCreatedBy( pictureTouristSpotCreatedByName( pictureTouristSpot ) );
        pictureTouristSpotResponse.setTouristSpotId( pictureTouristSpotTouristSpotId( pictureTouristSpot ) );
        pictureTouristSpotResponse.setId( pictureTouristSpot.getId() );
        byte[] picture = pictureTouristSpot.getPicture();
        if ( picture != null ) {
            pictureTouristSpotResponse.setPicture( Arrays.copyOf( picture, picture.length ) );
        }

        return pictureTouristSpotResponse;
    }

    @Override
    public List<PictureTouristSpotResponse> toListPictureTouristResponse(List<PictureTouristSpot> pictureTouristSpot) {
        if ( pictureTouristSpot == null ) {
            return null;
        }

        List<PictureTouristSpotResponse> list = new ArrayList<PictureTouristSpotResponse>( pictureTouristSpot.size() );
        for ( PictureTouristSpot pictureTouristSpot1 : pictureTouristSpot ) {
            list.add( toPictureTouristResponse( pictureTouristSpot1 ) );
        }

        return list;
    }

    private String pictureTouristSpotCreatedByName(PictureTouristSpot pictureTouristSpot) {
        if ( pictureTouristSpot == null ) {
            return null;
        }
        User createdBy = pictureTouristSpot.getCreatedBy();
        if ( createdBy == null ) {
            return null;
        }
        String name = createdBy.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Long pictureTouristSpotTouristSpotId(PictureTouristSpot pictureTouristSpot) {
        if ( pictureTouristSpot == null ) {
            return null;
        }
        TouristSpot touristSpot = pictureTouristSpot.getTouristSpot();
        if ( touristSpot == null ) {
            return null;
        }
        Long id = touristSpot.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
