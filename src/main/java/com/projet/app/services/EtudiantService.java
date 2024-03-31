package com.projet.app.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.app.model.Etudiant;

import com.projet.app.repository.EtudiantRepository;

@Service
public class EtudiantService {
	
	@Autowired
	private EtudiantRepository etudiantRepository;
	
	
	
	public Etudiant addEtudiant(Etudiant etudiant) {
		return etudiantRepository.save(etudiant);
	}
	
	public List<Etudiant> getAllEtudiants(){
		return etudiantRepository.findAll();
	}
	
	public void deleteEtudiant(long id) {
		 etudiantRepository.deleteById(id);
	}
	
	public Etudiant readById(Long id) {
		Optional<Etudiant> etud = etudiantRepository.findById(id);
		if(etud.isPresent()) {
			return etud.get();
		}else {
			return null;
		}
	}
	
	public double consulterSolde(Long idEtudiant) {
        Optional<Etudiant> etudiantOptional = etudiantRepository.findById(idEtudiant);
        if (etudiantOptional.isPresent()) {
            Etudiant etudiant = etudiantOptional.get();
            return etudiant.getSoldeCarte();
        } else {
            throw new IllegalArgumentException("L'étudiant avec l'ID " + idEtudiant + " n'existe pas.");
        }
    }
	
	public Etudiant updateEtudiant(Long id, Etudiant etudiant) {
		Optional<Etudiant> etud= etudiantRepository.findById(id);
		if(etud.isPresent()) {
			Etudiant etudiantExist = etud.get();
			etudiantExist.setNom(etudiant.getNom());
			etudiantExist.setPrenom(etudiant.getPrenom());
			etudiantExist.setSoldeCarte(etudiant.getSoldeCarte());
			etudiantExist.setCodeSecurite(etudiant.getCodeSecurite());
			etudiantExist.setEmail(etudiant.getEmail());
			return etudiantRepository.save(etudiantExist);
			
		}
		return null;
	}
	
	public void rechargerCarte(Long idEtudiant, double montant) {
		Optional<Etudiant> etud = etudiantRepository.findById(idEtudiant);
		if(etud.isPresent()) {
			Etudiant etudiant=etud.get();
			double nouveauSolde=etudiant.getSoldeCarte()+montant;
			etudiant.setSoldeCarte(nouveauSolde);
			etudiantRepository.save(etudiant);
		}
	}
	
	
	
	
}
