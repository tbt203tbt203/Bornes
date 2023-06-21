package com.ntico.repository;

import com.ntico.service.Bornes;
import com.ntico.service.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BornesRepository extends JpaRepository<Bornes, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM Bornes WHERE IdBorne = :IdBorne")
    ArrayList<Bornes> findByIdBorne(String IdBorne);

    @Query(nativeQuery = true, value = "SELECT * FROM Bornes WHERE Utiliser = :Utiliser")
    ArrayList<Bornes> findByUtiliser(String Utiliser);

    @Query(nativeQuery = true, value = "SELECT * FROM Bornes WHERE Utilisateur = :Utilisateur")
    ArrayList<Bornes> findByUtilisateur(String Utilisateur);
    @Query(nativeQuery = true, value = "SELECT * FROM Bornes WHERE Heure = :Heure")
    ArrayList<Bornes> findByHeure(String Heure);

    @Query(nativeQuery = true, value = "DELETE FROM Bornes WHERE id_borne = :idBorne")
   // ArrayList<Bornes> deleteByIdBorne(String idBorne);
    ArrayList<Bornes> deleteByIdBorne(@Param("idBorne") String idBorne);

    @Query(nativeQuery = true, value = "SELECT * FROM Log WHERE id_borne = 818")
    ArrayList<Log> findBy818();

    @Query(nativeQuery = true, value = "SELECT * FROM Bornes WHERE debutfin = :debutfin")
    ArrayList<Bornes> findBydebutfin(String debutfin);
}