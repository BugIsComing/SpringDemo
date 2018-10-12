package com.lc.springdemo.client;

import com.lc.springdemo.model.Bean1;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author lc
 */
public class App {
    public static void main(String[]args){
        /**
         * 这种方式已经过时，一次只能加载一个xml文件
         */
        //Resource resource = new ClassPathResource("appcontext.xml");
        //BeanFactory factory = new XmlBeanFactory(resource);
        /**
         * 加载和xml文件的顺序无关，可以交换bean1.xml 和 bean2.xml的顺序
         * 通过ApplicationContext可以同时加载多个xml
         */
        ApplicationContext factory1=new ClassPathXmlApplicationContext(new String[] {"bean2.xml","bean1.xml"});
        Bean1 bean1 = (Bean1)factory1.getBean("bean1");
        /**
         * 通过文件系统加载XML文件，返回ApplicationContext
         */
        ApplicationContext factory2=new FileSystemXmlApplicationContext("classpath:bean2.xml","classpath:bean1.xml");
        Bean1 bean2 = (Bean1) factory2.getBean("bean1");
        /*****************调试可以发现，通过ApplicationContext加载XML时都要调用AbstractApplicationContext类中的refresh方法**********************/
        /**
         * 使用BeanFactory，只能逐个xml文件的加载，无法一起全部加载
         */
        BeanDefinitionRegistry reg = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(reg);
        reader.loadBeanDefinitions(new ClassPathResource("bean1.xml"));
        reader.loadBeanDefinitions(new ClassPathResource("bean2.xml"));
        BeanFactory bf=(BeanFactory)reg;

        /**
         * XmlWebApplicationContext是专为Web工程定制的
         */
//        ServletContext servletContext = request.getSession().getServletContext();
//        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext );

        /**
         * web工程中在web.xml文件设置加载的文件
         */
    }
}
