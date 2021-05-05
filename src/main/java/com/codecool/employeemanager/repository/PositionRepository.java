package com.codecool.employeemanager.repository;

import com.codecool.employeemanager.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {
}
