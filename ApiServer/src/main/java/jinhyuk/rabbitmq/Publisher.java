package jinhyuk.rabbitmq;

import jinhyuk.dto.message.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class Publisher {

    private final RabbitTemplate rabbitTemplate;

    private static final String topicExchange = "nbp";

    public void publishToCreator(CreateClusterRequestMessage message) {
        rabbitTemplate.convertAndSend(topicExchange, "Creator", message);
        log.info("Cluster Create 메시지를 보냈습니다.");
    }


    public void publishToCreator(CreateNodeRequestMessage message) {
        rabbitTemplate.convertAndSend(topicExchange, "Creator", message);
        log.info("Cluster Update 메시지를 보냈습니다.");
    }


    public void publishToCreator(DeleteClusterRequestMessage message) {
        rabbitTemplate.convertAndSend(topicExchange, "Creator", message);
        log.info("Cluster Delete 메시지를 보냈습니다.");
    }


    public void publishToCreator(DeleteNodeRequestMessage message) {
        rabbitTemplate.convertAndSend(topicExchange, "Creator", message);
        log.info("Node Delete 메시지를 보냈습니다.");
    }


    public void publishToRemoter(UpdateClusterRequestMessage message) {
        rabbitTemplate.convertAndSend(topicExchange, "Remoter", message);
        log.info("Update Policy 메시지를 보냈습니다.");
    }
}
