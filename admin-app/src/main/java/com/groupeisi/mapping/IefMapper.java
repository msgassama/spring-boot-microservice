package com.groupeisi.mapping;

import com.groupeisi.dto.Ief;
import com.groupeisi.entities.IefEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IefMapper {
    Ief toIef(IefEntity iefEntity);
    IefEntity fromIef(Ief ief);
}
