package com.groupeisi.dao;

import com.groupeisi.entities.IaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIaRepository extends JpaRepository<IaEntity, Integer> {
    IaEntity findByNom(String nom);
}
