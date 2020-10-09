package com.bharath.flightreservation.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.bharath.flightreservation.controllers.ReservationController;

@Component
public class EmailUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
	
	@Value("${com.bharath.flightreservation.itinerary.email.subject}")
	private String emailSubject;
	
	@Value("${com.bharath.flightreservation.itinerary.email.body}")
	private String emailBody;
	

	@Autowired
	private JavaMailSender sender;
	
	public void sendItinerary(String toAddress, String filePath) {
		MimeMessage message = sender.createMimeMessage();
		LOGGER.info("Inside util - sendItinerary()");
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setTo(toAddress);
			messageHelper.setSubject(emailSubject);
			messageHelper.setText(emailBody);
			messageHelper.addAttachment("Itinerary", new File(filePath));
//			sender.send(message);
		} catch (MessagingException e) {
			LOGGER.error("Inside util - sendItinerary(), error is " + e);
		}
	}
}
