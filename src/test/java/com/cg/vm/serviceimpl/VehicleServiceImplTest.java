package com.cg.vm.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.cg.vm.bean.Driver;
import com.cg.vm.bean.Vehicle;

import com.cg.vm.repository.VehicleRepository;
import com.cg.vm.service.VehicleService;

public class VehicleServiceImplTest {
	private Vehicle vehicle1;
	private Vehicle vehicle2;
	private Vehicle vehicle3;
	@Autowired
	VehicleService vehicleService;

	@Mock
	VehicleRepository vehicleRepository;

	@SuppressWarnings("deprecation")
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

	}

	@BeforeEach
	public void setUpMockData() {
		Driver driver = new Driver(1, "Ram", "sharma", "mumbai", "8237776772", "ram@gmail", "GA45789");
		Vehicle vehicle1 = new Vehicle((long) 2, "MH124997", "CAR", "AC", "Maruti", "pune", 5, 12, 2000, driver);
//		Driver driver1 = new Driver(2, "Ramu", "sharma", "goa", "8237776771", "ramu@gmail", "GA45799");
//		Vehicle vehicle2 = new Vehicle((long) 3, "MH129997", "CAR", "AC", "Innova", "pune", 5, 12, 2000, driver1);
//		Driver driver2 = new Driver(3, "Ramya", "sharma", "mumbai", "8237776775", "ramya@gmail", "GA55789");
//		Vehicle vehicle3 = new Vehicle((long) 4, "MH324997", "CAR", "AC", "Van", "pune", 5, 12, 2000, driver2);
	}

	@Test(expected = NullPointerException.class)
	public void testsaveOrUpdate() throws NullPointerException {
		when(vehicleRepository.save(vehicle1)).thenReturn(vehicle1);
		Vehicle vehicle = vehicleService.saveOrUpdate(vehicle1);
		assertEquals(vehicle1, vehicle);
	}

	@Test(expected = NullPointerException.class)
	public void testUpdate() throws NullPointerException {
		Vehicle expected = vehicleService.saveOrUpdate(vehicle1);
		assertEquals(expected, vehicle1);
	}

	@Test(expected = NullPointerException.class)
	public void testdeleteVehicleById() throws NullPointerException {
		long id = 3;
		when(vehicleRepository.findByVehicleId(id)).thenReturn(vehicle2);
		vehicleService.deleteVehicleById(id);
		verify(vehicleRepository, times(1)).deleteById(id);
		assertNull(vehicle2);
	}

	@Test(expected = NullPointerException.class)
	public void testfindVehicleByLocation() {
		when(vehicleRepository.save(vehicle3)).thenReturn(vehicle3);
		when(vehicleRepository.findByAddressContaining("pune"))
				.thenReturn(Stream.of(vehicle3).collect(Collectors.toList()));
		List<Vehicle> result = vehicleService.viewVehicleByLocation("pune");
		assertEquals(1, result.size());
	}

}