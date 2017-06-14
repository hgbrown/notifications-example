package hbrown.demo.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "sms_notification")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class SmsNotification extends Notification {

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Builder
    private SmsNotification(String recipientId, String firstName, String lastName, Date createdOn, String phoneNumber) {
        super(null, recipientId, firstName, lastName, createdOn);
        this.phoneNumber = phoneNumber;
    }

}
