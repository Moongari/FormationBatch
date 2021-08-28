package com.moon.formationBatch;

import com.moon.formationBatch.domaine.Formation;
import com.moon.formationBatch.listeners.ChargementFormateurStepListener;
import com.moon.formationBatch.listeners.ChargementFormationStepListener;
import com.moon.formationBatch.mappers.FormateurItemPreparedStatementSetter;
import com.moon.formationBatch.mappers.FormationItemPreparedStatementSetter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.sql.DataSource;

@Configuration
public class ChargementFormationsStepConfig {

    @Bean
    @StepScope
    public StaxEventItemReader<Formation> formationStaxEventItemReader(@Value("${formationsfile}") final Resource resource){
        return new StaxEventItemReaderBuilder<Formation>().name("FormationItemReader")
                .resource(resource)
                .addFragmentRootElements("formation")
                .unmarshaller(formationMarshaller())
                .build();

    }


    @Bean // permet de transformer un fichier xml en objet Java
    public Jaxb2Marshaller formationMarshaller(){
        Jaxb2Marshaller bean = new Jaxb2Marshaller();
        bean.setClassesToBeBound(Formation.class);
        return bean;
    }

    @Bean
    public JdbcBatchItemWriter<Formation> formationItemWriter(final DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Formation>()
                .dataSource(dataSource)
                .sql(FormationItemPreparedStatementSetter.FORMATIONS_INSERT_QUERY)
                .itemPreparedStatementSetter(new FormationItemPreparedStatementSetter())
                .build();

    }



    @Bean
    public Step chargementFormationStep(final StepBuilderFactory stepBuilderFactory){
        return stepBuilderFactory.get("chargementFormationStep")
                .<Formation,Formation>chunk(10)
                .reader(formationStaxEventItemReader(null))
                .writer(formationItemWriter(null))
                .listener(chargementFormationListener())
                .build();

    }
    @Bean
    public StepExecutionListener chargementFormationListener(){
        return new ChargementFormationStepListener();
    }

}
