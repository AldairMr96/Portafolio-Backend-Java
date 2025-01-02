package com.mycompany.clinica_odontologica.service;

import com.mycompany.clinica_odontologica.model.Secretariat;
import com.mycompany.clinica_odontologica.repository.ISecretariatRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecretariatService implements ISecretariatService {
    @Autowired
    private ISecretariatRepository secretariatRepository;
    @Override
    public void createSecretariat(Secretariat secretariat) {
        secretariatRepository.save(secretariat);
    }

    @Override
    public List<Secretariat> getSecretariats() {
        List<Secretariat> secretariats = secretariatRepository.findAll();
        return secretariats;
    }

    @Override
    public Secretariat findSecretariatById(Long idSecretariat) {
        Secretariat secretariatFound = secretariatRepository.findById(idSecretariat)
                .orElseThrow(()->new EntityNotFoundException("Secretatiat not found"));

        return secretariatFound;
    }

    @Override
    public void deleteSecretariatById(Long idSecretariat) {
        if (!secretariatRepository.existsById(idSecretariat)){
            throw new EntityNotFoundException("Secretatiat not found");
        }
        secretariatRepository.deleteById(idSecretariat);
    }

    @Override
    public Secretariat editSecretariat(Secretariat secretariat) {
        Secretariat secretariatFound = this.findSecretariatById(secretariat.getIdPerson());

        secretariatFound.setDni(secretariat.getDni());
        secretariatFound.setName(secretariat.getName());
        secretariatFound.setLastname(secretariat.getLastname());
        secretariatFound.setNumberPhone(secretariat.getNumberPhone());
        secretariatFound.setAddress(secretariat.getAddress());
        secretariatFound.setDateOfBirth(secretariat.getDateOfBirth());
        secretariatFound.setSector(secretariat.getSector());
        secretariatFound.setSecretariatUser(secretariat.getSecretariatUser());
        this.createSecretariat(secretariatFound);
        return secretariatFound;
    }
}