package ru.sakaev.backEndApp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@EnableIntegration
public class FileWritingIntegration {

    @Autowired
    private ProductGateway productGateway;

    @Bean
    public MessageChannel requestChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "requestChannel")
    public FileWritingMessageHandler fileWriter() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("C:/workingdir/fremSpri/hw/hw12/file"));
        handler.setExpectReply(false);
        handler.setAppendNewLine(true);
        handler.setFileNameGenerator(message -> "request_" + System.currentTimeMillis() + ".txt");
        return handler;
    }

    @MessagingGateway(defaultRequestChannel = "requestChannel")
    public interface ProductGateway {
        void writeToFile(@Header("http_requestMethod") String method, @Header("http_requestUrl") String url, String payload);
    }
}
