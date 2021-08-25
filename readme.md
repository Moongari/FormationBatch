# SPRING BATCH 
Ce dépôt contient un exemple afin de présenter Spring Batch
 
## Démarrage rapide
Pour tester ce code vous pouvez demarrer votre IDE habituelle est l'integrer dans votre IDE préférer rien de plus...

## Prérequis
Creer un projet maven avec https://start.spring.io/

ajouter les dependances suivantes 

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.5.4</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.moon</groupId>
	<artifactId>formationBatch</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>formationBatch</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>1.8</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-batch</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-freemarker</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-mail</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-oxm</artifactId>

		</dependency>
			<!--JAXB DEPENDENCIES -->
		<dependency>
			<groupId>javax.xml.bind</groupId>
			<artifactId>jaxb-api</artifactId>
			<version>2.2.11</version>
		</dependency>
		<dependency>
			<groupId>com.sun.xml.bind</groupId>
			<artifactId>jaxb-core</artifactId>
			<version>2.2.11</version>
		</dependency>
		<dependency>
			<groupId>com.sun.xml.bind</groupId>
			<artifactId>jaxb-impl</artifactId>
			<version>2.2.11</version>
		</dependency>
		<dependency>
			<groupId>javax.activation</groupId>
			<artifactId>activation</artifactId>
			<version>1.1</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.batch</groupId>
			<artifactId>spring-batch-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>

  * Java 8
 
### SPRING BATCH

creation d'un projet Formation avec Sprinbatch

### Configuration du fichier application.properties
https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.data

### BASE DE DONNEES

DROP TABLE IF EXISTS `seances`;
DROP TABLE IF EXISTS `formations`;
DROP TABLE IF EXISTS `formateurs`;



CREATE TABLE `formateurs` (
  `id` int(11) NOT NULL,
  `nom` varchar(12) NOT NULL,
  `prenom` varchar(12) NOT NULL,
  `adresse_email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `formations` (
  `code` varchar(20) NOT NULL,
  `libelle` varchar(200) NOT NULL,
  `descriptif` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `seances` (
  `code_formation` varchar(20) NOT NULL,
  `id_formateur` int(11) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `formateurs`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `formations`
  ADD PRIMARY KEY (`code`);

ALTER TABLE `seances`
  ADD KEY `code_formation` (`code_formation`,`id_formateur`),
  ADD KEY `FK_FORMATEURS` (`id_formateur`);


ALTER TABLE `seances`
  ADD CONSTRAINT `FK_FORMATEURS` FOREIGN KEY (`id_formateur`) REFERENCES `formateurs` (`id`),
  ADD CONSTRAINT `FK_FORMATIONS` FOREIGN KEY (`code_formation`) REFERENCES `formations` (`code`);

