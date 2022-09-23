package com.Bootcamp.BankMovement.domain;


import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
 @Document(collection = "physicalCard")
public class PhysicalCard {

	private String id;
	private String numberCard;
	private Date OpenDate;
	
	
}
