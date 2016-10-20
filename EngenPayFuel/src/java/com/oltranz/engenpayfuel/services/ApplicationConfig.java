/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.services;

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
      
      resourses.add(com.oltranz.engenpayfuel.services.AndroidWebService.class);
      resourses.add(com.oltranz.engenpayfuel.services.CompanyManagementService.class);
      resourses.add(com.oltranz.engenpayfuel.services.BranchManagementService.class);
      resourses.add(com.oltranz.engenpayfuel.services.PumpManagementService.class);
      resourses.add(com.oltranz.engenpayfuel.services.DeviceManagementService.class);
      resourses.add(com.oltranz.engenpayfuel.services.ProductManagementService.class);
      resourses.add(com.oltranz.engenpayfuel.services.PaymentModeManagementService.class);
      resourses.add(com.oltranz.engenpayfuel.services.UserManagementService.class);
      resourses.add(com.oltranz.engenpayfuel.services.SystemInitialiseService.class);
      resourses.add(com.oltranz.engenpayfuel.services.NozzleManagementService.class);
      resourses.add(com.oltranz.engenpayfuel.services.CustomerManagementService.class);
      resourses.add(com.oltranz.engenpayfuel.services.VoucherManagementService.class);
      resourses.add(com.oltranz.engenpayfuel.services.TransactionManagementService.class);
      resourses.add(com.oltranz.engenpayfuel.services.LogManagementService.class);
      resourses.add(com.oltranz.engenpayfuel.services.TankManagementService.class);
      resourses.add(com.oltranz.engenpayfuel.services.ActionManagementService.class);
      resourses.add(com.oltranz.engenpayfuel.services.EmailManagementService.class);
      resourses.add(com.oltranz.engenpayfuel.services.ChartManagementService.class);
      
      
  }
    
}