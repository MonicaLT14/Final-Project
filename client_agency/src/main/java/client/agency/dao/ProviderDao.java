package client.agency.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import client.agency.entity.Provider;

public interface ProviderDao extends JpaRepository<Provider, Long> {

}
