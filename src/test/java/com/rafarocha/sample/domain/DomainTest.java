package com.rafarocha.sample.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.neo4j.rest.support.RestTestBase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.rafarocha.sample.repository.ItineraryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/test-spring.xml"})
@Transactional
public class DomainTest extends RestTestBase {
	
	@Resource ItineraryRepository repository;
	
	@Test public void createItinerary() {
		Period workingDay = Pattern.WorkingDay.get().persist();
		Period saturday = Pattern.Saturday.get().persist();
		Period sunday = Pattern.Sunday.get().persist();
		
		Itinerary itinerary = Itinerary.create()
				.add(workingDay, "04:00")
				.add(saturday, "05:00")
				.add(sunday, "06:00")
				.persist();
		
		Itinerary found = repository.findOne( itinerary.getId() );
		assertNotNull( found.getBoard() ); // assertion error here, maybe mapping problem
		assertEquals( itinerary.getBoard().size(), found.getBoard().size() );
	}
	
}