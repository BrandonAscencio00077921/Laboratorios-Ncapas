package org.ncapas.jujustsuapi.repository;

import org.ncapas.jujustsuapi.model.Sorcerer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SorcererRepository extends JpaRepository<Sorcerer, UUID> {

    List<Sorcerer> findBySchoolIgnoreCase(String school);

    List<Sorcerer> findByGradeIgnoreCase(String grade);

    List<Sorcerer> findByIsActiveTrue();

}
