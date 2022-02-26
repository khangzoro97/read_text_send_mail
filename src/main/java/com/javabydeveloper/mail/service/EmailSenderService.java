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
    private int count_file_dtv1() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("D:\\report\\6508.txt"));
        String str;
        int count = 0;
        int k=1;
        while ((str = in.readLine()) != null) {
            if(k>3&&str.split(" ").length==12){
                count++;
            }
            k++;

        }
        return count;
    }
    private int count_file_dtv2() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("D:\\report\\6509.txt"));
        String str;
        int count = 0;
        int k=1;
        while ((str = in.readLine()) != null) {
            if(k>3&&str.split(" ").length==12){
                count++;
            }
            k++;

        }
        return count;
    }
    private int count_file_dtv3() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("D:\\report\\6510.txt"));
        String str;
        int count = 0;
        int k=1;
        while ((str = in.readLine()) != null) {
            if(k>3&&str.split(" ").length==12){
                count++;
            }
            k++;

        }
        return count;
    }

    public void sendEmail() throws MessagingException, IOException {
        int count = count_file();
        String[][] arr = new String[count][16];
        int count1 = count_file_dtv1();
        String[][] arr1 = new String[count1][11];
        int count2 = count_file_dtv2();
        String[][] arr2 = new String[count2][11];
        int count3 = count_file_dtv3();
        String[][] arr3 = new String[count3][11];
        int x=0;
        int y=0;
        int z=0;
        int ans_08=0;
        int ans_09=0;
        int ans_10=0;
        int aban=0;
        int ans=0;
        int other=0;
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
              int count_08 = Arrays.toString(arr[j]).split("6008").length;
              int count_09 = Arrays.toString(arr[j]).split("6009").length;
              int count_10 = Arrays.toString(arr[j]).split("6010").length;
              int count_ans = Arrays.toString(arr[j]).split("ANS").length;
              int count_aban = Arrays.toString(arr[j]).split("ABAN").length;
              int count_other = Arrays.toString(arr[j]).split("OTHER").length;

              if(count_08==2){
                  x=x+1;
              }
                if(count_09==2){
                    y=y+1;
                }
                if(count_10==2){
                    z=z+1;
                }
                if(count_08==2&&count_ans==2){
                    ans_08=ans_08+1;
                }
                if(count_09==2&&count_ans==2){
                    ans_09=ans_09+1;
                }
                if(count_10==2&&count_ans==2){
                    ans_10=ans_10+1;
                }
                if(count_ans==2){
                    ans=ans+1;
                }
                if(count_aban==2){
                    aban=aban+1;
                }
                if(count_other==2){
                    other=other+1;
                }
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            BufferedReader in = new BufferedReader(new FileReader("D:\\report\\6508.txt"));
            String str;

            int i = 1;

            while ((str = in.readLine()) != null) {
                if (i > 3 && str.split(" ").length == 12) {
                    arr1[i - 4] = str.split(" ");
                }
                i++;

            }

            for (int j = 0; j < count1; j++) {
                /*System.out.println(Arrays.toString(arr1[j]));*/
            }


            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader in = new BufferedReader(new FileReader("D:\\report\\6509.txt"));
            String str;

            int i = 1;

            while ((str = in.readLine()) != null) {
                if (i > 3 && str.split(" ").length == 12) {
                    arr2[i - 4] = str.split(" ");
                }
                i++;

            }

            for (int j = 0; j < count2; j++) {
                /*System.out.println(Arrays.toString(arr2[j]));*/
            }


            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader in = new BufferedReader(new FileReader("D:\\report\\6510.txt"));
            String str;

            int i = 1;

            while ((str = in.readLine()) != null) {
                if (i > 3 && str.split(" ").length == 12) {
                    arr3[i - 4] = str.split(" ");
                }
                i++;

            }

            for (int j = 0; j < count3; j++) {
                /*System.out.println(Arrays.toString(arr3[j]));*/
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
        model.put("time",arr[0][2]);
        model.put("x",x);
        model.put("y",y);
        model.put("z",z);
        model.put("count",count);
        model.put("ans",ans);
        model.put("aban",aban);
        model.put("other",other);
        model.put("ans_08",ans_08);
        model.put("ans_09",ans_09);
        model.put("ans_10",ans_10);
        model.put("sta_1",arr1[0][1]);
        model.put("avai_1",arr1[0][7]);
        model.put("sta_2",arr2[0][1]);
        model.put("avai_2",arr2[0][7]);
        model.put("sta_3",arr3[0][1]);
        model.put("avai_3",arr3[0][7]);


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
