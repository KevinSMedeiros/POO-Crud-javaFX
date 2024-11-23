CREATE database BDalg3; /* criar novo banco de dados chamado BDalg3 */
USE BDalg3; /* abrir o banco de dados BDalg3 (após criado) */
CREATE TABLE Carros (CarroNomeDono VARCHAR(255), CarroPlaca VARCHAR(255), CarroID INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(CarroID)); /* criar a tabela Carros*/
ALTER TABLE Carros ADD COLUMN CarroModelo VARCHAR(255);
