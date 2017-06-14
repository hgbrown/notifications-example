package hbrown.demo.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "email_notification")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class EmailNotification extends Notification {

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @Builder
    private EmailNotification(String recipientId, String firstName, String lastName, Date createdOn, String emailAddress) {
        super(null, recipientId, firstName, lastName, createdOn);
        this.emailAddress = emailAddress;
    }

}
