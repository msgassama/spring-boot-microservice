package com.groupeisi.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ief")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IefEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100, nullable = false)
    private String nom;
    @ManyToOne
    private IaEntity iaEntity;
}
