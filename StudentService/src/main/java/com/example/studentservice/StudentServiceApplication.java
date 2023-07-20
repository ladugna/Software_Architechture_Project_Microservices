package com.example.studentservice;

//import com.example.teacherservice.event.TeacherAddedEvent;
import com.example.teacherservice.event.TeacherAddedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;


@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
@EnableKafka
public class StudentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentServiceApplication.class, args);
    }

    @Async
    @KafkaListener(topics = {"notificationTopic"}, groupId = "notificationId")
    public void handleNotification(TeacherAddedEvent teacherAddedEvent){
        log.info("Received notification for teacher - {},{},{} added", teacherAddedEvent.getFirstName(), teacherAddedEvent.getLastName(),teacherAddedEvent.getSchool());
    }

}

