package com.Bootcamp.BankClient.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientModel {

	@JsonProperty("clientId")
	private String id;
	@JsonProperty("profileId")
	private String clientProfileId;
	private String documentNumber;
	private String fullName;

}
