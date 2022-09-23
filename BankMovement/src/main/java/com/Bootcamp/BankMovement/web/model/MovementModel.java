package com.Bootcamp.BankMovement.web.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.Bootcamp.BankMovement.domain.ClientProduct;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovementModel {
	@JsonProperty("movementId")
	private String id;

	@JsonProperty("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private Timestamp dateMovement;

	private String type;

	private BigDecimal amount;

	private String clientProductId;

}
