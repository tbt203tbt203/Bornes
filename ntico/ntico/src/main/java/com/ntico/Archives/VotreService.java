/*package com.ntico.repository;

import com.ntico.service.Bornes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VotreService {
    private final ProductRepository productRepository;

    public VotreService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ArrayList<Bornes> obtenirBornesParPseudo(String pseudo) {
        return productRepository.findByPseudo(pseudo);
    }

    // Autres méthodes de service...
}
*/