/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zer0time.pfebackend;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 * @author saad
 */
public class SpringApplicationContext implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
     context = ac;
    }
    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }
    

    
    
}
