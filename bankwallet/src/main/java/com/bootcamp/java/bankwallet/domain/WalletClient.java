package com.bootcamp.java.bankwallet.domain;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("walletClient")
public class WalletClient {
	@Id
	private String id;
	private String documentNumber;
	private String documentType;
	private String fullName;
	private String phoneNumber;
	private String phoneImei;
	private String email;
	private Double balance;
	private String clientProductId;
	

}
