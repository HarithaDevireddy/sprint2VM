package com.cg.vm.service;

import java.util.List;

import com.cg.vm.bean.Vehicle;

public interface VehicleService {
	public Vehicle saveOrUpdate(Vehicle vehicle);

	public Iterable<Vehicle> getAllVehicles();

	public Vehicle deleteVehicleById(Long vehicleId);

	public Vehicle findByVehicleId(Long id);

	public List<Vehicle> viewVehicleByLocation(String Location);

}
