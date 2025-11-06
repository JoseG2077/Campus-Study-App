CREATE DATABASE StudyApp;
USE StudyApp;

CREATE TABLE Users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  fullName VARCHAR(15) NOT NULL,
  email VARCHAR(30) NOT NULL,
  username VARCHAR(15) NOT NULL,
  password VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS StudySpaces (
  id INT AUTO_INCREMENT PRIMARY KEY,
  spaceName VARCHAR(100),
  location VARCHAR(100),
  description TEXT,
  photoPath VARCHAR(225),
  indoorsOrOutdoors VARCHAR(20),
  noiseLevel VARCHAR (50)
);


CREATE TABLE Admins (
    adminID INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    fullName VARCHAR(100) NOT NULL
    );
    
INSERT INTO Admins (username, password, fullName)
VALUES 
('admin1', 'adminpass1', 'Jose Gonzalez');

INSERT INTO Users (fullName, email, username, password)
VALUES ('Jose G', 'jose@csustan.edu', 'josegr', 'passw123');

INSERT INTO StudySpaces (spaceName, location, description, photoPath, indoorsOrOutdoors, noiseLevel)
VALUES
		('Library', 'Main Building', 'Quiet, air-conditioned study space.'),
       ('Science Patio', 'Science Building', 'Outdoor tables, shaded.'),
       ('Student Center', 'Commons', 'Good for groups.');
