package client.agency.controller.model;

import java.util.Set;

import client.agency.entity.Agency;
import client.agency.entity.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AgencyClient {
	private Long clientId;
	private String clientFirstName;
	private String clientLastName;
	private String clientEmail;
	private String clientRoomNumber;
	private static ClientAgency agency;

	public AgencyClient(Client client) {
		clientId = client.getClientId();
		clientFirstName = client.getClientFirstName();
		clientLastName = client.getClientLastName();
		clientEmail = client.getClientEmail();
	}

	@Data
	@NoArgsConstructor
	public static class ClientAgency {
		private Long agencyId;
		private String agencyName;
		private String agencyAddress;
		private String agencyCity;
		private String agencyState;
		private String agencyZip;
		private String agencyPhone;

		public ClientAgency(Set<Agency> agencys) {
			agencyId = agency.getAgencyId();
			agencyName = agency.getAgencyName();
			agencyAddress = agency.getAgencyAddress();
			agencyCity = agency.getAgencyCity();
			agencyState = agency.getAgencyState();
			agencyZip = agency.getAgencyZip();
			agencyPhone = agency.getAgencyPhone();
		}
	}
}