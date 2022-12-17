package com.groupeisi.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ief {
    private Integer id;
    @NotNull(message = "Le nom ne doit pas etre nul!")
    private String nom;
}
