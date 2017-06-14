package hbrown.demo.service;

import hbrown.demo.model.EmailNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationSender implements NotificationSender<EmailNotification> {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public Class<EmailNotification> appliesTo() {
        return EmailNotification.class;
    }

    @Override
    public void send(EmailNotification notification) {
        LOGGER.info("Send email to {} {} via address: {}",
                notification.getFirstName(),
                notification.getLastName(),
                notification.getEmailAddress()
        );
    }

}
