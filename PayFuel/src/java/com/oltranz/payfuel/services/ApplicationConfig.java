/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.services;

import java.util.Set;

import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("*")
public class ApplicationConfig  extends Application{
    @Override
    public Set<Class<?>> getClasses(){
        Set<Class<?>> resources = new java.util.HashSet<>();
        addResourceClasses(resources);
        return resources;
        
    }
    
  private void addResourceClasses(Set<Class<?>> resourses)  {
      
      resourses.add(com.oltranz.payfuel.services.AndroidWebService.class);
      resourses.add(com.oltranz.payfuel.services.CompanyManagementService.class);
      resourses.add(com.oltranz.payfuel.services.BranchManagementService.class);
      resourses.add(com.oltranz.payfuel.services.PumpManagementService.class);
      resourses.add(com.oltranz.payfuel.services.DeviceManagementService.class);
      resourses.add(com.oltranz.payfuel.services.ProductManagementService.class);
      resourses.add(com.oltranz.payfuel.services.PaymentModeManagementService.class);
      resourses.add(com.oltranz.payfuel.services.UserManagementService.class);
      resourses.add(com.oltranz.payfuel.services.SystemInitialiseService.class);
      resourses.add(com.oltranz.payfuel.services.NozzleManagementService.class);
      resourses.add(com.oltranz.payfuel.services.CustomerManagementService.class);
      resourses.add(com.oltranz.payfuel.services.VoucherManagementService.class);
      resourses.add(com.oltranz.payfuel.services.TransactionManagementService.class);
      resourses.add(com.oltranz.payfuel.services.LogManagementService.class);
      resourses.add(com.oltranz.payfuel.services.TankManagementService.class);
      resourses.add(com.oltranz.payfuel.services.ActionManagementService.class);
      
      
  }
    
}