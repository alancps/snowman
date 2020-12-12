package com.snowman.touristspot.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tourist_spot")
@Getter @Setter
@NoArgsConstructor
public class TouristSpot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", length = 100)
	@NotNull
	private String name;
	
	@Column(name="longitude", precision=11, scale=8)
	@NotNull
	private BigDecimal longitude;
	
	@Column(name="latitude", precision=10, scale=8)
	@NotNull
	private BigDecimal latitude;
	
	/* TODO
	 * @Type(type="org.hibernate.spatial.GeometryType")
	private Point coordinates;*/ 
	
	@ManyToOne
	@JoinColumn(name = "category")
	private Category category;
	
	@OneToMany(mappedBy = "touristSpot")
	private List<FavoriteTouristSpot> favoriteTouristSpots;
	
	@OneToMany(orphanRemoval = true, mappedBy = "touristSpot")
	private List<PictureTouristSpot> pictureTouristSpot;
	
}
