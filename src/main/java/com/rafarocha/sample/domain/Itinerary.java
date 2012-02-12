package com.rafarocha.sample.domain;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.util.CollectionUtils;

@NodeEntity
public class Itinerary {
	
	@GraphId protected Long id;
	@RelatedTo(elementClass = Period.class) private Period period;
	@RelatedTo(elementClass = Hour.class) private Set<Hour> hours;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Period getPeriod() {
		return period;
	}
	public void setPeriod(Period period) {
		this.period = period;
	}
	public Set<Hour> getHours() {
		return hours;
	}
	protected void setHours(Set<Hour> hours) {
		this.hours = hours;
	}
	
	public Itinerary set(Period period) {
		setPeriod(period);
		return this;
	}
	
	public Itinerary set(String hour) {
		return add(hour);
	}
	
	public Itinerary add(String hour) {
		if ( CollectionUtils.isEmpty( this.hours ) )
			this.hours = new HashSet<Hour>();
		this.hours.add( Hour.create(hour) ); return this;
	}
	
	private final static char bar = '|'; 
	public Itinerary importTimesheet(Period period, String timesheet) {
		if ( period == null || StringUtils.isEmpty(timesheet) ) return this;
		
		String time = StringUtils.EMPTY;
		for (int i = 0; i < timesheet.length(); i++) {
			if ( timesheet.charAt(i) == bar ) continue;
			time += timesheet.charAt(i);

			if ( time.length() == Hour.PATTERN.length() ) {
				add(time );
				time = StringUtils.EMPTY;
			}
		}
		return set( period );
	}

}