package com.hit_chess.handler;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hit_chess.model.Piece;
import com.hit_chess.service.ChessGameService;

@Component
public class ChessGameHandler extends TextWebSocketHandler{
    private final ChessGameService chessGameService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    public ChessGameHandler(ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);
        sendBoardState(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Map<String, Object> data = objectMapper.readValue(message.getPayload(), Map.class);
        String action =(String)data.get("action");
        System.out.println(action);
        if (action!=null ) {
            chessGameService.initializeBoard();
            broadcastBoardState();
        } else {
            System.out.println(data);
            int fromRow = (Integer) data.get("fromRow");
            int fromCol = (Integer) data.get("fromCol");
            int toRow = (Integer) data.get("toRow");
            int toCol = (Integer) data.get("toCol");
            chessGameService.movePiece(fromRow, fromCol, toRow, toCol);
            broadcastBoardState();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session.getId());
    }

    private void sendBoardState(WebSocketSession session) throws Exception {
        List<List<Piece>> boardState = chessGameService.getBoardState();
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(boardState)));
    }

    private void broadcastBoardState() throws Exception {
        List<List<Piece>> boardState = chessGameService.getBoardState();
        String boardJson = objectMapper.writeValueAsString(boardState);
        for (WebSocketSession session : sessions.values()) {
            session.sendMessage(new TextMessage(boardJson));
        }
    }
}