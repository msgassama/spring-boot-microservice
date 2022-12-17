package com.groupeisi.mapping;

import com.groupeisi.dto.Ia;
import com.groupeisi.entities.IaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IaMapper {
    Ia toIa(IaEntity iaEntity);
    IaEntity fromIa(Ia ia);
}
