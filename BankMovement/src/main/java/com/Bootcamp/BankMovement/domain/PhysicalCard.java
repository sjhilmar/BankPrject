package com.Bootcamp.BankMovement.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;

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
	@Id
	private String id;
	private String numberCard;
	private Date OpenDate;
	private List<ClientProduct>clientProducts;

}
