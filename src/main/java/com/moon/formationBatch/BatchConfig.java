package com.moon.formationBatch;

import com.moon.formationBatch.validators.MyJobParametersValidators;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Bean
    public JobParametersValidator defaultJobParametersValidator(){
        DefaultJobParametersValidator bean = new DefaultJobParametersValidator();
        bean.setRequiredKeys(new String[]{"formateursFile","formationsFile","seancesFile"});
        bean.setOptionalKeys(new String[]{"run.id"});
        return bean;
    }


    @Bean
    public JobParametersValidator myJobParametersValidator(){
        return new MyJobParametersValidators();
    }

    //on se retrouve avec 2 JobParametersValidator, par contre il faut les composes dans un composite
    // car on en peut passer qu'un seul type de validator dans le Job.
    @Bean
    public JobParametersValidator compositeJobParametersValidator(){
        CompositeJobParametersValidator bean = new CompositeJobParametersValidator();
        bean.setValidators(Arrays.asList(defaultJobParametersValidator(),myJobParametersValidator()));
        return bean;
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory){
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("hello moon");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Job job(final JobBuilderFactory jobBuilderFactory){
        return jobBuilderFactory.get("formation-batch")
                .start(step1(null))
                .validator(compositeJobParametersValidator())
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
