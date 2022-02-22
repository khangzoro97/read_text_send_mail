package com.javabydeveloper.demo;

import java.util.HashMap;
import java.util.Map;

import com.javabydeveloper.LoadConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.javabydeveloper.mail.service.EmailSenderService;
import com.javabydeveloper.model.Mail;

@SpringBootApplication
@ComponentScan("com.javabydeveloper.mail.service")
public class SpringBootEmailTemplateApplication implements ApplicationRunner{
	
	@Autowired
    private EmailSenderService emailService;

	
	public static void main(String[] args) {
        LoadConfig config = new LoadConfig();
        config.start();
		SpringApplication.run(SpringBootEmailTemplateApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

       /* Mail mail = new Mail();

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", "Developer!");
        model.put("location", "United States");
        model.put("sign", "Java Developer");
        mail.setProps(model);*/

        emailService.sendEmail();
        emailService.start();

	}
}
