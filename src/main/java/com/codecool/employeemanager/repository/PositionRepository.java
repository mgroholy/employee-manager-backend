package com.codecool.employeemanager.repository;

import com.codecool.employeemanager.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Integer> {

    Optional<Position> findByName(String name);
}
