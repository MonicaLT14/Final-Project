package client.agency.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import client.agency.controller.model.AgencyClient;
import client.agency.controller.model.AgencyData;
import client.agency.controller.model.AgencyProvider;
import client.agency.dao.AgencyDao;
import client.agency.dao.ClientDao;
import client.agency.dao.ProviderDao;
import client.agency.entity.Agency;
import client.agency.entity.Client;
import client.agency.entity.Provider;

@Service
public class AgencyService {

	@Autowired
	private AgencyDao agencyDao;

	@Autowired
	private ClientDao clientDao;

	@Autowired
	private ProviderDao providerDao;

	@Transactional(readOnly = false)
	public AgencyData saveAgency(AgencyData agencyData) {
		Long agencyId = agencyData.getAgencyId();
		Agency agency = findOrCreateAgency(agencyId);

		copyAgencyFields(agency, agencyData);
		return new AgencyData(agencyDao.save(agency));
	}

	private void copyAgencyFields(Agency agency, AgencyData agencyData) {
		agency.setAgencyId(agencyData.getAgencyId());
		agency.setAgencyName(agencyData.getAgencyName());
		agency.setAgencyAddress(agencyData.getAgencyAddress());
		agency.setAgencyCity(agencyData.getAgencyCity());
		agency.setAgencyState(agencyData.getAgencyState());
		agency.setAgencyZip(agencyData.getAgencyZip());
		agency.setAgencyPhone(agencyData.getAgencyPhone());
	}

	private Agency findOrCreateAgency(Long agencyId) {
		if (Objects.isNull(agencyId)) {
			return new Agency();
		} else {
			return findAgencyById(agencyId);
		}
	}

	private Agency findAgencyById(Long agencyId) {
		return agencyDao.findById(agencyId)
				.orElseThrow(() -> new NoSuchElementException("Agency with ID=" + agencyId + " was not found."));
	}

	@Transactional(readOnly = true)
	public List<AgencyData> retrieveAllAgencies() {
		List<Agency> agencies = agencyDao.findAll();

		List<AgencyData> result = new LinkedList<>();

		for (Agency agency : agencies) {
			AgencyData agencyData = new AgencyData(agency);
			// agencyData.getClients().clear();
			// agencyData.getProviders().clear();
			result.add(agencyData);
		}
		return result;
	}

	@Transactional(readOnly = true)
	public AgencyData retrieveAgencyById(Long agencyId) {
		return new AgencyData(findAgencyById(agencyId));
	}

	@Transactional(readOnly = false)
	public void deleteAgencyById(Long agencyId) {
		Agency agency = findAgencyById(agencyId);
		agencyDao.delete(agency);
	}

	private void copyProviderFields(Provider provider, AgencyProvider agencyProvider) {
		provider.setProviderFirstName(agencyProvider.getProviderFirstName());
		provider.setProviderLastName(agencyProvider.getProviderLastName());
		provider.setProviderId(agencyProvider.getProviderId());
		provider.setProviderService(agencyProvider.getProviderService());
		provider.setProviderPhone(agencyProvider.getProviderPhone());
	}

	private void copyClientFields(Client client, AgencyClient agencyClient) {
		client.setClientFirstName(agencyClient.getClientFirstName());
		client.setClientLastName(agencyClient.getClientLastName());
		client.setClientEmail(agencyClient.getClientEmail());
		client.setClientId(agencyClient.getClientId());
		client.setClientRoomNumber(agencyClient.getClientRoomNumber());
	}

	@Transactional(readOnly = false)
	public AgencyProvider saveProvider(Long agencyId, AgencyProvider agencyProvider) {
		Agency agency = findAgencyById(agencyId);
		Long providerId = agencyProvider.getProviderId();
		Provider provider = findOrCreateProvider(providerId);

		copyProviderFields(provider, agencyProvider);

		provider.getAgencies().add(agency);
		agency.getProviders().add(provider);

		Provider dbProvider = providerDao.save(provider);
		return new AgencyProvider(dbProvider);
	}

	@Transactional(readOnly = false)
	public AgencyClient saveClient(Long agencyId, AgencyClient agencyClient) {
		Agency agency = findAgencyById(agencyId);
		Long clientId = agencyClient.getClientId();
		Client client = findOrCreateClient(clientId);

		copyClientFields(client, agencyClient);

		client.setAgency(agency);
		agency.getClients().add(client);

		Client dbClient = clientDao.save(client);
		return new AgencyClient(dbClient);
	}

	private Provider findOrCreateProvider(Long providerId) {
		if (Objects.isNull(providerId)) {
			return new Provider();
		}
		return findProviderById(providerId);
	}

	private Client findOrCreateClient(Long clientId) {
		if (Objects.isNull(clientId)) {
			return new Client();
		}
		return findClientById(clientId);
	}

	private Provider findProviderById(Long providerId) {
		Provider provider = providerDao.findById(providerId)
				.orElseThrow(() -> new NoSuchElementException("Provider with ID=" + providerId + " was not found."));
		return provider;
	}

	private Client findClientById(Long clientId) {
		Client client = clientDao.findById(clientId)
				.orElseThrow(() -> new NoSuchElementException("Client with ID=" + clientId + " was not found."));
		return client;
	}
}
