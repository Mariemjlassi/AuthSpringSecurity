package com.projet.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projet.app.dto.AdminRegisterDto;
import com.projet.app.dto.ChefRegisterDto;
import com.projet.app.dto.EtudiantRegisterDto;
import com.projet.app.dto.RegisterDto;
import com.projet.app.model.Admin;
import com.projet.app.model.Chef;
import com.projet.app.model.Etudiant;
import com.projet.app.model.UserEntity;
import com.projet.app.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Service
public class AuthServiceImpl implements AuthService {
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public UserEntity createUser(RegisterDto registerDto) {
        
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return null;
        }
        UserEntity user;
        if (registerDto instanceof EtudiantRegisterDto) {
            user = createUserFromEtudiantDto((EtudiantRegisterDto) registerDto);
        } else if (registerDto instanceof ChefRegisterDto) {
            user = createUserFromChefDto((ChefRegisterDto) registerDto);
        } else if (registerDto instanceof AdminRegisterDto) {
            user = createUserFromAdminDto((AdminRegisterDto) registerDto);
        } else {
            throw new IllegalArgumentException("Invalid RegisterDto type");
        }
        
        userRepository.save(user);

        return user;
    }
    
    @Transactional
    private UserEntity createUserFromEtudiantDto(EtudiantRegisterDto dto) {
        
    	Etudiant etudiant = new Etudiant();
        etudiant.setEmail(dto.getEmail());
        etudiant.setPassword(passwordEncoder.encode(dto.getPassword()));
        etudiant.setRole(dto.getRole());
        etudiant.setNom(dto.getNom());
        etudiant.setPrenom(dto.getPrenom());
        etudiant.setSoldeCarte(dto.getSoldeCarte());
        etudiant.setNumeroEtudiant(dto.getNumeroEtudiant());
        
        entityManager.persist(etudiant);
        entityManager.persist(etudiant);

        return etudiant;
    }
    @Transactional
    private UserEntity createUserFromChefDto(ChefRegisterDto registerDto) {
        
        Chef chef = new Chef();
        chef.setEmail(registerDto.getEmail());
        chef.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        chef.setRole(registerDto.getRole());
        chef.setNom(registerDto.getNom());
        chef.setPrenom(registerDto.getPrenom());
        entityManager.persist(chef);
        return chef;
    }
    @Transactional
    private UserEntity createUserFromAdminDto(AdminRegisterDto registerDto) {
        
        Admin admin = new Admin();
        admin.setEmail(registerDto.getEmail());
        admin.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        admin.setRole(registerDto.getRole());
        admin.setNom(registerDto.getNom());
        admin.setPrenom(registerDto.getPrenom());
        entityManager.persist(admin);
        return admin;
    }


}
