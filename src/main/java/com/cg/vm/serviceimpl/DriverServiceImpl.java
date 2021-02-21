package com.cg.vm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.vm.bean.Driver;
import com.cg.vm.exceptions.DriverNotFoundException;
import com.cg.vm.repository.DriverRepository;
import com.cg.vm.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepository driverRepository;

	public Driver saveorUpdateDriver(Driver driver) throws DriverNotFoundException {

		try {
			return driverRepository.save(driver);
		} catch (Exception e) {
			throw new DriverNotFoundException("Driver details not added");
		}
	}

	public Driver findDriverByFirstName(String firstName) throws DriverNotFoundException {
		Driver driver = driverRepository.findByFirstName(firstName);
		if (driver == null)
			throw new DriverNotFoundException("Driver First name " + firstName + " Not in database");
		return driver;
	}

	public Driver findDriverByLastName(String lastName) throws DriverNotFoundException {
		Driver driver = driverRepository.findByLastName(lastName);
		if (driver == null)
			throw new DriverNotFoundException("Driver Last name " + lastName + " Not in database");
		return driver;
	}

	public Driver deleteDriverByDriverId(int driverId) throws DriverNotFoundException {

		Driver driver = driverRepository.findByDriverId(driverId);
		if (driver == null)
			throw new DriverNotFoundException("Driver Id " + driverId + " NOt found");
		else
			driverRepository.delete(driver);
		return null;

	}

	public Driver deleteDriverById(String licenseNo) throws DriverNotFoundException {
		Driver driver = driverRepository.findByLicenseNo(licenseNo);
		if (driver == null)
			throw new DriverNotFoundException("Driver ILicense number " + licenseNo + " NOt found");
		else
			driverRepository.delete(driver);
		return null;
	}

	public Iterable<Driver> getAllDrivers() throws DriverNotFoundException {
		Iterable<Driver> driver = driverRepository.findAll();
		if (((List<Driver>) driver).size() == 0)
			throw new DriverNotFoundException("No drivers found in driver table");
		return driver;
	}

}