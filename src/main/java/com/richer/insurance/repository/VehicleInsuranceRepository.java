package com.richer.insurance.repository;

import org.springframework.data.repository.CrudRepository;

import com.richer.insurance.model.VehicleInsurance;

public interface VehicleInsuranceRepository extends CrudRepository<VehicleInsurance, String> {
}
