package io.licence.imageformater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.licence.imageformater.utils.Crop;
import io.licence.imageformater.utils.PathImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitMQConsumer {

    Logger log = LoggerFactory.getLogger(RabbitMQConsumer.class);

    private ImageService imageService;

    @Autowired
    public RabbitMQConsumer(ImageService imageService) {
        this.imageService = imageService;
    }

    @RabbitListener(queues = "${javainuse.rabbitmq.queue}")
    void greenFilter(Message message) throws IOException {
        try {
            if (message.getMessageProperties().getType().equals("green")) {
                var messageString = new String(message.getBody());
                var pathImage = new ObjectMapper().readValue(messageString, PathImage.class);
                var newUrl = imageService.greenFilter(pathImage.getUrl());
                log.info(newUrl);
            }
        } catch (JsonProcessingException e) {
            log.error("Failed to parse json message: "+e.getLocalizedMessage());
        }
    }


    @RabbitListener(queues = "${javainuse.rabbitmq.queue}")
    public void receiveMessage(Message message) throws IOException {
        try {
            if (message.getMessageProperties().getType().equals("crop")) {
                var messageString = new String(message.getBody());
                var crop = new ObjectMapper().readValue(messageString, Crop.class);
                var newUrl = imageService.crop(crop.getPathImage(), crop.getX(),
                        crop.getY(), crop.getWidth(), crop.getHeight());
                log.info(newUrl);
            }
        } catch (JsonProcessingException e) {
            log.error("Failed to parse json message: "+e.getLocalizedMessage());
        }}

}