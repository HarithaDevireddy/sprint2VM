package com.cg.vm.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.vm.bean.Driver;
import com.cg.vm.exceptions.DriverNotFoundException;
import com.cg.vm.serviceimpl.DriverServiceImpl;

@RestController
@RequestMapping("/api/vehiclemanagement")
public class DriverController {
	@Autowired
   private DriverServiceImpl driverService;
	
	@PostMapping("/driver")
	public ResponseEntity<Driver>createNewDriver(@RequestBody Driver driver)throws DriverNotFoundException{
		Driver dvr=driverService.saveorUpdateDriver(driver);
		return new ResponseEntity<Driver>(dvr,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/driver/delete/{licenseNo}")
	public ResponseEntity<String> deleteDriver(@PathVariable String licenseNo)throws DriverNotFoundException
	{
		driverService.deleteDriverById(licenseNo);
		return new ResponseEntity<String>("Deleted successfully by licenseNo",HttpStatus.OK);
	}
	
	@DeleteMapping("/driver/delete1/{driverId}")
	public ResponseEntity<String> deleteDriver(@PathVariable int driverId)throws DriverNotFoundException
	{
		driverService.deleteDriverByDriverId(driverId);
		return new ResponseEntity<String>("Deleted successfully by Driver Id",HttpStatus.OK);
	}
	@GetMapping("/driver/get/all")
	public Iterable<Driver> getAllDrivers()throws DriverNotFoundException{
		return driverService.getAllDrivers();
	}
	
	@GetMapping("/driver/fname/{firstName}")
	public ResponseEntity<?> getDriverByFirstName(@PathVariable String firstName) throws DriverNotFoundException{
		Driver dvr = driverService.findDriverByFirstName(firstName);
		return new ResponseEntity<Driver>(dvr, HttpStatus.OK);
	}

	@GetMapping("/driver/lname/{lastName}")
	public ResponseEntity<?> getDriverByLastName(@PathVariable String lastName) throws DriverNotFoundException{
		Driver dvr = driverService.findDriverByLastName(lastName);
		return new ResponseEntity<Driver>(dvr, HttpStatus.OK);
	}
	
}