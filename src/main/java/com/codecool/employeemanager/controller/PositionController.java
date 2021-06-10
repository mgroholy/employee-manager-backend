package com.codecool.employeemanager.controller;

import com.codecool.employeemanager.model.Position;
import com.codecool.employeemanager.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://employee-ms.netlify.app", allowCredentials = "true")
@RestController
public class PositionController {

    private PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/positions")
    public List<Position> getPositions(){
        return positionService.getPositions();
    }

    @PostMapping(path="/positions")
    public Position addNewPosition(@RequestBody Position position){
        return positionService.addNewPosition(position);
    }

    @DeleteMapping(path="/positions/{id}/delete")
    public void deletePosition(@PathVariable(name="id") int positionId){
        positionService.deletePosition(positionId);
    }

}
