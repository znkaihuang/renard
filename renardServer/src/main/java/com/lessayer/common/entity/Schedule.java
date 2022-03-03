package com.lessayer.common.entity;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Schedule {
	
	@JsonAlias("Name")
	private String event;
	@JsonAlias("Date")
	private String date;
	@JsonAlias("Day")
	private String day;
	@JsonAlias("Description")
	private String description;
	
}
