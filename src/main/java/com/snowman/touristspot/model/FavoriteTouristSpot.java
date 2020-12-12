package com.snowman.touristspot.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "favorite_tourist_spot")
@Getter @Setter
@NoArgsConstructor
public class FavoriteTouristSpot {
	
	@EmbeddedId
	FavoriteTouristSpotId id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", insertable=false, updatable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "tourist_spot_id",insertable=false, updatable=false)
	private TouristSpot touristSpot;

}
