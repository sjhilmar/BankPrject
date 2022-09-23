package com.Bootcamp.BankClient.domain;

import javax.persistence.*;

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
@Document("proxy")
public class Proxy {

    @Id
    private String id;

    private String type;

    private String fullName;

    private String clientId;
}
