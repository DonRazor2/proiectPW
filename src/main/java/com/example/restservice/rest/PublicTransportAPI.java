package com.example.restservice.rest;

import com.example.restservice.rest.entity.PublicTransportVehicle;
import com.example.restservice.rest.entity.Route;
import com.example.restservice.rest.controller.PublicTransportController;
import com.example.restservice.rest.controller.RoutesController;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PublicTransportAPI {

	@Autowired
	private PublicTransportController publicTransportController;

	@Autowired
	private RoutesController routesController;

	final Logger logger = LoggerFactory.getLogger(PublicTransportAPI.class);

	@Operation(summary = "Fetches details about a transport based on ID stored in the DB", tags = "Transport API endpoints")
	@GetMapping("/vehicle/{id}")
	public PublicTransportVehicle getVehicleById(@PathVariable(value = "id") final long id) {

		logger.info("/Vehicle was called with id {}", id);
		return publicTransportController.getVehicleById(id);
	}

	@Operation(summary = "Adds or changes a transport based on ID stored in the DB", tags = "Transport API endpoints")
	@PutMapping("/vehicle/{id}")
	public PublicTransportVehicle addVehicleAndReturnId(
			@PathVariable(value = "id") final long id,
			@RequestParam(value = "numberOfSeats", defaultValue = "25") final int numberOfSeats,
			@RequestParam(value = "gasTank", defaultValue = "80") final int gasTank,
			@RequestParam(value = "descriptionOfVehicle", defaultValue = "") final String descriptionOfVehicle) {

		logger.info("/putTransportVehicle was called with id {}", id);
		return publicTransportController.addVehicleAndReturnId(id, numberOfSeats, gasTank, descriptionOfVehicle);
	}

	@Operation(summary = "Deletes a transport based on ID from the DB", tags = "Transport API endpoints")
	@DeleteMapping("/vehicle/{id}")
	public PublicTransportVehicle deleteVehicle(@PathVariable(value = "id") final long id) {


		logger.info("/deleteTransportVehicle was called with id {}", id);
		return null;
	}

	@Operation(summary = "Fetches details about a route based on ID stored in the DB", tags = "Routes API endpoints")
	@GetMapping("/getRoute")
	public Route getRouteById(@RequestParam(value = "id") final long id) {

		logger.info("/getRoute was called with id {}", id);
		return routesController.getRouteById(id);
	}

	@Operation(summary = "Modifies details about a route", tags = "Routes API endpoints")
	@PostMapping("/postRoute")
	public Route modifyRoute(@RequestParam(value = "routeId") final long routeId,
			@RequestParam(value = "vehId") final long vehId) {

		logger.info("/postRoute was called with routeId {} and vehId {}", routeId, vehId);
		return routesController.modifyRoute(routeId, vehId);
	}

	@Operation(summary = "Retrieves information about all the routes", tags = "Routes API endpoints")
	@GetMapping("/routes")
	public Route getAllRoutes() {
		return null;
	}
}
