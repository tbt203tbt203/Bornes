package com.ntico.repository;

import com.ntico.service.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UtilisateursRepository extends JpaRepository<Utilisateur, Long> {

    @Query(nativeQuery = true, value="select * from Utilisateur where pseudo = :pseudo")
    ArrayList<Utilisateur> findByPseudo(String pseudo);

    @Query(nativeQuery = true, value = "select * from Utilisateur where email = :email")
    ArrayList<Utilisateur> findByEmail(String email);
    @Query(nativeQuery = true, value = "SELECT * FROM Utilisateur WHERE statut = 'attente'")
    ArrayList<Utilisateur> findByStatut();
}


