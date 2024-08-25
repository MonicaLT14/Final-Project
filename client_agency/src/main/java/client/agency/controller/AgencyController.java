package client.agency.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import client.agency.controller.model.AgencyClient;
import client.agency.controller.model.AgencyData;
import client.agency.controller.model.AgencyProvider;
import client.agency.service.AgencyService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/agency")
@Slf4j
public class AgencyController {
	@Autowired
	private AgencyService agencyService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public AgencyData insertAgency(@RequestBody AgencyData agencyData) {
		log.info("Creating Agency {}", agencyData);
		return agencyService.saveAgency(agencyData);
	}

	@PutMapping("/{agencyId}")
	public AgencyData updateAgency(@PathVariable Long agencyId, @RequestBody AgencyData agencyData) {
		agencyData.setAgencyId(agencyId);
		log.info("Updating agency {}", agencyData);
		return agencyService.saveAgency(agencyData);
	}

	@GetMapping
	public List<AgencyData> retrieveAllAgencies() {
		log.info("Retrieve all agencies");
		return agencyService.retrieveAllAgencies();
	}

	@GetMapping("/{agencyId}")
	public AgencyData retrieveAgencyById(@PathVariable Long agencyId) {
		log.info("Retrieving agency with ID={}", agencyId);
		return agencyService.retrieveAgencyById(agencyId);
	}

	@DeleteMapping
	public void deleteAllAgencies() {
		log.info("Attempting to delete all agencies");
		throw new UnsupportedOperationException("Deleting all agencies is not allowed.");
	}

	@DeleteMapping("{agencyId}")
	public Map<String, String> deleteAgencyById(@PathVariable Long agencyId) {
		log.info("Deleting agency with ID={}", agencyId);
		agencyService.deleteAgencyById(agencyId);
		return Map.of("message", "Agency with ID=" + agencyId + " deleted.");
	}

	@PostMapping("/{agencyId}/provider")
	@ResponseStatus(code = HttpStatus.CREATED)
	public AgencyProvider addProviderToAgency(@PathVariable Long agencyId, @RequestBody AgencyProvider agencyProvider) {
		log.info("Adding Provider {} for agency with ID={}", agencyProvider, agencyId);
		return agencyService.saveProvider(agencyId, agencyProvider);
	}

	@PostMapping("/{agencyId}/client")
	@ResponseStatus(code = HttpStatus.CREATED)
	public AgencyClient addClientToAgency(@PathVariable Long agencyId, @RequestBody AgencyClient agencyClient) {
		log.info("Adding client {} for agency with ID={}", agencyClient, agencyId);
		return agencyService.saveClient(agencyId, agencyClient);
	}
}
