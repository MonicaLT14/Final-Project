package client.agency.controller.model;

import client.agency.entity.Provider;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AgencyProvider {
	private Long providerId;
	private String providerFirstName;
	private String providerLastName;
	private String providerPhone;
	private String providerService;

	public AgencyProvider(Provider provider) {
		providerId = provider.getProviderId();
		providerFirstName = provider.getProviderFirstName();
		providerLastName = provider.getProviderLastName();
		providerPhone = provider.getProviderPhone();
		providerService = provider.getProviderService();
	}

}
