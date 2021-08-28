package com.moon.formationBatch.listeners;

import com.moon.formationBatch.domaine.Formateur;
import com.moon.formationBatch.domaine.Formation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.listener.StepListenerSupport;


// permet de tracer le nombre de formateurs traité par la Step
public class ChargementFormationStepListener extends StepListenerSupport<Formation,Formation> implements StepExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChargementFormationStepListener.class);

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
    LOGGER.info("Chargement des formations:{} formation(s) enregistré(s)",stepExecution.getReadCount());
        return stepExecution.getExitStatus();
    }
}
