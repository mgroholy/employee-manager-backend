package com.codecool.employeemanager.service;

import com.codecool.employeemanager.model.Position;
import com.codecool.employeemanager.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    private PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<Position> getPositions(){
        return positionRepository.findAll();
    }
}
