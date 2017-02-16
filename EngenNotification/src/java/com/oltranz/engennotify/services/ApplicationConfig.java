/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engennotify.services;

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
       resourses.add(com.oltranz.engennotify.services.SystemInitialiseService.class);
      resourses.add(com.oltranz.engennotify.services.NotificationManagementService.class);
  }
    
}