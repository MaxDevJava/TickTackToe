package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    Repository rep;

    public Controller(Repository rep) {
        this.rep = rep;
    }

    @GetMapping("/NewGame")
    public GameLogic getGames() {

        return new GameLogic(rep.save(new Move()));
    }

    @PostMapping("/move/{i}/{j}")
    public GameLogic move(@PathVariable Integer i, @PathVariable Integer j) {
        var previous = rep.findTopByOrderByIdDesc();

        if (previous.isEmpty()) throw new IllegalArgumentException("Game not found");

        var newGame = new GameLogic(previous.get());

        if (!newGame.isMoveValid(i, j)) throw new IllegalArgumentException("Move not valid");
        if (newGame.isGameOver()) throw new IllegalArgumentException("Game is over");

        newGame.makeMove(i, j);
        rep.save(new Move(newGame));
        return newGame;
    }
}
