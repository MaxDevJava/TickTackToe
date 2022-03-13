package com.example.demo;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public class GameLogic {
    public Cell[][] board;

    public Player player;

    public GameLogic() {
        player = Player.X;
        board = new Cell[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = Cell.Empty;
    }


    public GameLogic(Move move) {
        this.player = move.player;
        this.board = Arrays.stream(move.board.split(";"))
                .map(r -> Arrays.stream(r.split(",")).map(Cell::valueOf).toArray(Cell[]::new))
                .toArray(Cell[][]::new);
    }


    public Optional<Player> getWinner() {
        final var b = board;
        Function<Cell, Optional<Player>> winner = cell -> Optional.of(cell == Cell.X ? Player.X : Player.O);


        // check rows
        if (b[0][0] == b[0][1] && b[0][0] == b[0][2] && b[0][0] != Cell.Empty) return winner.apply(b[0][0]);
        if (b[1][0] == b[1][1] && b[1][0] == b[1][2] && b[1][0] != Cell.Empty) return winner.apply(b[1][0]);
        if (b[2][0] == b[2][1] && b[2][0] == b[2][2] && b[2][0] != Cell.Empty) return winner.apply(b[2][0]);
        // check columns
        if (b[0][0] == b[1][0] && b[0][0] == b[2][0] && b[0][0] != Cell.Empty) return winner.apply(b[0][0]);
        if (b[0][1] == b[1][1] && b[0][1] == b[2][1] && b[0][1] != Cell.Empty) return winner.apply(b[0][1]);
        if (b[0][2] == b[1][2] && b[0][2] == b[2][2] && b[0][2] != Cell.Empty) return winner.apply(b[0][2]);
        // check diagonals
        if (b[0][0] == b[1][1] && b[0][0] == b[2][2] && b[0][0] != Cell.Empty) return winner.apply(b[0][0]);
        if (b[0][2] == b[1][1] && b[0][2] == b[2][0] && b[0][2] != Cell.Empty) return winner.apply(b[0][2]);
        return Optional.empty();
    }


    public boolean isDraw() {
        return Arrays.stream(board).flatMap(Arrays::stream).allMatch(cell -> cell != Cell.Empty);
    }

    public boolean isGameOver() {

        return getWinner().isPresent() || isDraw();
    }

    public void makeMove(int i, int j) {
        board[i][j] = player == Player.X ? Cell.X : Cell.O;
        player = player == Player.X ? Player.O : Player.X;
    }


    public boolean isMoveValid(int i, int j) {
        return board[i][j] == Cell.Empty;
    }
}
