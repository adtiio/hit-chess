package com.hit_chess.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.hit_chess.model.Piece;


@Service
public class ChessGameService {

    private List<List<Piece>> board;
    public int player = 0;

    public ChessGameService() {
        initializeBoard();
    }

    public void initializeBoard() {
        player = 0;
        board = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Piece> row = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                row.add(null);
            }
            board.add(row);
        }

        String symbols[] = {"♖", "♖", "♕", "♔", "♖"};
        String names[] = {"pown", "pown", "hero1", "hero2", "pown"};

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board.get(i).set(j, new Piece("", "", ""));
            }
        }
        for (int i = 0; i < 5; i++) {
            board.get(0).set(i, new Piece(names[i], "A", symbols[i]));
            board.get(4).set(i, new Piece(names[i], "B", symbols[i]));
        }
    }

    public List<List<Piece>> getBoardState() {
        return board;
    }

    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        Piece piece = board.get(fromRow).get(fromCol);
        switch (piece.getType()) {
            case "pown":
                movePown(fromRow, fromCol, toRow, toCol, piece);
                break;
            case "hero1":
                moveHero1(fromRow, fromCol, toRow, toCol, piece);
                break;
            case "hero2":
                moveHero2(fromRow, fromCol, toRow, toCol, piece);
                break;
            default:
                move(fromRow, fromCol, toRow, toCol, piece);
        }
    }

    public void movePown(int fromRow, int fromCol, int toRow, int toCol, Piece piece) {
        if (((fromRow == toRow - 1 && fromCol == toCol) || 
             (fromCol == toCol - 1 && fromRow == toRow) || 
             (fromRow == toRow + 1 && fromCol == toCol) || 
             (fromCol == toCol + 1 && fromRow == toRow))) {
            move(fromRow, fromCol, toRow, toCol, piece);
        }
    }

    public void moveHero1(int fromRow, int fromCol, int toRow, int toCol, Piece piece) {
        // Hero1 moves two blocks straight in any direction (Up, Down, Left, Right)
        if ((Math.abs(toRow - fromRow) == 2 && fromCol == toCol) || 
            (Math.abs(toCol - fromCol) == 2 && fromRow == toRow)) {

            int midRow = (fromRow + toRow) / 2;
            int midCol = (fromCol + toCol) / 2;

            Piece targetPiece = board.get(toRow).get(toCol);
            Piece midPiece = board.get(midRow).get(midCol);

            if (targetPiece == null || !targetPiece.getColor().equals(piece.getColor())) {
                if (midPiece != null && !midPiece.getColor().equals(piece.getColor())) {
                    board.get(midRow).set(midCol, new Piece("", "", ""));
                }
                move(fromRow, fromCol, toRow, toCol, piece);
            }
        }
    }

    public void moveHero2(int fromRow, int fromCol, int toRow, int toCol, Piece piece) {
        // Hero2 moves two blocks diagonally in any direction (FL, FR, BL, BR)
        if (Math.abs(toRow - fromRow) == 2 && Math.abs(toCol - fromCol) == 2) {
            int midRow = (fromRow + toRow) / 2;
            int midCol = (fromCol + toCol) / 2;

            Piece targetPiece = board.get(toRow).get(toCol);
            Piece midPiece = board.get(midRow).get(midCol);

            if (targetPiece == null || !targetPiece.getColor().equals(piece.getColor())) {
                if (midPiece != null && !midPiece.getColor().equals(piece.getColor())) {
                    board.get(midRow).set(midCol, new Piece("", "", ""));
                }
                move(fromRow, fromCol, toRow, toCol, piece);
            }
        }
    }

    public void move(int fromRow, int fromCol, int toRow, int toCol, Piece piece) {
        Piece targetPiece = board.get(toRow).get(toCol);

        if(targetPiece.getColor().equals("") || !targetPiece.getColor().equals(piece.getColor())){
            if (player == 0) {
                board.get(fromRow).set(fromCol, new Piece("","", ""));
                board.get(toRow).set(toCol, piece);
                player = 1;
            } else if (player == 1 && piece.getColor().equals("A")) {
                board.get(fromRow).set(fromCol, new Piece("", "", ""));
                board.get(toRow).set(toCol, piece);
                player = 2;
            } else if (player == 2 && piece.getColor().equals("B")) {
                board.get(fromRow).set(fromCol, new Piece("","", ""));
                board.get(toRow).set(toCol, piece);
                player = 1;
            }
        }
        
    }
}
