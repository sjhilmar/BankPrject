package com.Bootcamp.BankMovement.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.Bootcamp.BankMovement.domain.ClientProduct;
import com.Bootcamp.BankMovement.web.model.ClientProductModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientProductMapper {

    ClientProduct clientProductModelToClientProduct (ClientProductModel model);

    ClientProductModel clientProductToClientProductModel (ClientProduct clientProduct);

    List<ClientProductModel> clientProductsToClientProductModels(List<ClientProduct> clientProducts);


    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget ClientProduct entity, ClientProductModel updateEntity);
}
