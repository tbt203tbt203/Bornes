package com.ntico.service;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Log")
@Getter
@Setter
public class Log{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String borneId;

    private String ancienneValeur;
    private String heure;
    private String pseudo;
    private String debutfin;
    private String libre;



    @Override
    public String toString() {
        return "Log [id = " + id + ", borne_id = " + borneId +  ", debutfin = " + debutfin + ", heure = " + heure +", libre = " + libre + ", pseudo = " + pseudo + "]" + "<br>";
    }

}
