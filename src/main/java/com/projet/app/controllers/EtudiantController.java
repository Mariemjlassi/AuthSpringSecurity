package com.projet.app.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.web.bind.annotation.RestController;

import com.projet.app.dto.RechargerCarteRequest;
import com.projet.app.model.Etudiant;

import com.projet.app.services.EtudiantService;



@RestController

public class EtudiantController {
	@Autowired
	private EtudiantService es;
	
	
	
	@PostMapping("/rechargerCarte")
	public void rechargerCarte(@RequestBody RechargerCarteRequest request) {
	    es.rechargerCarte(request.getId(), request.getMontant());
	}
	
	@GetMapping("/api/etudiant")
	public List<Etudiant> getAll(){
		return es.getAllEtudiants();
	}
	@PostMapping("/api/etudiant")
	public Etudiant add(@RequestBody Etudiant etudiant) {
		return es.addEtudiant(etudiant);
	}
	
	@PutMapping("/api/etudiant/{id}")
	public ResponseEntity<Etudiant> update(@PathVariable("id") Long id,@RequestBody Etudiant etudiant){
		Etudiant etudiant2=es.updateEtudiant(id, etudiant);
		if(etudiant2 == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		return ResponseEntity.ok(etudiant2);
	}
	
	
	

}
