package com.ntico.service;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Bornes")
@Getter
@Setter
public class Bornes {


   // @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    @Column(name = "id_borne")
    private Long idBorne;

    @Column(name = "utilisateur")
    private String utilisateur;

    @Column(name = "libre")
    private String utiliser;

    @Column(name = "Heure")
    private String Heure;
    // Getters and setters
    @Column(name = "debutfin")
    private String df;
}
