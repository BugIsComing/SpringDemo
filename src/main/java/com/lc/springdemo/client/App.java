package com.lc.springdemo.client;

import com.lc.springdemo.model.Bean1;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lc
 */
public class App {
    public static void main(String[]args){
        /**
         * 加载和xml文件的顺序无关，可以交换bean1.xml 和 bean2.xml的顺序
         */
        ApplicationContext factory=new ClassPathXmlApplicationContext(new String[] {"bean2.xml","bean1.xml"});
        Bean1 bean1 = (Bean1)factory.getBean("bean1");
        Bean1 bean11 = (Bean1)factory.getBean("bean1");
    }
}
