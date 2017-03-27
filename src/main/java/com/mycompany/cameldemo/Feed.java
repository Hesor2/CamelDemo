/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cameldemo;

public class Feed
{
    private int allergyId;
    private String allergyName;
    private String allergyDescription;

    public int getAllergyId() {
        return allergyId;
    }

    public void setAllergyId(int allergyId) {
        this.allergyId = allergyId;
    }

    public String getAllergyName() {
        return allergyName;
    }

    public void setAllergyName(String allergyName) {
        this.allergyName = allergyName;
    }

    public String getAllergyDescription() {
        return allergyDescription;
    }

    public void setAllergyDescription(String allergyDescription) {
        this.allergyDescription = allergyDescription;
    }
    
    @Override
    public String toString()
    {
        return '\n' + "ID: " + allergyId + '\n' + "Name: " + allergyName + '\n' + "Description: " + allergyDescription;
    }
}
