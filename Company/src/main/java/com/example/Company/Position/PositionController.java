package com.example.Company.Position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/positions")
public class PositionController {

    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {this.positionService = positionService;}

    @GetMapping
    public List<Position> getPositions(){return positionService.getPositions();}

    @GetMapping("/{positionId}")
    public Position getPositionById(@PathVariable Long positionId){
        return positionService.getPositionById(positionId);
    }

    @PostMapping
    public void addNewPosition(@RequestBody Position position){
        positionService.addNewPosition(position);
    }

    @RequestMapping(value = "/{positionId}/employees/{employeeId}", method = {RequestMethod.GET, RequestMethod.PUT})
    public void addEmployeeWithPosition(@PathVariable Long positionId, @PathVariable Long employeeId){
        positionService.addEmployeeWithPosition(positionId, employeeId);
    }

    @DeleteMapping(path="/{positionId}")
    public void deletePosition(@PathVariable Long positionId){
        positionService.deletePosition(positionId);
    }


}
