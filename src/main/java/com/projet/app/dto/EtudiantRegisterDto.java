package com.projet.app.dto;

import lombok.Data;

@Data
public class EtudiantRegisterDto extends RegisterDto{
	private String numeroEtudiant;
    private double soldeCarte;
}
