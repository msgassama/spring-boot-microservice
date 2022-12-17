package com.groupeisi.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "ia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100, nullable = false)
    private String nom;
    //@OneToMany(mappedBy = "iaEntity")
    //private List<AppUserEntity> appUserEntities;
    @ManyToOne
    private AppUserEntity appUserEntity;
    @OneToMany(mappedBy = "iaEntity")
    private List<IefEntity> iefEntities;
}
