package hbrown.demo;

import hbrown.demo.repository.NotificationRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NotificationsExampleApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(NotificationsExampleApplication.class, args);
        final NotificationRepository repository = ctx.getBean(NotificationRepository.class);

        repository
                .findAll()
                .forEach(System.out::println);
    }

}
