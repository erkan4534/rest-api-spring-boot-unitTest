package com.vendor.mail;

import com.vendor.exception.ZeroSubscribersException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NewsletterSender {

    private SubscribersDatabase subscribersDatabase;
    private MessagingEngine messagingEngine;

    public void sendNewsletter(String subject) throws ZeroSubscribersException {
        List<String> emails = subscribersDatabase.getSubscribers();

        if(numberOfSubscribers() == 0){
            throw new ZeroSubscribersException();
        }
        messagingEngine.sendEmail(subject, emails);
    }

    public int numberOfSubscribers(){
        return subscribersDatabase.getSubscribers().size();
    }


}
