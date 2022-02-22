package com.javabydeveloper.mail.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.javabydeveloper.Preference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.javabydeveloper.model.Mail;

import static java.lang.Thread.sleep;

@Service("emailService")
public class EmailSenderService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    private int count_file() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("D:\\report\\report_daily.txt"));
        String str;
        int count = 0;
        int k=1;
        while ((str = in.readLine()) != null) {
            if(k>3&&str.split(" ").length==16){
                count++;
            }
            k++;

        }
        return count;
    }

    public void sendEmail() throws MessagingException, IOException {
        int count = count_file();
        String[][] arr = new String[count][16];
        try {
            BufferedReader in = new BufferedReader(new FileReader("D:\\report\\report_daily.txt"));
            String str;

            int i = 1;

            while ((str = in.readLine()) != null) {
                if (i > 3 && str.split(" ").length == 16) {
                    arr[i - 4] = str.split(" ");
                }
                i++;

            }

            for (int j = 0; j < count; j++) {
                /*System.out.println(Arrays.toString(arr[j]));*/
            }


            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        Date date = new Date();
        DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

//        helper.addAttachment("template-cover.png", new ClassPathResource("javabydeveloper-email.PNG"));

        helper.setFrom(new InternetAddress("sale@mobifoneplus.com.vn"));

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name",arr);


        Context context = new Context();
        context.setVariables(model);

        String html = templateEngine.process("newsletter-template", context);
        helper.setText(html, true);

        String to_1 = Preference.to_1;
        String to_2 = Preference.to_2;
        String to_3 = Preference.to_3;
        helper.setTo(new String[]{to_1, to_2, to_3});

        String subject = Preference.subject;
        helper.setSubject(subject+' '+dateFormat2.format(date));

        try {
            emailSender.send(message);
            System.out.println("Email sent success at " + dateFormat.format(date));
        } catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }
    public void start() {

        try {
            while(true){
                sendEmail();
                sleep(86401000);
            }
        } catch (Exception ex3) {
            ex3.printStackTrace();
        }
    }

}
