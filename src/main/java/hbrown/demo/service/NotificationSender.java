package hbrown.demo.service;

import hbrown.demo.model.Notification;

public interface NotificationSender<N extends Notification> {

    Class<N> appliesTo();

    void send(N notification);

}
