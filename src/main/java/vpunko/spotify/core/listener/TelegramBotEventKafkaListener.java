package vpunko.spotify.core.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import vpunko.spotify.core.dto.User;

@Slf4j
@Component
public class TelegramBotEventKafkaListener {

    @KafkaListener(topics = "${spring.kafka.in.telegram-bot-user-received-event.topic}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "group1")
    void listener(@Payload User data,
                  @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                  @Header(KafkaHeaders.OFFSET) int offset) {
        log.info("Received message [{}] from group1, partition-{} with offset-{}",
                data,
                partition,
                offset);
    }
}