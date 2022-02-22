package com.javabydeveloper;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;


public class LoadConfig extends Thread{
    public static void getPropValues() throws IOException {

        try {
            FileInputStream propsFile = new FileInputStream(new File("config.properties"));
            Properties properties = new Properties();
            properties.load(new InputStreamReader(propsFile, Charset.forName("UTF-8")));
            propsFile.close();

         /*   String key_sms = properties.getProperty("appkey", "");
            synchronized (Preference.key_sms) {
                Preference.key_sms = key_sms.split(",");

            }

            String ip_access = properties.getProperty("ip_access", "");
            synchronized (Preference.ip_access) {
                Preference.ip_access = ip_access.split(",");
            }*/

            String to_1 = properties.getProperty("to_1", "");
            synchronized (Preference.to_1) {
                Preference.to_1 = to_1;
            }
            String to_2 = properties.getProperty("to_2", "");
            synchronized (Preference.to_2) {
                Preference.to_2 = to_2;
            }
            String to_3 = properties.getProperty("to_3", "");
            synchronized (Preference.to_3) {
                Preference.to_3 = to_3;
            }

            String subject = properties.getProperty("subject", "");
            synchronized (Preference.subject) {
                Preference.subject = subject;
            }

            String fileToAttach = properties.getProperty("fileToAttach", "");
            synchronized (Preference.fileToAttach) {
                Preference.fileToAttach = fileToAttach;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
    public void run() {

        try {
            while(true){
                getPropValues();
                sleep(300000);
            }
        } catch (Exception ex3) {
            ex3.printStackTrace();

        }
    }

}
