package hbrown.demo.repository;

import hbrown.demo.model.EmailNotification;
import hbrown.demo.model.Notification;
import hbrown.demo.model.SmsNotification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationRepositoryTest {

    @Autowired
    NotificationRepository cut;

    @Test
    public void sanityTest() throws Exception {
        assertThat(cut).isNotNull();
    }

    @Test
    public void shouldBeAbleToPersistEmailNotification() throws Exception {
        final EmailNotification email = EmailNotification
                .builder()
                .recipientId("0000000001")
                .firstName("Henry")
                .lastName("Brown")
                .emailAddress("henry.g.brown@gmail.com")
                .build();

        cut.save(email);

        assertThat(email.getId()).isNotNull();
    }

    @Test
    public void shouldBeAbleToPersistSmsNotification() throws Exception {
        final SmsNotification sms = SmsNotification
                .builder()
                .recipientId("0000000002")
                .firstName("Henry")
                .lastName("Brown")
                .phoneNumber("27834470070")
                .build();

        cut.save(sms);

        assertThat(sms.getId()).isNotNull();
    }

    @Test
    public void shouldBeAbleToFindAllRecipientsByRecipientId() throws Exception {
        final List<Notification> recipients = cut
                .findByRecipientIdIsIn(asList("1111111111", "2222222222"));

        assertThat(recipients)
                .hasSize(2)
                .extracting("id")
                .containsExactlyInAnyOrder(-1000L, -2000L);
    }

}