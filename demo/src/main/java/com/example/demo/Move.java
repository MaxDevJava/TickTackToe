package com.example.demo;

import javax.persistence.*;
import java.util.Arrays;
import java.util.stream.Collectors;

enum Cell {
    Empty, X, O
}

enum Player {
    X, O
}

@Entity
public class Move {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    public Player player;
    public String board;

    public Move() {
        this(new GameLogic());
    }

    public Move(GameLogic game) {
        this.board = Arrays.stream(game.board)
                .map(r -> Arrays.stream(r).map(Cell::toString).collect(Collectors.joining(",")))
                .collect(Collectors.joining(";"));
        this.player = game.player;
    }
    public Long getId() {
        return id;
    }

    public String getBoard(){
        return board;
    }
}
