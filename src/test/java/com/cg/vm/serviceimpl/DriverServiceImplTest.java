package com.cg.vm.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.vm.bean.Driver;
import com.cg.vm.exceptions.DriverNotFoundException;
import com.cg.vm.repository.DriverRepository;
import com.cg.vm.service.DriverService;

@RunWith(SpringRunner.class)
@SpringBootTest
class DriverServiceImplTest {

	@Autowired
	DriverService driverService;

	@MockBean
	DriverRepository driverRepository;

	@Test
	public void testAddDriver() {
		Driver driver = new Driver(1, "Arun", "cibi", "pollachi", "9080664252", "arun@gmail.com", "L123");
		when(driverRepository.save(driver)).thenReturn(driver);
		Driver result = driverService.saveorUpdateDriver(driver);
		assertEquals(result.getDriverId(), driver.getDriverId());
	}

	@Test
	public void testFindAllDrivers() {
		Driver d1 = new Driver(1, "Arun", "cibi", "pollachi", "9080664252", "arun@gmail.com", "L123");
		Driver d2 = new Driver(2, "shyam", "kp", "coimbatore", "9066664252", "shyam@gmail.com", "L234");
		when(driverRepository.findAll()).thenReturn(Stream.of(d1, d2).collect(Collectors.toList()));
		assertEquals(2, ((List<Driver>) driverService.getAllDrivers()).size());
	}

	@Test
	public void testAddDriverForDuplicates() {
		Driver driver1 = new Driver(1, "Arun", "cibi", "pollachi", "9080664252", "arun@gmail.com", "L123");
		when(driverRepository.save(driver1)).thenThrow(new DriverNotFoundException("Content not found in DataBase"));
		assertThrows(DriverNotFoundException.class, () -> driverService.saveorUpdateDriver(driver1));
	}

	@Test
	public void testFindAllDriversforDriverNotFoundException() {
		@SuppressWarnings("unused")
		Driver driver1 = new Driver(1, "Arun", "cibi", "pollachi", "9080664252", "arun@gmail.com", "L123");
		when(driverRepository.findAll()).thenThrow(new DriverNotFoundException("No drivers found"));
		assertThrows(DriverNotFoundException.class, () -> driverService.getAllDrivers());
	}

	@Test
	public void testdeleteDriverByDriverId() {
		Driver driver = new Driver(1, "Arun", "cibi", "pollachi", "9080664252", "arun@gmail.com", "L123");
		when(driverRepository.findByDriverId(1)).thenReturn(driver);
		Driver dvr = driverService.deleteDriverByDriverId(1);
		assertNull(dvr);

	}

	@Test
	public void testfindDriverByDriverFirstName() {
		Driver driver = new Driver(1, "Arun", "cibi", "pollachi", "9080664252", "arun@gmail.com", "L123");
		when(driverRepository.findByFirstName("Arun")).thenReturn(driver);
		Driver dvr = driverService.findDriverByFirstName("Arun");
		assertEquals(dvr, driver);

	}

}
