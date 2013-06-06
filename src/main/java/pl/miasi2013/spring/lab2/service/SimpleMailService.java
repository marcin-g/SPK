package pl.miasi2013.spring.lab2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import pl.miasi2013.spring.lab2.model.Book;
import pl.miasi2013.spring.lab2.model.User;

public class SimpleMailService{
	
	@Autowired
	private UserService userService;

    private MailSender mailSender;
    private SimpleMailMessage templateMessage;
    private Object lock=new Object();

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    public void sendInfoTwoMonths(final Book book, final User user){
        Runnable sendMail=new Runnable() {
			
			@Override
			public void run() {
				synchronized (lock) {
					System.err.println("wysylam "+templateMessage.getFrom());   
			        SimpleMailMessage msg = new SimpleMailMessage(templateMessage);
			        msg.setSubject("Upłynął czas dla książki");
			        msg.setTo(user.getEmail());
			        List<String> mails = new ArrayList<String>();
			        for (User admin : userService.getAdmins()) {
			        	mails.add(admin.getEmail());
			        }
			        msg.setCc(mails.toArray(new String[mails.size()]));
			        msg.setText(new StringBuilder()
			           .append("Witaj " + user.getFirstname() + " " + user.getLastname() + ",\n")
			           .append("Czas na oddanie książki " + book.getTitle() + " minął. Książka zostaje zwrócona do biblioteki do biblioteki")
			           .append("")
			           .append("Pozdrawiamy")
			           .toString()
			        );
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
    
    public void sendBookOrdered(final Book book, final User user){
        Runnable sendMail=new Runnable() {
			
			@Override
			public void run() {
				synchronized (lock) {
					System.err.println("wysylam "+templateMessage.getFrom());   
			        SimpleMailMessage msg = new SimpleMailMessage(templateMessage);
			        msg.setSubject("Zamówiono nową książkę");
			        msg.setTo(user.getEmail());
			        List<String> mails = new ArrayList<String>();
			        for (User admin : userService.getAdmins()) {
			        	mails.add(admin.getEmail());
			        }
			        msg.setCc(mails.toArray(new String[mails.size()]));
			        msg.setText(new StringBuilder()
			           .append("Zamówiona książka:")
			           .append(book.getTitle() + ", " + book.getAuthor() + ", " + book.getPublisher() + ", " + book.getYear())
			           .toString()
			        );
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
    
    public void sendBookBorowed(final Book book, final User whoHas, final User whoWantToBorrow){
        Runnable sendMail=new Runnable() {
			
			@Override
			public void run() {
				synchronized (lock) {
					System.err.println("wysylam "+templateMessage.getFrom());   
			        SimpleMailMessage msg = new SimpleMailMessage(templateMessage);
			        msg.setSubject("Wypożyczenie książki");
			        msg.setTo(whoWantToBorrow.getEmail());
			        List<String> mails = new ArrayList<String>();
			        mails.add(whoHas.getEmail());
			        for (User admin : userService.getAdmins()) {
			        	mails.add(admin.getEmail());
			        }
			        msg.setCc(mails.toArray(new String[mails.size()]));
			        msg.setText(new StringBuilder()
			           .append("Użytkownik " + whoWantToBorrow.getFirstname() + " " + whoWantToBorrow.getLastname() + " wypożyczy książkę " + book.getTitle() + ", którą aktualnie posiada" + whoHas.getFirstname() + " " + whoHas.getLastname() + "." )
			           .append("")
			           .append("Pozdrawiamy")
			           .toString()
			        );
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
    
    public void sendBookReturned(final Book book, final User whoWantToBorrow){
        Runnable sendMail=new Runnable() {
			
			@Override
			public void run() {
				synchronized (lock) {
					System.err.println("wysylam "+templateMessage.getFrom());   
			        SimpleMailMessage msg = new SimpleMailMessage(templateMessage);
			        msg.setSubject("Książka dostępna");
			        msg.setTo(whoWantToBorrow.getEmail());
			        msg.setText(new StringBuilder()
			           .append("Witaj " + whoWantToBorrow.getFirstname() + " " + whoWantToBorrow.getLastname() + ",\n")
			           .append("Książka " + book.getTitle() + " została zwrócona do biblioteki i jest dostępna do odbioru")
			           .append("")
			           .append("Pozdrawiamy")
			           .toString()
			        );
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