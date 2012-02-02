package com.rafarocha.sample.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.rafarocha.sample.domain.Itinerary;

public interface ItineraryRepository extends GraphRepository<Itinerary> {

}