package client.agency.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clientId;

	private String clientFirstName;
	private String clientLastName;
	private String clientEmail;
	private String clientRoomNumber;

	@ManyToOne
	@JoinColumn(name = "agency_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Agency agency;
}