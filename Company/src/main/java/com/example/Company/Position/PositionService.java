package com.example.Company.Position;

import com.example.Company.Employee.Employee;
import com.example.Company.Employee.EmployeeRepository;
import com.example.Company.Exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {

    private final PositionRepository positionRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository, EmployeeRepository employeeRepository){this.positionRepository = positionRepository;
        this.employeeRepository = employeeRepository;
    }

    public Position getPositionById(Long id) {
        return positionRepository.findById(id).orElseThrow(() -> new ApiRequestException("No position found with id: " + id));
    }

    public List<Position> getPositions() {
        return positionRepository.findAll();
    }

    public Employee getEmployeeById(Long positionId, Long employeeId){
        Position position = getPositionById(positionId);
        Employee employee = position.getEmployeesWithPosition().stream().filter(e -> e.getId().equals(employeeId)).findAny().orElseThrow(() -> new ApiRequestException("Employee with id " + employeeId + " does not have position with id " + positionId));
        return employee;
    }

    public boolean checkIfPositionIncludesEmployeeById(Long positionId, Long employeeId){
        Position position = getPositionById(positionId);
        boolean found = position.getEmployeesWithPosition().stream().anyMatch(e -> e.getId().equals(employeeId));
        return found;
    }

    public void addEmployeeWithPosition(Long positionId, Long employeeId) {
        Position position = getPositionById(positionId);
        if(employeeRepository.existsById(employeeId)){
            position.addEmployeesWithPosition(employeeRepository.getReferenceById(employeeId));
            positionRepository.save(position);
            System.out.println("Position updated with new employee.\n" + position);
        }else{
            throw new ApiRequestException("Employee with id " + employeeId + "does not exist. Cannot update position.");
        }
    }

    public void addNewPosition(Position position) {
        Optional<Position> positionByName = positionRepository.findByName(position.getName());
        if(positionByName.isPresent()){
            throw new ApiRequestException("Position by the name " + position.getName() + " already exists.");
        }else{
            positionRepository.save(position);
            System.out.println("Position created.\n" + position);
        }
    }

    public void deletePosition(Long positionId) {
        Position position = getPositionById(positionId);
        for(Employee e : position.getEmployeesWithPosition()){
            e.setPosition(null);
        }
        positionRepository.delete(position);
        System.out.println("Position deleted.");
    }
}
