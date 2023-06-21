package com.ntico.controller;

import com.ntico.repository.BornesRepository;
import com.ntico.repository.LogRepository;
import com.ntico.repository.UtilisateursRepository;
import com.ntico.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.ntico.service.LogHeure.getBorneHeures;
import static com.ntico.service.LogLibre.getBorneLibre;
import static com.ntico.service.LogUsername.getBorneUsername;

@SpringBootApplication
@Controller
@RestController
@Component
@RequestMapping("borne")
public class BornesController {
    @Autowired
    private BornesService bornesService;
    private final UtilisateursRepository utilisateursRepository;
    private LogDebutfin LogDebutfin;
    @Autowired
    private BornesRepository bornesRepository;
    @Autowired
    private LogUsername logUsername;
    @Autowired
    private LogLibre LogLibre;
    @Autowired
    private LogHeure LogHeure;
    @Autowired
    private LogRepository logRepository;

    public BornesController(UtilisateursRepository utilisateursRepository) {
        this.utilisateursRepository = utilisateursRepository;
    }
    public static void main(String[] args) {
        SpringApplication.run(BornesController.class, args);
    }

    @GetMapping("/all")
    public ResponseEntity getBorne_ALL() throws IOException {
        String Borne818 = bornesService.getBorne("818");
        String Borne820 = bornesService.getBorne("820");
        String Borne821 = bornesService.getBorne("821");
        String Borne822 = bornesService.getBorne("822");
        //  String te = relation.getBorne818();
        String RedcocouleurRouge = bornesService.getCouleurRouge();
        String infoB = "<title>Information bornes</title>" +
                "<h3><p>Information bornes</p></h3>" +
                "<strong>818</strong>" + Borne818 + "<br><br>" +
                "<strong>820</strong>" + Borne820 + "<br><br>" +
                "<strong>821</strong>" + Borne821 + "<br><br>" +
                "<strong>822</strong>" + Borne822 + "<br><br>" +
                "<br><br>" + RedcocouleurRouge + "<br>";

        return ResponseEntity.status(HttpStatus.OK).body(infoB);

    }

    @GetMapping("/statut")
    public ResponseEntity getStatut() {
        ArrayList<Utilisateur> personnesEnAttente = utilisateursRepository.findByStatut();
        return ResponseEntity.ok(personnesEnAttente);
    }
    @PostMapping("/create/utilisateur")
    public RedirectView createProfilUtilisateur(@RequestParam("email") String email,
                                                @RequestParam("pseudo") String pseudo,
                                                @RequestParam("statut") String statut) {
        Utilisateur produit = new Utilisateur();
        produit.setEmail(email.toLowerCase()); // Conversion en minuscules
        produit.setPseudo(pseudo.toLowerCase()); // Conversion en minuscules
        produit.setStatut(statut.toLowerCase()); // Conversion en minuscules
        utilisateursRepository.save(produit);
        return new RedirectView("/borne/merci/redirection");
    }
    //relations page html ne pas supprimer
    @GetMapping("/merci/redirection")
    public RedirectView getAllProduct3() {
        return new RedirectView("http://localhost:8080/Merci.html");
    }
    //relations page html ne pas supprimer
    @PostMapping("/product/all/AjoutUtilisateur")
    public RedirectView getAllProduct4() {
        return new RedirectView("http://localhost:8080/AjoutUtilisateur.html");
    }
    //relations page html ne pas supprimer
    @GetMapping("/enregistrement/attente")
    public RedirectView getAllProductAttente() {
        return new RedirectView("http://localhost:8080/StatutAttente.html");
    }

