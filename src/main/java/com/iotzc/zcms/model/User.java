package com.iotzc.zcms.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.Data;

@Data
@EntityScan
public class User {

	private Integer id; 
}
