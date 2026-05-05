package org.ncapas.jujustsuapi.service;

import org.ncapas.jujustsuapi.model.Sorcerer;
import org.ncapas.jujustsuapi.repository.SorcererRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SorcererService {
    @Autowired
    private SorcererRepository sorcererRepository;

    //Crear un hechicero
    public Sorcerer save(Sorcerer sorcerer){
        return sorcererRepository.save(sorcerer);
    }

    //Lista de todos los hechiceros
    public List<Sorcerer> findAll() {
        return sorcererRepository.findAll();
    }

    //Encontrar por id
    public Sorcerer findById(UUID id) {
        return sorcererRepository.findById(id).get();
    }

    //Actualizar por id
    public Sorcerer update(UUID id, Sorcerer newSorcerer){
        Sorcerer sorcerer = sorcererRepository.findById(id).get();
        sorcerer.setName(newSorcerer.getName());
        sorcerer.setGrade(newSorcerer.getGrade());
        sorcerer.setCursedTechnique(newSorcerer.getCursedTechnique());
        sorcerer.setSchool(newSorcerer.getSchool());
        sorcerer.setCursedEnergy(newSorcerer.getCursedEnergy());
        sorcerer.setIsActive(newSorcerer.getIsActive());
        return sorcererRepository.save(sorcerer);
    }

    //Eliminar por id
    public void delete(UUID id){
        sorcererRepository.deleteById(id);
    }

    //Encontrar por school
    public List<Sorcerer> findBySchool(String school){
        return sorcererRepository.findBySchoolIgnoreCase(school);
    }

    //Encontrar por grade
    public List<Sorcerer> findByGrade(String grade) {
        return sorcererRepository.findByGradeIgnoreCase(grade);
    }

    //Enconrar hechiceros activos
    public List<Sorcerer> findByIsActive(){
        return sorcererRepository.findByIsActiveTrue();
    }
}
