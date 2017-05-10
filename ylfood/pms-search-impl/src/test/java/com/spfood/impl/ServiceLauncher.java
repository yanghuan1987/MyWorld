/**
 * @copyright 
 */
package com.spfood.impl;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wang yi
 * 
 * @createdTime 2016年12月9日
 */
public class ServiceLauncher {
    public static void main(String[] args) throws IOException{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"spring-context.xml","spring-dubbo.xml","local-spring-environment.xml"});
        context.start();
 
        System.in.read();
        context.close();
    }
}
