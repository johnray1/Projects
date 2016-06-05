/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.IntercityTransport.services;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author manzi
 */
@javax.ws.rs.ApplicationPath("*")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.oltranz.IntercityTransport.services.BusesManagementServices.class);
        resources.add(com.oltranz.IntercityTransport.services.CardsManagementServices.class);
        resources.add(com.oltranz.IntercityTransport.services.ContractsManagementServices.class);
        resources.add(com.oltranz.IntercityTransport.services.MembershipServices.class);
        resources.add(com.oltranz.IntercityTransport.services.SaleItemsManagementServices.class);
        resources.add(com.oltranz.IntercityTransport.services.SaleServices.class);
        resources.add(com.oltranz.IntercityTransport.services.SellingDevicesManagementServices.class);
        resources.add(com.oltranz.IntercityTransport.services.SellingProfilesManagementServices.class);
        resources.add(com.oltranz.IntercityTransport.services.ServicesProvidersManagementServices.class);
        resources.add(com.oltranz.IntercityTransport.services.SystemServices.class);
        resources.add(com.oltranz.IntercityTransport.services.TransportersManagementServices.class);
        resources.add(com.oltranz.IntercityTransport.services.WalletsManagementServices.class);
    }
    
}
