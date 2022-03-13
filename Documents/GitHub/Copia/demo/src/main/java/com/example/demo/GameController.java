package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    TicTacToeRepository rep;

    public GameController(TicTacToeRepository repository) {

        this.rep = repository;
    }

    /**
     * @return the state of the game just created
     */
    @GetMapping("/startGame")
    public TicTacToeGame getGames() {
        return new TicTacToeGame(rep.save(new TicTacToeMove()));
    }

    /**
     * Updates the game state with the move made by the player with the given i and j coordinates, checking for invalid moves
     *
     * @param i the row of the move
     * @param j the column of the move
     *
     * @return the updated game state
     */
    @PostMapping("/move/{i}/{j}")
    public TicTacToeGame move(@PathVariable Integer i, @PathVariable Integer j) {
        var previous = rep.findTopByOrderByIdDesc();

        if (previous.isEmpty()) throw new IllegalArgumentException("Game not found");

        var newGame = new TicTacToeGame(previous.get());

        if (!newGame.isMoveValid(i, j))
            throw new IllegalArgumentException("Move not valid");
        if (newGame.isGameOver())
            throw new IllegalArgumentException("Game is over");

        newGame.makeMove(i, j);
        rep.save(new TicTacToeMove(newGame));
        return newGame;
    }
}
