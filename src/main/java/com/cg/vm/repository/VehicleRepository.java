package com.cg.vm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



import com.cg.vm.bean.Vehicle;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

	Vehicle findByVehicleId(Long vehicleId);

	List<Vehicle> findByAddressContaining(String location);

	Vehicle viewVehicleByLocation(String string);

}