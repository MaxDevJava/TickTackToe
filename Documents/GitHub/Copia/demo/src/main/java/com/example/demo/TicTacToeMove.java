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
public class TicTacToeMove {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    public Player player;
    public String board;

    public TicTacToeMove() {
        this(new TicTacToeGame());
    }

    public TicTacToeMove(TicTacToeGame game) {
        this.board = Arrays.stream(game.board)
                .map(r -> Arrays.stream(r).map(Cell::toString).collect(Collectors.joining(",")))
                .collect(Collectors.joining(";"));
        this.player = game.player;
    }
    public Long getId() {
        return id;
    }
}
