package com.vendor.mail;

import com.vendor.exception.ZeroSubscribersException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsletterSenderTest {

    @Test
    public void constructorAssignsDatabase(){

        MessagingEngine messagingEngine = mock(MessagingEngine.class);
        SubscribersDatabase subscribersDatabase = new SubscribersDatabase();
        NewsletterSender newsletterSender = new NewsletterSender(subscribersDatabase, messagingEngine);

        assertThat(subscribersDatabase).isEqualTo(newsletterSender.getSubscribersDatabase());

    }

    @Test
    public void numberOfSubscribers(){

        SubscribersDatabase subscribersDatabaseMock = mock(SubscribersDatabase.class);
        MessagingEngine messagingEngineMock = mock(MessagingEngine.class);

        NewsletterSender sender = new NewsletterSender(subscribersDatabaseMock, messagingEngineMock);

        List<String> subscribersList = Arrays.asList("email1", "email2", "email3");
        when(subscribersDatabaseMock.getSubscribers()).thenReturn(subscribersList);

        assertThat(sender.numberOfSubscribers()).isEqualTo(3);

    }

    @Test
    public void zeroSubscribersThrown() {

        NewsletterSender newsletterSender = new NewsletterSender(new SubscribersDatabase(), new MessagingEngine());
        NewsletterSender newsletterSenderSpy = spy(newsletterSender);

        when(newsletterSenderSpy.numberOfSubscribers()).thenReturn(0);

        assertThatThrownBy(() -> newsletterSenderSpy.sendNewsletter("SUBJECT")).isInstanceOf(ZeroSubscribersException.class);

    }
}