    @GetMapping("/mettreAttente")
        public ResponseEntity<String> updateMettreAttente(@RequestParam(value = "mettreAttenteParPseudo") String addAttente) {
        ArrayList<Utilisateur> usersFromDb = utilisateursRepository.findByPseudo(addAttente.toLowerCase());
        if (usersFromDb != null && !usersFromDb.isEmpty()) {
            Utilisateur userFromDb = usersFromDb.get(0);
            userFromDb.setStatut("attente");
            utilisateursRepository.save(userFromDb); // Mettre à jour la base de données

            // Ajoutez un log pour vérifier si la sauvegarde a été effectuée
            System.out.println("La sauvegarde a été effectuée avec succès.");
            return ResponseEntity.ok("Mise à jour du statut de " + addAttente + " effectuée avec succès !" + "<br>Valeur enregistrée : attente");
        } else {
            // La liste est vide, vous pouvez afficher un message ou effectuer une autre action appropriée
            System.out.println("Aucun enregistrement trouvé dans la base de données");
            return ResponseEntity.ok("Ce pseudo n'est pas trouvé dans la base de données.");
        }
    }
//s'enlever de l'attente relier a StatutNOAttente.html
    @GetMapping("/enleverAttente")
    public ResponseEntity<String> updateEnleverAtente(@RequestParam(value = "enleverAttenteParPseudo") String delAttente) {
        ArrayList<Utilisateur> usersFromDb = utilisateursRepository.findByPseudo(delAttente.toLowerCase());
        if (usersFromDb != null && !usersFromDb.isEmpty()) {
            Utilisateur userFromDb = usersFromDb.get(0);
            userFromDb.setStatut("null");
            utilisateursRepository.save(userFromDb); // Mettre à jour la base de données
            System.out.println("La sauvegarde a été effectuée avec succès.");
            return ResponseEntity.ok("Mise à jour du statut de " + delAttente + " effectuée avec succès !" + "<br>Valeur enregistrée : null");
        } else {
            // La liste est vide, vous pouvez afficher un message ou effectuer une autre action appropriée
            System.out.println("Aucun enregistrement trouvé dans la base de données");
            return ResponseEntity.ok("Ce pseudo n'est pas trouvé dans la base de données.");
        }
    }
    //vOIR LA TABLE LOG EN JSON
    @GetMapping("/logenjson")
    public ResponseEntity updateLogJSON() throws IOException {
        List<String> borneIds = Arrays.asList("818", "820", "821", "822");
        for (String borneId : borneIds) {
            String Username = getBorneUsername(borneId);
            String Libre = getBorneLibre(borneId);
            String BorneHeure = getBorneHeures(borneId);
            String debutfin = LogDebutfin.getBornedebutfinBddTableLog(borneId);

            Bornes produit = new Bornes();

            produit.setIdBorne(Long.valueOf(borneId));
            produit.setUtiliser(Libre);
            produit.setUtilisateur(Username);
            produit.setHeure(BorneHeure);
            produit.setDf(debutfin);

            bornesRepository.save(produit);
        }
        List<Bornes> allBornes = bornesRepository.findAll();
        return ResponseEntity.ok(allBornes);
    }

    //Introduction de la table log ne pas supprimer
    private Log createLogEntry(String borneId, String ancienneValeur, String heure, String pseudo, String debutfin, String libre) {
        Log log = new Log();
        log.setBorneId(borneId);

        log.setAncienneValeur(ancienneValeur);
        log.setHeure(heure);
        log.setPseudo(pseudo);
        log.setDebutfin(debutfin);
        log.setLibre(libre);
        return log;
    }
    //tous de la base de table Log ne pas supprimer
    @GetMapping("/product/all/create/updateAllBornesB")
    public ResponseEntity updateAllBornesB() throws IOException {
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

    //recupere le dernier pseudo de la borne demander ici 818
    // a partir de la base log
    @GetMapping("/pseudoRecup/818")
    public ResponseEntity pseudo818Recuperation() {
        ArrayList<Log> testpseudo = logRepository.findBybornes("818");
        // String pseudo = logRepository.findBybornes().get(0).getPseudo();
        return ResponseEntity.ok(testpseudo.get(0).getPseudo());
    }
}