package com.ntico.repository;

import com.ntico.service.Bornes;
import com.ntico.service.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {


//permet de gerer la table log
    @Query(nativeQuery = true, value = "SELECT * FROM log WHERE borne_id = CAST(:borne_id AS VARCHAR) AND pseudo <> 'nul' ORDER BY id DESC LIMIT 1")
    ArrayList<Log> findBybornes(String borne_id);

}