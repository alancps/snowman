package com.snowman.touristspot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "picture_tourist_spot")
@Getter @Setter
@NoArgsConstructor
public class PictureTouristSpot {
	
	public PictureTouristSpot(byte[] picture) {
		this.picture = picture;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="picture")
	@NotNull
	private byte[] picture;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "tourist_spot")
	@NotNull
	private TouristSpot touristSpot;
	
	@ManyToOne
	@JoinColumn(name = "created_by")
	@NotNull
	private User createdBy;
}
