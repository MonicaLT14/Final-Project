package client.agency.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import client.agency.entity.Agency;

public interface AgencyDao extends JpaRepository<Agency, Long> {

}
