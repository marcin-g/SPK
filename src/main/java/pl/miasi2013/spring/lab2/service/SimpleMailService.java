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
			        List<String> mails = new ArrayList<String>();
			        mails.add(user.getEmail());
			        for (User admin : userService.getAdmins()) {
			        	mails.add(admin.getEmail());
			        }
			        msg.setCc(mails.toArray(new String[mails.size()]));
			        msg.setText(new StringBuilder()
			           .append("Witaj " + user.getFirstname() + " " + user.getLastname() + ",\n")
			           .append("Czas na oddanie książki " + book.getTitle() + " minął. Prosimy jak najszybciej odnieść książkę do biblioteki")
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