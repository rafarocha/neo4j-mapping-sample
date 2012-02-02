package com.rafarocha.sample.domain;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.util.CollectionUtils;

@NodeEntity
public class Itinerary {
	
	@GraphId protected Long id;
	
//	@RelatedTo(elementClass = Queue.class, type = "ACTS_IN", direction = INCOMING)
	private Map<Period, Queue<String>> board;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Map<Period, Queue<String>> getBoard() {
		return this.board;
	}
	protected void setBoard(Map<Period, Queue<String>> board) {
		this.board = board;
	}
	
	public Itinerary add(Period period, String hour) {
		getScheduleButIfEmptyInitIt( period ).add( hour );
		return this;
	}
	
	public static Itinerary create() {
		try {
			return Itinerary.class.newInstance();
		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Queue<String> getScheduleButIfEmptyInitIt( Period period ) {
		if ( getBoard() != null ) {
			if ( !CollectionUtils.isEmpty(getBoard().get(period)) ) 
				return getBoard().get( period );
		} else
			setBoard( new HashedMap() );
		getBoard().put(period, new PriorityQueue<String>());
		return getBoard().get( period );
	}
	
	private final static char bar = '|'; 
	public Itinerary importScheduleFromGoogleEarth(Period period, String timesheet) {
		if ( period == null || StringUtils.isEmpty(timesheet) ) return this;
		
		String time = StringUtils.EMPTY;
		for (int i = 0; i < timesheet.length(); i++) {
			if ( timesheet.charAt(i) == bar ) continue;
			time += timesheet.charAt(i);

			if ( time.length() == Hour.PATTERN.length() ) {
				getScheduleButIfEmptyInitIt( period ).add( time );
				time = StringUtils.EMPTY;
			}
		}
		return this;
	}

}