package ar.edu.unju.fi.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@Configuration
public class AppBean {
	
	private static ApplicationContext context;
	
	public static <T> T getBean(Class<T> type){
		context = new ClassPathXmlApplicationContext(new String[] {"Spring-AutoScan.xml"});
		return context.getBean(type);
	}
	
} 
