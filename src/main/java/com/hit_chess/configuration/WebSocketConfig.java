package com.hit_chess.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.hit_chess.handler.ChessGameHandler;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

    private final ChessGameHandler chessGameHandler;
    public WebSocketConfig(ChessGameHandler chessGameHandler) {
        this.chessGameHandler = chessGameHandler;
    }
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
        registry.addHandler(chessGameHandler, "/chess").setAllowedOrigins("*");
    }

}
