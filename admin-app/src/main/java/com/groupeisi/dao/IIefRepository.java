package com.groupeisi.dao;

import com.groupeisi.entities.IefEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIefRepository extends JpaRepository<IefEntity, Integer> {
    IefEntity findByNom(String nom);
}
