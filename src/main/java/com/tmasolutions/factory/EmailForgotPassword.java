package com.tmasolutions.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@Service
public class EmailForgotPassword {
    @Autowired
    private JavaMailSender emailSender;

    Map<String, String> propertyModel = new HashMap<String, String>();

    public EmailForgotPassword() {}

    private String proceedData() throws IOException {
        Resource resource = new ClassPathResource("/templates/notifications/email/EmailForgotPassword.html");
        File file = resource.getFile();
        String html = Files.readString(file.toPath());
        for(Entry<String, String> entry : propertyModel.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            html = html.replace("${" +key + "}" , value);
        }
        return html;
    }

    public void sendEmail(String Username, String Link, String toAddress) throws MessagingException, IOException {

        propertyModel.put("username", Username);
        propertyModel.put("link", Link);

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setTo(toAddress);
        helper.setText(proceedData(), true);
        helper.setSubject("Forgot password");
        emailSender.send(message);
    }

}

