package com.rafarocha.sample.domain;

import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Schedule {
	
	@RelatedTo(elementClass = Itinerary.class)
	private Itinerary itinerary;
	
	public Itinerary getItinerary() {
		return itinerary;
	}
	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}

}