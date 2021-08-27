package com.moon.formationBatch;

import com.moon.formationBatch.domaine.Formateur;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;


@Configuration
public class ChargementFormateurStepConfig {

    @Bean
    @StepScope
    public FlatFileItemReader<Formateur> FormateurItemReader(@Value("${formateursfile}") Resource inputFile){
        return new FlatFileItemReaderBuilder<Formateur>()
                .name("FormateurItemReader")
                .resource(inputFile)
                .delimited()
                .delimiter(";")
                .names(new String[]{"id","nom","prenom","adresseEmail"})
                .targetType(Formateur.class)
                .build();
    }
    @Bean
    public ItemWriter<Formateur> formateurItemWriter(){
        return (items)-> items.forEach(System.out::println);
    }

    @Bean
    public Step ChargementFormateurStep(StepBuilderFactory stepBuilderFactory){
        return stepBuilderFactory.get("ChargementFormateurStep")
                .<Formateur,Formateur>chunk(5)
                .reader(FormateurItemReader(null))
                .writer(formateurItemWriter())
                .build();


    }

}
