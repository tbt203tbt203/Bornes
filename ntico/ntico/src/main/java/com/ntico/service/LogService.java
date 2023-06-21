/*package com.ntico.service;

import com.ntico.repository.BornesRepository;
import com.ntico.repository.LogRepository;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.ntico.service.LogHeure.getBorneHeures;
import static com.ntico.service.LogLibre.getBorneLibre;
import static com.ntico.service.LogUsername.getBorneUsername;
@Service
public class LogService {

    private BornesRepository bornesRepository;
    private LogRepository logRepository;

    public LogService(BornesRepository bornesRepository, LogRepository logRepository) {
        this.bornesRepository = bornesRepository;
        this.logRepository = logRepository;
    }
    public String getLog() throws IOException {

        List<String> borneIds = Arrays.asList("818", "820", "821", "822");

        for (String borneId : borneIds) {
            String Username = getBorneUsername(borneId);
            String Libre = getBorneLibre(borneId);
            String BorneHeure = getBorneHeures(borneId);
            String debutfin = LogDebutfin.getBornedebutfinBddTableLog(borneId);

            // Recherchez l'ancienne entrée de Bornes dans la base de données
            Optional<Bornes> AncienneValeurBorne = bornesRepository.findById(Long.valueOf(borneId));

            if (AncienneValeurBorne.isPresent()) {
                Bornes oldBornes = AncienneValeurBorne.get();

                if (oldBornes.getUtiliser() == null || !oldBornes.getUtiliser().equals(Libre)) {

                    if (Libre != null && Libre.equals("non")) { //Libre.equals("non")
                        Log log = createLogEntry(borneId, oldBornes.getUtiliser(), BorneHeure, Username, debutfin, Libre);
                        logRepository.save(log);
                        oldBornes.setUtiliser(Libre);
                        oldBornes.setDf(debutfin);
                        oldBornes.setHeure(BorneHeure);
                    }

                    if(oldBornes.getUtiliser() != null || oldBornes.getUtiliser().equals(Libre)){

                        LocalDateTime dateTimeActuelle = LocalDateTime.now();
                        DateTimeFormatter formatteur = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String dateTimeFormatee = dateTimeActuelle.format(formatteur);
                        oldBornes.setHeure(dateTimeFormatee);

                        String pseudo = logRepository.findBybornes(borneId).get(0).getPseudo();
                        oldBornes.setUtilisateur(pseudo);

                        Log log = createLogEntry(borneId, oldBornes.getUtiliser(), dateTimeFormatee, pseudo, debutfin, Libre);
                        logRepository.save(log);
                        oldBornes.setUtiliser(Libre);
                        oldBornes.setDf(debutfin);
                    }
                }
                bornesRepository.save(oldBornes);
            } else {
                // Si aucune entrée de Bornes n'existe, vérifiez si une entrée avec le même ID de borne existe déjà
                Optional<Bornes> existingBornes = bornesRepository.findById(Long.valueOf(borneId));

                if (!existingBornes.isPresent()) {
                    // Si aucune entrée avec le même ID de borne n'existe, créez une nouvelle entrée
                    Bornes newBornes = new Bornes();
                    newBornes.setIdBorne(Long.valueOf(borneId));
                    newBornes.setUtiliser(Libre);
                    newBornes.setUtilisateur(Username);
                    newBornes.setHeure(BorneHeure);
                    newBornes.setDf(debutfin);

                    bornesRepository.save(newBornes);
                }
            }
        }
        List<Log> allLogs = logRepository.findAll();
        return ResponseEntity.ok(allLogs);
    }

}

*/