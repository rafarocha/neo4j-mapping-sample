package com.rafarocha.sample.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.google.common.collect.Ranges;

@NodeEntity
public class Hour implements Comparable<Hour> {
	
	@GraphId public Long id;
	
	public static final String PATTERN = "HH:mm";
	public String hour;   // 00-23 timezone
	public String minute; // 00-59 minutes
	
	public Hour() {}
	public Hour(String hour, String minute) {
		this.hour = hour;
		this.minute = minute;
	}
	
	public static Hour create(String info) { // TODO vulnerable to errors
		String[] crumb = info.split(":");
		return new Hour( crumb[0], crumb[1] );
	}
	
	public static Hour create(Date date) {
		SimpleDateFormat pattern = new SimpleDateFormat( PATTERN );
		String hour = pattern.format( date );
		return Hour.create( hour );
	}
	
	public boolean isValid(String hour) {
		if ( hour == null ) return false;
		
		if ( Ranges.closed(5,5).apply(hour.length()) ) return false; // [hh:00] or [hhmm]
		if ( !hour.contains(":") ) return false;
		
		String[] crumb = hour.split(":");
		if ( !NumberUtils.isNumber(crumb[0]) ) return false;
		if ( !NumberUtils.isNumber(crumb[1]) ) return false;
		return true;
	}
	
	@Override public String toString() {
		if ( this == null ) return null;
		if ( StringUtils.isEmpty(this.hour) ) return null;
		return this.hour + ":" + this.minute;
	}
	
	public static void throwIllegalStateException() {
		throw new IllegalStateException( "problem with patter hour [hh:mm]" );
	}

	@Override public int compareTo(Hour hour) {
		if ( hour == null ) return 0;
		if ( this.hour == null || hour.hour == null ) return 0;
		
		int compareHour = this.hour.compareTo( hour.hour );
		int compareMinutes = this.minute.compareTo( hour.minute );
				
		if ( compareHour != 0 ) return compareHour;
		return compareMinutes;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	
}