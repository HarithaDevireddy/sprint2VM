package com.cg.vm.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.vm.domain.Vehicle;
import com.cg.vm.service.VehicleService;
import com.cg.vm.serviceimpl.MapValidationErrorService;

@RestController
@RequestMapping("api/vehiclemanagement/vehicle")
public class VehicleController {
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	
	@PostMapping("/vehicle/create")
	public ResponseEntity<?>createNewVehicle( @Valid @RequestBody Vehicle vehicle,BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result); 
		if(errorMap!=null) return errorMap;
		 Vehicle veh =vehicleService.saveOrUpdate(vehicle);
		return new ResponseEntity<Vehicle>(veh,HttpStatus.OK);
		
	}
	@GetMapping("/all")
	public Iterable<Vehicle> getAllVehicles(){
		return vehicleService.getAllVehicles();
	}
	
	@DeleteMapping("/{VehicleId}")
	public ResponseEntity<?> deleteVehicleId(@PathVariable Long vehicleId) {
		vehicleService.deleteVehicleById(vehicleId);
		return new ResponseEntity<String>("Vehicle with id : "+vehicleId+" deleted successfully.",HttpStatus.OK);
	}
	    }