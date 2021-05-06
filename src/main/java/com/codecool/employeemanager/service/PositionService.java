package com.codecool.employeemanager.service;

import com.codecool.employeemanager.model.Position;
import com.codecool.employeemanager.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public Position findByName(String name){
        return positionRepository.findByName(name).orElseThrow(() -> new NoSuchElementException("No position with the name " + name + " exists"));
    }

    public Position addNewPosition(Position position) {
        Optional<Position> positionOptional = positionRepository.findByName(position.getName());
        if(positionOptional.isPresent()){
            throw new IllegalArgumentException("Position with the name \"" + position.getName() + "\" already exists.");
        }
        position.setEmployees(new HashSet<>());
        positionRepository.save(position);
        return position;
    }
}
