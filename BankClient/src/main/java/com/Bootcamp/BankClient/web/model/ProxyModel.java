package com.Bootcamp.BankClient.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProxyModel {

    @JsonProperty("proxyId")
    private String id;

    private String type;

    private String fullName;

    private String clientId;

}
