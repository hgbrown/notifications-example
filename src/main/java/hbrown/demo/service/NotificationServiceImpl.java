package hbrown.demo.service;

import com.google.common.base.MoreObjects;
import hbrown.demo.model.Notification;
import hbrown.demo.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.*;

@Service
public class NotificationServiceImpl implements NotificationService {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final NotificationRepository notificationRepository;
    private final List<NotificationSender> notificationSenders;
    private final Map<Class<? extends Notification>, NotificationSender> notificationSenderMap = new HashMap<>();

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository,
                                   List<NotificationSender> notificationSenders) {
        this.notificationRepository = notificationRepository;
        this.notificationSenders = notificationSenders;
    }

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void init() {
        LOGGER.info("NotificationServiceImpl:@PostConstruct");

        notificationSenders
                .forEach(notificationSender -> notificationSenderMap
                        .put(notificationSender.appliesTo(), notificationSender));

        LOGGER.debug("notificationRepository={}", notificationRepository);
        LOGGER.debug("notificationSenders={}", notificationSenders);
        LOGGER.debug("notificationSenderMap={}", notificationSenderMap);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Notification> sendCampaign(String message, String... recipientIds) {
        LOGGER.info("NotificationServiceImpl:sendCampaign(name=[{}], recipientIds=[{}])",
                message, Arrays.toString(recipientIds));

        final List<Notification> notifications = notificationRepository
                .findByRecipientIdIsIn(asList(recipientIds));

        notifications.forEach(notification -> notificationSenderMap
                .get(notification.getClass())
                .send(notification));

        return notifications;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("notificationRepository", notificationRepository)
                .add("notificationSenders", notificationSenders)
                .add("notificationSenderMap", notificationSenderMap)
                .toString();
    }

}
