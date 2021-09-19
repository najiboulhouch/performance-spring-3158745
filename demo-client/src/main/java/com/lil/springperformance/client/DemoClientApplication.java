package com.lil.springperformance.client;

import java.util.Arrays;

import com.lil.springperformance.client.domain.DemoProperties;
import com.lil.springperformance.client.manage.DemoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class DemoClientApplication {

	private static Logger logger = LoggerFactory.getLogger(DemoClientApplication.class);

	private static DemoManager demoManager;

	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/application.xml", DemoClientApplication.class);
		DemoProperties props = (DemoProperties) context.getBean("appProperties");
		DemoClientApplication demoApplication = new DemoClientApplication();
		SpringApplication.run(DemoClientApplication.class, args);
		logger.info("Open this application in your browser at http://localhost:" + props.getRuntimeProperties().getProperty("server.port", ""));
		demoManager = new DemoManager(props.getRuntimeProperties().getProperty("demo.mode", "baseline"));
		context.close();
	}

}
