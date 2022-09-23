package com.Bootcamp.BankMovement.domain;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
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
@Document(collection = "clientProduct")
public class ClientProduct {
    @Id
    private String id;

    @Column(unique=true)
    private String accountNumber;

    private BigDecimal balance;

    private Date openDate;

    private Date closeDate;

    private String clientId;

    private String codeProduct;
    
    private String clientType;

}
