/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cameldemo;

import java.util.Map;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.UriEndpointComponent;

/**
 *
 * @author Markus
 */
public class AllergiesComponent extends UriEndpointComponent
{

    public AllergiesComponent() 
    {
        super(AllergiesEndpoint.class);
    }
    
    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> map) throws Exception 
    {
        AllergiesEndpoint endpoint = new AllergiesEndpoint(uri, remaining, this);
        AllergiesConfiguration configuration = new AllergiesConfiguration();

        // use the built-in setProperties method to clean the camel parameters map
        setProperties(configuration, map);

        endpoint.setConfiguration(configuration);
        return endpoint;
    }
}
