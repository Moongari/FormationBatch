package com.moon.formationBatch.validators;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.util.StringUtils;

public class MyJobParametersValidators implements JobParametersValidator {



    @Override
    public void validate(JobParameters jobParameters) throws JobParametersInvalidException {
        //nous allons verifier les extensions des fichiers

        if(StringUtils.endsWithIgnoreCase(jobParameters.getString("formateursFile"),"csv")){
            throw new JobParametersInvalidException("le fichier des formateurs doit etre au format csv");
        }

        if(StringUtils.endsWithIgnoreCase(jobParameters.getString("formationsFile"),"xml")){
            throw new JobParametersInvalidException("le fichier des formations doit etre au format csv");
        }
        if(StringUtils.endsWithIgnoreCase(jobParameters.getString("seancesFile"),"csv")){
            throw new JobParametersInvalidException("le fichier des seances doit etre au format csv");
        }else if(StringUtils.endsWithIgnoreCase(jobParameters.getString("seancesFile"),"txt")){
            throw new JobParametersInvalidException("le fichier des seances doit etre au format txt");
        }

    }
}
