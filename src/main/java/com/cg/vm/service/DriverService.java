package com.cg.vm.service;

import org.springframework.stereotype.Component;

import com.cg.vm.bean.Driver;
import com.cg.vm.exceptions.DriverNotFoundException;

@Component
public interface DriverService {
	public Driver saveorUpdateDriver(Driver driver) throws DriverNotFoundException;
	public Driver findDriverByFirstName(String firstName) throws DriverNotFoundException;
	public Driver findDriverByLastName(String lastName) throws DriverNotFoundException;
	public Driver deleteDriverByDriverId(int driverId) throws DriverNotFoundException;
	public Driver deleteDriverById(String licenseNo) throws DriverNotFoundException;
	public Iterable<Driver> getAllDrivers()throws DriverNotFoundException;

}