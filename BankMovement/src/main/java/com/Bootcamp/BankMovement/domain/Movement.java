package com.Bootcamp.BankMovement.domain;

import java.util.Date;

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
@Document(collection = "movement")
public class Movement {
    @Id
    private String id;

    private Date dateMovement;

    private String type;

    private Double amount;

    private String clientProductId;
    
    private String reason;
    
    private String destinedNumberAccount;

}
