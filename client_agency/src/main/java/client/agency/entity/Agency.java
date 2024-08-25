package client.agency.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Agency {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long agencyId;

	private String agencyName;
	private String agencyAddress;
	private String agencyCity;
	private String agencyState;
	private String agencyZip;
	private String agencyPhone;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "agency_provider", joinColumns = @JoinColumn(name = "agency_id"), inverseJoinColumns = @JoinColumn(name = "provider_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Provider> providers = new HashSet<>();

	@OneToMany(mappedBy = "agency", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Client> clients = new HashSet<>();
}
