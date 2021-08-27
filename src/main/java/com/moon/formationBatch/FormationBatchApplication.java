package com.moon.formationBatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootApplication

public class FormationBatchApplication {

	public static void main(String[] args)  {

		SpringApplication.run(FormationBatchApplication.class, args);



	}

}
