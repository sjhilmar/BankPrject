package com.Bootcamp.BankMovement.web.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.sql.Timestamp;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientProductModel {

    @JsonProperty("clientProductId")
    private String id;
    @JsonProperty("email")
    @NotBlank(message="email type can't be empty")
    private String email;

    @JsonProperty("accountNumber")
    @NotBlank(message="accountNumber type can't be empty")
    private String accountNumber;
    @JsonProperty("balance")
    @NotBlank(message="balance type can't be empty")
    private BigDecimal balance;

    private String clientId;

    private String codeProduct;

    @JsonProperty("openDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Timestamp openDate;

    @JsonProperty("closeDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Timestamp closeDate;


    private String clientType;

}
