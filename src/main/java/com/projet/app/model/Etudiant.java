package com.projet.app.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity

public class Etudiant extends UserEntity{
	
    private String numeroEtudiant;
    private double soldeCarte;
    
    @OneToMany(mappedBy = "etudiant")
    private List<Paiement> paiements = new ArrayList<>();
    
   
    

}
