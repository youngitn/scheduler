package com.vp.scheduler.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Errors {
	String statusCode;
	String message;
	String[] fields;

}
