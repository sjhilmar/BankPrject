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
 @Document(collection = "creditDebts")
public class CreditDebts {
	
	private String id;
	private String clientProductId;
	private Date dateDebts;
	private Double amountDebts;
//	private List<ClientProduct> clientProduct;
		
}
