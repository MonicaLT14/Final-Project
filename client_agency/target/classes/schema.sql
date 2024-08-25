DROP TABLE IF EXISTS agency_provider;
DROP TABLE IF EXISTS provider;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS agency;

CREATE TABLE agency (
    agency_id INT NOT NULL AUTO_INCREMENT,
    agency_name VARCHAR(256),
    agency_address VARCHAR(128), 
    agency_city VARCHAR(60),
    agency_state VARCHAR(40),
    agency_zip VARCHAR(20),
    agency_phone VARCHAR(30),
    PRIMARY KEY (agency_id)
);

CREATE TABLE client (
    client_id INT NOT NULL AUTO_INCREMENT, 
    client_first_name VARCHAR(60), 
    client_last_name VARCHAR(60), 
    client_email VARCHAR(380),
    client_room_number VARCHAR(60),
    PRIMARY KEY (client_id)
);

CREATE TABLE provider (
    provider_id INT NOT NULL AUTO_INCREMENT,
    agency_id INT,
    provider_first_name VARCHAR(128),
    provider_last_name VARCHAR(128),
    provider_phone VARCHAR(60),
    provider_service VARCHAR(380),
    PRIMARY KEY (provider_id),
    FOREIGN KEY (agency_id) REFERENCES agency(agency_id) ON DELETE CASCADE
);

CREATE TABLE agency_provider (
    agency_id INT,
    provider_id INT, 
    FOREIGN KEY (agency_id) REFERENCES agency(agency_id) ON DELETE CASCADE,
    FOREIGN KEY (provider_id) REFERENCES provider(provider_id) ON DELETE CASCADE
);
