package hbrown.demo.service;

import hbrown.demo.model.SmsNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SmsNotificationSender implements NotificationSender<SmsNotification> {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public Class<SmsNotification> appliesTo() {
        return SmsNotification.class;
    }

    @Override
    public void send(SmsNotification notification) {
        LOGGER.info("Send sms to {} {} via phone number: {}",
                notification.getFirstName(),
                notification.getLastName(),
                notification.getPhoneNumber()
        );
    }
}
