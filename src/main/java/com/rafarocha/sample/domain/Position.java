package com.rafarocha.sample.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;

@NodeEntity
public class Position {

	@GraphId private String id;
	
	private double latitude;
	private double longitude;
	private double altitude;
	
	public Position() {}
	public Position(double lat, double lng, double alt) {
		this.latitude = lat;
		this.longitude = lng;
		this.altitude = alt;
	}
	public Position(Coordinate coordinate) {
		this.latitude = coordinate.getLatitude();
		this.longitude = coordinate.getLongitude();
		this.altitude = coordinate.getAltitude();	
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getAltitude() {
		return altitude;
	}
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
	
}