package hbrown.demo.service;

import hbrown.demo.model.Notification;

import java.util.List;

public interface NotificationService {

    List<Notification> sendCampaign(String message, String ... recipientIds);

}
