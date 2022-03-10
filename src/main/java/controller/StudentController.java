package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import model.StudentMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProducerService;

@Api(tags = "student.message") //
@RestController
@RequestMapping("/v1")
public class StudentController {
    private final ProducerService producerService;
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    public StudentController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping(value = "/sendStudentMessageEvent",consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}) //
    public ResponseEntity<StudentMessage> sendStudentMessage(
            @RequestBody StudentMessage studentMessage,
            @RequestHeader(value = "eventType", defaultValue = "student-message") String eventType,
            @RequestHeader(value = "source", defaultValue = "-") String source) {

        LOGGER.info("Sending message");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            LOGGER.info(objectMapper.writeValueAsString(studentMessage));

        } catch (JsonProcessingException e) {
            LOGGER.info("Empty message");
            e.printStackTrace();
        }

        producerService.sendStudentMessageEvent(studentMessage, eventType, source);
        return ResponseEntity.ok(studentMessage);
    }

}
