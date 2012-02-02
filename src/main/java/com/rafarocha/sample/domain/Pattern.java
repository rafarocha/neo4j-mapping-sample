package com.rafarocha.sample.domain;

public enum Pattern {

	WorkingDay, Monday, Tuesday, Wednesday, 
		Thursday, Friday, Saturday, Sunday;
	
	public String toString() { 
		return super.toString().toLowerCase();	
	}
	
	public Period get() {
		return new Period( this.toString() );
	}
	
}