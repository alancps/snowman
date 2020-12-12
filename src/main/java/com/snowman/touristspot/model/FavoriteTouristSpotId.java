package com.snowman.touristspot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteTouristSpotId implements Serializable {
	private static final long serialVersionUID = -834597410441295456L;

	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "tourist_spot_id")
	private Long touristSpotId;

}
