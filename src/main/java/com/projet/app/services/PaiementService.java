package com.projet.app.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projet.app.model.Etudiant;
import com.projet.app.model.Menu;
import com.projet.app.model.Paiement;

import com.projet.app.repository.EtudiantRepository;
import com.projet.app.repository.PaiementRepository;

@Service
public class PaiementService {
	
	@Autowired
    private EtudiantRepository etudiantRepository;
	@Autowired
	private MenuService ms;
    @Autowired
    private PaiementRepository paiementRepository;
    @Autowired
	private RestaurantService rs;
    
    @Transactional
    public void effectuerPaiement(Long etudiantId, String numeroCarte,int codeSecurite) {
      Optional<Etudiant> etud = etudiantRepository.findById(etudiantId);
      double m =200;
        if(etud.isPresent()) {
        	Etudiant etudiant=etud.get();
        	if (aDejaPaiementPourAujourdhui(etudiant)) {
                throw new IllegalStateException("L'étudiant a déjà effectué un paiement aujourd'hui.");
            }
        	Menu menu = ms.getMenuDuJourActuel(); 
	        
        	if(menu!=null &&menu.getQteDisponible()>0) {
        		int nombrePlacesDisponibles = rs.getNombrePlacesDisponibles();
        		if (nombrePlacesDisponibles > 0) { 
                    if (etudiant.getSoldeCarte() >= m) {
                        double nouveauSolde = etudiant.getSoldeCarte() - m;

                        etudiant.setSoldeCarte(nouveauSolde);
                        Paiement paiement = new Paiement();
                        paiement.setNumeroCarte(numeroCarte);
                        paiement.setCodeSecurite(codeSecurite);
                        paiement.setMontant(m);
                        paiement.setEtudiant(etudiant);
                        etudiant.getPaiements().add(paiement);

                        rs.mettreAjourPlacesDisponibles(1);
                        menu.setQteDisponible(menu.getQteDisponible() - 1);

                        paiementRepository.save(paiement);
                        etudiantRepository.save(etudiant);
                    }
        		}
        	}
        }
    
    }
    
    private boolean aDejaPaiementPourAujourdhui(Etudiant etudiant) {
        return etudiant.getPaiements().stream()
                .anyMatch(paiement -> paiement.getDatePaiement().equals(LocalDate.now()));
    }
    
    public List<Paiement> getAllPaiements(){
    	return paiementRepository.findAll();
    }
    

}
