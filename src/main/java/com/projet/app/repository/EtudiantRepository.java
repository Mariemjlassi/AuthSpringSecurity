package com.projet.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.app.model.Etudiant;
import java.util.List;


@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Long>{
	
}
