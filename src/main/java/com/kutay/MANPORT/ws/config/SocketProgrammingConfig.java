package com.kutay.MANPORT.ws.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class SocketProgrammingConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { //buraya mesaj gonderilcek yani burayi sunucum dinliyo
        registry.addEndpoint("/issueTracking").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) { //buradan mesaj alinacak yani burayi client dinliyo
        registry.enableSimpleBroker("/issueTrackingBroker"); //burayi react uygulamasi dinlicek her yeni issue geldiginde tekrardan issuelari cekecek
    }
}
