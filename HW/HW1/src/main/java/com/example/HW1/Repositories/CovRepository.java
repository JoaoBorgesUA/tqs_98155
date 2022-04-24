package com.example.HW1.Repositories;

import java.util.Optional;

import com.example.HW1.Models.CovObject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CovRepository extends CrudRepository<CovObject, Long> {
    Optional<CovObject> findByCountry(String country);

    Optional<CovObject> findByDate(String date);

}