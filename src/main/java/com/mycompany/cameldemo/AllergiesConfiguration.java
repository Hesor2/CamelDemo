/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cameldemo;

import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;

/**
 *
 * @author Markus
 */
@UriParams
public class AllergiesConfiguration 
{
    @UriParam
    private String query;

    @UriParam(defaultValue = "60000")
    private int delay = 6000;

    public int getDelay() 
    {
        return delay;
    }

    public void setDelay(int delay) 
    {
        this.delay = delay;
    }

    public String getQuery() 
    {
        return query;
    }

    public void setQuery(String query) 
    {
        this.query = query;
    }
}
