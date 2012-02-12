package com.rafarocha.sample.domain;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Line {
	
	@GraphId protected Long id;
	
	public String code;
	
	@RelatedTo(elementClass = Itinerary.class) public Set<Itinerary> schedule;
	@RelatedTo(elementClass = Position.class) public Set<Position> ways;
	@RelatedTo(elementClass = Position.class) public Set<Position> stops;
	
	public Line() {}
	public Line(String code) {
		this();
		this.code = code;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Set<Position> getWays() {
		return ways;
	}
	public void setWays(Set<Position> ways) {
		this.ways = ways;
	}
	public Set<Itinerary> getSchedule() {
		return schedule;
	}
	protected void setSchedule(Set<Itinerary> schedule) {
		this.schedule = schedule;
	}
	public Set<Position> getStops() {
		return stops;
	}
	protected void setStops(Set<Position> stops) {
		this.stops = stops;
	}
	
	public Line addWay(Position pos) {
		if ( this.ways == null )
			this.ways = new HashSet<Position>();
		this.ways.add( pos ); return this;
	}
	
	public Line removeWay(Position pos) {
		if ( !CollectionUtils.isEmpty( this.ways ) )
			this.ways.remove( pos );
		return this;
	}
	
	public Line addStop(Position position) {
		if ( CollectionUtils.isEmpty( this.stops ) )
			this.stops = new HashSet<Position>();
		this.stops.add( position ); return this;
	}
	
	public Line removeStop(Position position) {
		if ( !CollectionUtils.isEmpty( this.ways ) )
			this.stops.remove( position );
		return this;
	}
	
	public Line add(Itinerary itinerary) {
		if ( CollectionUtils.isEmpty( this.schedule ) )
			this.schedule = new HashSet<Itinerary>();
		this.schedule.add( itinerary ); return this;
	}
	
	public Line remove(Itinerary itinerary) {
		if ( !CollectionUtils.isEmpty( this.schedule ) ) 
			this.schedule.remove( itinerary );
		return this;
	}
	
	public Line clearWays() {
		if ( !CollectionUtils.isEmpty( this.ways ) )
			this.ways.clear();
		return this;
	}
	
	public Line clearStops() {
		if ( !CollectionUtils.isEmpty( this.stops ) )
			this.stops.clear();
		return this;
	}

}