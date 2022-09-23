package com.Bootcamp.BankClient.domain;

import java.util.List;

import javax.persistence.Column;
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
@Document("client")
public class Client {

	    @Id
	    private String id;

	    private List<ClientProfile> clientProfile;

	    @Column(unique=true)
	    private String documentNumber;

	    private String fullName;
}
