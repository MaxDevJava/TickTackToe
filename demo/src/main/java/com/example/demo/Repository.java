package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Repository extends JpaRepository<Move, Long> {
    Optional<Move> findTopByOrderByIdDesc();
}
