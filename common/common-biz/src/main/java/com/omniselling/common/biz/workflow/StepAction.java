package com.omniselling.common.biz.workflow;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class StepAction implements Serializable
{
    private static final long serialVersionUID = 1L;
    private final String currentStep; //Status should inherit from a super enum with value {BEGIN, END}
    private String timeoutActionClass;
    private long timeoutSec;
    private String actionClass; //should be the fully qualified name of an implementation of IWorkflowExternalAction interface    
    private Integer priority;
    private String poolId;
    private Map<Serializable, String> nextStepMap; //object should be the response from action;

    private List<String> subSteps; //current step will be finished only if all the substeps are finished
    private String parentStep;
    private List<String> expectedResults;

    public StepAction(String step, String actionClass)
    {
        super();
        this.currentStep = step;
        this.actionClass = actionClass;
    }

    public boolean hasActionClass()
    {
        return !StringUtils.isBlank(this.getActionClass());
    }
    public boolean hasSubSteps()
    {
        List<String> steps = this.getSubSteps();
        if (steps == null || steps.size() == 0)
            return false;
        return true;
    }

    public String getActionClass()
    {
        return actionClass;
    }

    public void setActionClass(String actionClass)
    {
        this.actionClass = actionClass;
    }

    public Map<Serializable, String> getNextStepMap()
    {
        return nextStepMap;
    }

    public void setNextStepMap(Map<Serializable, String> nextStep)
    {
        this.nextStepMap = nextStep;
    }

    public String getCurrentStep()
    {
        return currentStep;
    }

    public long getTimeoutSec()
    {
        return timeoutSec;
    }

    public void setTimeoutSec(long timeoutSec)
    {
        this.timeoutSec = timeoutSec;
    }

    public String getTimeoutActionClass()
    {
        return timeoutActionClass;
    }

    public void setTimeoutActionClass(String timeoutActionClass)
    {
        this.timeoutActionClass = timeoutActionClass;
    }

    public Integer getPriority()
    {
        return priority;
    }

    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }

    public String getPoolId()
    {
        return poolId;
    }

    public void setPoolId(String poolId)
    {
        this.poolId = poolId;
    }

    public List<String> getSubSteps()
    {
        return subSteps;
    }

    public void setSubSteps(List<String> subSteps)
    {
        this.subSteps = subSteps;
    }

    public String getParentStep()
    {
        return parentStep;
    }

    public void setParentStep(String parentStep)
    {
        this.parentStep = parentStep;
    }

    public List<String> getExpectedResults()
    {
        return expectedResults;
    }

    public void setExpectedResults(List<String> expectedResults)
    {
        this.expectedResults = expectedResults;
    }

}
