package client.agency.controller.model;

import java.util.HashSet;
import java.util.Set;

import client.agency.entity.Agency;
import client.agency.entity.Client;
import client.agency.entity.Provider;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AgencyData {
	private Long agencyId;
	private String agencyName;
	private String agencyAddress;
	private String agencyCity;
	private String agencyState;
	private String agencyZip;
	private String agencyPhone;

	public Set<AgencyClient> clients = new HashSet<>();
	public Set<AgencyProvider> providers = new HashSet<>();

	public AgencyData(Agency agency) {
		agencyId = agency.getAgencyId();
		agencyName = agency.getAgencyName();
		agencyAddress = agency.getAgencyAddress();
		agencyCity = agency.getAgencyCity();
		agencyState = agency.getAgencyState();
		agencyZip = agency.getAgencyZip();
		agencyPhone = agency.getAgencyPhone();

		for (Client client : agency.getClients()) {
			clients.add(new AgencyClient(client));
		}

		for (Provider provider : agency.getProviders()) {
			providers.add(new AgencyProvider(provider));
		}
	}
}