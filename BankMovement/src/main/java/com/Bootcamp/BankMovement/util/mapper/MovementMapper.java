package com.Bootcamp.BankMovement.util.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import com.Bootcamp.BankMovement.domain.Movement;
import com.Bootcamp.BankMovement.web.model.MovementModel;


@Mapper(componentModel = "spring")
public interface MovementMapper {
	Movement movementModelToMovement(MovementModel model);

	MovementModel movementToMovementModel(Movement client);

	List<MovementModel> movementsToMovementModels(List<Movement> clients);

	@Mapping(target = "id", ignore = true)
	void update(@MappingTarget Movement entity, MovementModel updateEntity);
}
