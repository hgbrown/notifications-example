package hbrown.demo.service;

import hbrown.demo.model.EmailNotification;
import hbrown.demo.model.Notification;
import hbrown.demo.model.SmsNotification;
import hbrown.demo.repository.NotificationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationServiceImplTest {

    @MockBean
    NotificationRepository notificationRepository;

    @Autowired
    NotificationService cut;

    @Test
    public void sanityTest() throws Exception {
        assertThat(cut).isNotNull();
    }

    @Test
    public void shouldBeAbleToSendCampaign() throws Exception {
        final String[] recipientIds = {"aaaaaa", "bbbbb"};

        given(notificationRepository
                .findByRecipientIdIsIn(asList(recipientIds)))
                .willReturn(asList(
                        SmsNotification
                                .builder()
                                .firstName("Elizabeth")
                                .lastName("Eden")
                                .createdOn(new Date())
                                .phoneNumber("2956109717")
                                .build(),

                        EmailNotification
                                .builder()
                                .firstName("Frank")
                                .lastName("Fredericks")
                                .createdOn(new Date())
                                .emailAddress("frankie@fast.com")
                                .build()

                        )
                );
        final List<Notification> sentNotifications = cut
                .sendCampaign("All merchandise is 40% OFF", recipientIds);

        assertThat(sentNotifications)
                .hasSize(2)
                .extracting("firstName")
                .containsExactlyInAnyOrder("Elizabeth", "Frank");
    }

}
