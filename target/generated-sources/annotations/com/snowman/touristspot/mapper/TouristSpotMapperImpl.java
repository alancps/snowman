package com.snowman.touristspot.mapper;

import com.snowman.touristspot.dto.response.PictureTouristSpotResponse;
import com.snowman.touristspot.dto.response.TouristSpotResponse;
import com.snowman.touristspot.model.PictureTouristSpot;
import com.snowman.touristspot.model.TouristSpot;
import com.snowman.touristspot.model.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-11T23:56:58-0300",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 3.21.0.v20200304-1404, environment: Java 1.8.0_201 (Oracle Corporation)"
)
public class TouristSpotMapperImpl implements TouristSpotMapper {

    @Override
    public TouristSpotResponse toTouristSpotResponse(TouristSpot touristSpot) {
        if ( touristSpot == null ) {
            return null;
        }

        TouristSpotResponse touristSpotResponse = new TouristSpotResponse();

        touristSpotResponse.setPictureTouristSpotResponse( pictureTouristSpotListToPictureTouristSpotResponseList( touristSpot.getPictureTouristSpot() ) );
        touristSpotResponse.setCategory( touristSpot.getCategory() );
        touristSpotResponse.setId( touristSpot.getId() );
        touristSpotResponse.setLatitude( touristSpot.getLatitude() );
        touristSpotResponse.setLongitude( touristSpot.getLongitude() );
        touristSpotResponse.setName( touristSpot.getName() );

        return touristSpotResponse;
    }

    @Override
    public TouristSpot toTouristSpot(TouristSpotResponse touristSpot) {
        if ( touristSpot == null ) {
            return null;
        }

        TouristSpot touristSpot1 = new TouristSpot();

        touristSpot1.setPictureTouristSpot( pictureTouristSpotResponseListToPictureTouristSpotList( touristSpot.getPictureTouristSpotResponse() ) );
        touristSpot1.setCategory( touristSpot.getCategory() );
        touristSpot1.setId( touristSpot.getId() );
        touristSpot1.setLatitude( touristSpot.getLatitude() );
        touristSpot1.setLongitude( touristSpot.getLongitude() );
        touristSpot1.setName( touristSpot.getName() );

        return touristSpot1;
    }

    @Override
    public List<TouristSpotResponse> toListTouristResponse(List<TouristSpot> touristSpot) {
        if ( touristSpot == null ) {
            return null;
        }

        List<TouristSpotResponse> list = new ArrayList<TouristSpotResponse>( touristSpot.size() );
        for ( TouristSpot touristSpot1 : touristSpot ) {
            list.add( toTouristSpotResponse( touristSpot1 ) );
        }

        return list;
    }

    @Override
    public PictureTouristSpot PictureTouristSpotResponseToPictureTouristSpot(PictureTouristSpotResponse pictureTouristSpotResponse) {
        if ( pictureTouristSpotResponse == null ) {
            return null;
        }

        PictureTouristSpot pictureTouristSpot = new PictureTouristSpot();

        pictureTouristSpot.setCreatedBy( pictureTouristSpotResponseToUser( pictureTouristSpotResponse ) );
        pictureTouristSpot.setId( pictureTouristSpotResponse.getId() );
        byte[] picture = pictureTouristSpotResponse.getPicture();
        if ( picture != null ) {
            pictureTouristSpot.setPicture( Arrays.copyOf( picture, picture.length ) );
        }

        return pictureTouristSpot;
    }

    @Override
    public PictureTouristSpotResponse PictureTouristSpotToPictureTouristSpotResponse(PictureTouristSpot model) {
        if ( model == null ) {
            return null;
        }

        PictureTouristSpotResponse pictureTouristSpotResponse = new PictureTouristSpotResponse();

        pictureTouristSpotResponse.setCreatedBy( modelCreatedByName( model ) );
        pictureTouristSpotResponse.setId( model.getId() );
        byte[] picture = model.getPicture();
        if ( picture != null ) {
            pictureTouristSpotResponse.setPicture( Arrays.copyOf( picture, picture.length ) );
        }

        return pictureTouristSpotResponse;
    }

    protected List<PictureTouristSpotResponse> pictureTouristSpotListToPictureTouristSpotResponseList(List<PictureTouristSpot> list) {
        if ( list == null ) {
            return null;
        }

        List<PictureTouristSpotResponse> list1 = new ArrayList<PictureTouristSpotResponse>( list.size() );
        for ( PictureTouristSpot pictureTouristSpot : list ) {
            list1.add( PictureTouristSpotToPictureTouristSpotResponse( pictureTouristSpot ) );
        }

        return list1;
    }

    protected List<PictureTouristSpot> pictureTouristSpotResponseListToPictureTouristSpotList(List<PictureTouristSpotResponse> list) {
        if ( list == null ) {
            return null;
        }

        List<PictureTouristSpot> list1 = new ArrayList<PictureTouristSpot>( list.size() );
        for ( PictureTouristSpotResponse pictureTouristSpotResponse : list ) {
            list1.add( PictureTouristSpotResponseToPictureTouristSpot( pictureTouristSpotResponse ) );
        }

        return list1;
    }

    protected User pictureTouristSpotResponseToUser(PictureTouristSpotResponse pictureTouristSpotResponse) {
        if ( pictureTouristSpotResponse == null ) {
            return null;
        }

        User user = new User();

        user.setName( pictureTouristSpotResponse.getCreatedBy() );

        return user;
    }

    private String modelCreatedByName(PictureTouristSpot pictureTouristSpot) {
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
}
