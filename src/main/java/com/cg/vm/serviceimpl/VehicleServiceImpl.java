package com.cg.vm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.vm.bean.Driver;
import com.cg.vm.bean.Vehicle;
import com.cg.vm.exceptions.DriverNotFoundException;
import com.cg.vm.exceptions.VehicleNotFoundException;
import com.cg.vm.repository.DriverRepository;
import com.cg.vm.repository.VehicleRepository;
import com.cg.vm.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {
	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private DriverRepository driverRepository;

	public Vehicle saveOrUpdate(Vehicle vehicle) {
		if (vehicle.getDriver().getDriverId() == 0) {
			vehicle.setDriver(null);
		}
		Driver driver = driverRepository.findByDriverId(vehicle.getDriver().getDriverId());
		if (driver == null)
			throw new DriverNotFoundException("Driver not found in database");
		else
			return vehicleRepository.save(vehicle);

	}

	public Vehicle findByVehicleId(Long vehicleId) {
		Vehicle vehicle = vehicleRepository.findByVehicleId(vehicleId);
		if (vehicle == null)
			throw new VehicleNotFoundException("Vehicle Id " + vehicleId + " Not In Database");
		else
			return vehicle;

	}

	public Iterable<Vehicle> getAllVehicles() {
		return vehicleRepository.findAll();
	}

	public Vehicle deleteVehicleById(Long vehicleId) {
		Vehicle vehicle = vehicleRepository.findByVehicleId(vehicleId);
		if (vehicle == null)
			throw new VehicleNotFoundException("Vehicle Id " + vehicleId + " Not In Database");
		else
			vehicleRepository.delete(vehicle);
		return null;
	}

	@Override
	public List<Vehicle> viewVehicleByLocation(String location) {
		List<Vehicle> vehicles = vehicleRepository.findByAddressContaining(location);
		if (vehicles.size() == 0)
			throw new VehicleNotFoundException("Vehicles from " + location + " Not Found");
		else
			return vehicles;
	}
}