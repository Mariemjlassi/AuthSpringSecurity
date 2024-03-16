package com.projet.app.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.projet.app.dto.RechargerCarteRequest;
import com.projet.app.model.Etudiant;
import com.projet.app.services.EtudiantService;



@RestController
@RequestMapping("/api/etudiant")
public class EtudiantController {
	@Autowired
	private EtudiantService es;
	@PostMapping
	public Etudiant addEtudiant(@RequestBody Etudiant etudiant) {
		return es.addEtudiant(etudiant);
	}
	
	@PostMapping("/rechargerCarte")
	public void rechargerCarte(@RequestBody RechargerCarteRequest request) {
	    es.rechargerCarte(request.getId(), request.getMontant());
	}
	
	@GetMapping
	public List<Etudiant> getAll(){
		return es.getAllEtudiants();
	}
	
	
	

}
