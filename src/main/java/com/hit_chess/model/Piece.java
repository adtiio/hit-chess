package com.hit_chess.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Piece {
    public String type;
    public String color;
    public String symbol;
}
