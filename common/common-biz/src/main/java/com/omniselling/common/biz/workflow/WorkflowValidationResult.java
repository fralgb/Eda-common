package com.omniselling.common.biz.workflow;

public class WorkflowValidationResult
{
    private boolean pass;
    private String message;
    private String graph;

    public boolean isPass()
    {
        return pass;
    }

    public void setPass(boolean pass)
    {
        this.pass = pass;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getGraph()
    {
        return graph;
    }

    public void setGraph(String graph)
    {
        this.graph = graph;
    }

}
