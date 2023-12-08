package com.marijana.library1223.models;

import com.marijana.library1223.enums.TopicEnum;
import jakarta.persistence.Embeddable;
import lombok.Data;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

@Data
@Embeddable
public class InformationBook {
    private String educationLevel;
    private String currentTopic;

}
