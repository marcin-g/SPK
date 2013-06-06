package pl.miasi2013.spring.lab2.service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import pl.miasi2013.spring.lab2.model.Book;
import pl.miasi2013.spring.lab2.model.User;

public class SimpleMailService{

    private MailSender mailSender;
    private SimpleMailMessage templateMessage;
    private Object lock=new Object();

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
        Runnable sendMail=new Runnable() {
			
			@Override
			public void run() {
				synchronized (lock) {
					System.err.println("wysylam "+templateMessage.getFrom());   
			        SimpleMailMessage msg = new SimpleMailMessage(templateMessage);
			        msg.setCc(new String[]{
			        		"przemyslaw.grzeszczak@gmail.com", "bartosz.koninski@gmail.com","martinezz699@gmail.com"
			        });
			        msg.setText("Book ");
			        try{
			            mailSender.send(msg);
			        }
			        catch(MailException ex) {
			            // simply log it and go on...
			            System.err.println(ex.getMessage());            
			        }
				}
			}
		};
		
		Thread watek=new Thread(sendMail);
		watek.start();
    	
    }
}