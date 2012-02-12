package com.rafarocha.sample.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.neo4j.rest.support.RestTestBase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.rafarocha.sample.repository.ItineraryRepository;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/test-spring.xml"})
@Transactional
public class DomainTest extends RestTestBase {
	
	@Resource ItineraryRepository repository;
	
	@Test public void simulateSuspectedSlowAdvisedAroundAspectJ() throws Exception {
		long start = System.currentTimeMillis();
		Line line = new Line( "313" ).persist();
		
		Coordinate coordinate = new Coordinate(0.0, 0.0);
		for (int i = 0; i < 30; i++) {
			line.addWay( new Position(coordinate) );
		}
		
		long finish = System.currentTimeMillis();
		String timeFormated = new SimpleDateFormat("mm:ss:SSSS").format( new Date(finish-start) );
		System.out.println( String.format("time with persist %s", timeFormated) );
	}
	
	
	@Test public void simulateSuspectedSlowAdvisedAroundAspectJWithoutPersistEntity() throws Exception {
		long start = System.currentTimeMillis();
		Line line = new Line( "313" ); // remove persist method
		
		Coordinate coordinate = new Coordinate(0.0, 0.0);
		for (int i = 0; i < 30; i++) {
			line.addWay( new Position(coordinate) );
		}
		
		long finish = System.currentTimeMillis();
		String timeFormated = new SimpleDateFormat("mm:ss:SSSS").format( new Date(finish-start) );
		System.out.println( String.format("time WITHOUT persist %s", timeFormated) );
	}
	
}