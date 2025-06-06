-- Database schema for the project

CREATE DATABASE IF NOT EXISTS fitness_db;
USE fitness_db;

-- Table for utilizatori (users)
CREATE TABLE utilizatori (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nume VARCHAR(255) NOT NULL,
    rol VARCHAR(50),
    varsta INT,
    greutate DOUBLE,
    inaltime DOUBLE
);

-- Table for antrenori (trainers)
CREATE TABLE antrenori (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nume VARCHAR(255) NOT NULL,
    specializare VARCHAR(255)
);

-- Join table for many-to-many relationship between antrenori and utilizatori
CREATE TABLE antrenor_utilizator (
    antrenor_id INT NOT NULL,
    utilizator_id INT NOT NULL,
    PRIMARY KEY (antrenor_id, utilizator_id),
    FOREIGN KEY (antrenor_id) REFERENCES antrenori(id) ON DELETE CASCADE,
    FOREIGN KEY (utilizator_id) REFERENCES utilizatori(id) ON DELETE CASCADE
);

-- Table for sporturi (sports)
CREATE TABLE sporturi (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nume VARCHAR(255) NOT NULL,
    calorii_pe_minut DOUBLE
);

-- Table for antrenamente_speciale (special trainings)
CREATE TABLE antrenamente_speciale (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tip VARCHAR(255),
    dificultate VARCHAR(255),
    durata INT
);

-- Table for obiective (goals)
CREATE TABLE obiective (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tip VARCHAR(255),
    valoare_tinta DOUBLE,
    data_limita DATE
);