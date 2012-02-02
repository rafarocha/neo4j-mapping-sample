package com.rafarocha.sample.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Period {
	
	public Period() {}
	public Period(String name) {
		this.name = name;
	}

	@GraphId private Long id;	
	private String name;
	private Long start;
	private Long finish;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	public Long getFinish() {
		return finish;
	}
	public void setFinish(Long finish) {
		this.finish = finish;
	}
	public boolean isDayOfWeek() {
		return ( start == null || finish == null );
	}
	
	public void fill() {
		for (Pattern pattern : Pattern.values()) {
			pattern.get().persist();
		}
	}
	
}