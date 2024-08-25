package client.agency.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import client.agency.entity.Client;

public interface ClientDao extends JpaRepository<Client, Long> {

}
