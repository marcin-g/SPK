package pl.miasi2013.spring.lab2.utils;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import pl.miasi2013.spring.lab2.model.Book;
import pl.miasi2013.spring.lab2.model.User;

public class SimpleMailSender{

    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    public void sendBookInfo(String text){

        // Do the business calculations...

        // Call the collaborators to persist the order...

        // Create a thread safe "copy" of the template message and customize it
        System.err.println("wysyłam");   
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo("martinezz699@gmail.com");
        msg.setText("Book ");
        try{
            this.mailSender.send(msg);
        }
        catch(MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());            
        }
    }
}