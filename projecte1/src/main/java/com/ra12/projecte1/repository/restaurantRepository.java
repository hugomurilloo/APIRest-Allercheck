package com.ra12.projecte1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ra12.projecte1.model.restaurantModel;

@Repository
public interface restaurantRepository extends CrudRepository<restaurantModel, Long> {
    // No es necesario implementar métodos básicos, CrudRepository los hace por mi :) :
    // save(), findAll(), findById(), deleteById(), count(), existsById(), etc.
}