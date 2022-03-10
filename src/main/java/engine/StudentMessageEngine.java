package engine;

import model.EventBase;
import model.StudentMessageEvent;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StudentMessageEngine {
    @Value("${kafka.topic.student-message}")
    private String STUDENT_MESSAGE_TOPIC;

    private final Producer<String, StudentMessageEvent> studentMessageEventProducer;
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentMessageEngine.class);

    public StudentMessageEngine(Producer<String, StudentMessageEvent> studentMessageEventProducer) {
        this.studentMessageEventProducer = studentMessageEventProducer;
    }

    private <T extends EventBase> void sendMessage(@NotNull Producer<String, T> producer, String topic, T studentMessageEvent) {

        producer.send(new ProducerRecord<>(topic, studentMessageEvent.getEventId(), studentMessageEvent), (metadata, exception) -> {
            if (exception != null) {
                LOGGER.error("Encounter an error while sending event to kafka - EventId: {} - Error: {}",
                       studentMessageEvent.getEventId(), exception.getMessage());
           } else {
                LOGGER.info("Succesfully sended event of eventId {} to topic {} partition [{}] @ offset {}",
                        studentMessageEvent.getEventId(), metadata.topic(), metadata.partition(), metadata.offset());
            }
        });
    }

    public void sendStudentMessageEvent(StudentMessageEvent studentMessageEvent) {
        this.sendMessage(studentMessageEventProducer, STUDENT_MESSAGE_TOPIC, studentMessageEvent);
    }

}
