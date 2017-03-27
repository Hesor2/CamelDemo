package com.mycompany.cameldemo;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultPollingEndpoint;
import org.apache.camel.spi.UriEndpoint;

@UriEndpoint(scheme= "allergies", title= "Allergies", syntax= "allergies://operationPath", consumerOnly= true, consumerClass= AllergiesConsumer.class, label= "allergies")
public class AllergiesEndpoint extends DefaultPollingEndpoint 
{

    private String operationPath;
    private AllergiesConfiguration configuration;
    
    public AllergiesEndpoint(String uri, String operationPath, AllergiesComponent component) 
    {
        super(uri, component);
        this.operationPath = operationPath;
    }

    @Override
    public Producer createProducer() throws Exception 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Consumer createConsumer(Processor processor) throws Exception 
    {
        AllergiesConsumer consumer = new AllergiesConsumer(this, processor);
        return consumer;
    }

    @Override
    public boolean isSingleton() 
    {
        return true;
    }
    
    public String getOperationPath() {
        return operationPath;
    }

    public void setOperationPath(String operationPath) 
    {
        this.operationPath = operationPath;
    }

    public AllergiesConfiguration getConfiguration() 
    {
        return configuration;
    }

    public void setConfiguration(AllergiesConfiguration configuration) 
    {
        this.configuration = configuration;
    }

}
