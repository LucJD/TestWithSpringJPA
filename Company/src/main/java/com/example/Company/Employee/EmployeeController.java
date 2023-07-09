package com.example.Company.Employee;

import com.example.Company.Benefit.Benefit;
import com.example.Company.Benefit.BenefitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){ this.employeeService = employeeService;}

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getEmployees();
    }
    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable Long employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    public void addNewEmployee(@RequestBody Employee employee){
        employeeService.addNewEmployee(employee);
    }
    @PutMapping(path="{employeeId}")
    public void updateEmployee(@PathVariable Long employeeId, @RequestParam String employeeName){
        employeeService.updateEmployee(employeeId, employeeName);
    }
    @RequestMapping(value = "{employeeId}/benefits/{benefitId}", method = {RequestMethod.GET, RequestMethod.PUT})
    public void updateEmployeeBenefits(@PathVariable Long employeeId, @PathVariable Long benefitId){
        employeeService.updateEmployeeBenefits(employeeId, benefitId);
    }
    @RequestMapping(value = "/{employeeId}/positions/{positionId}", method = {RequestMethod.GET, RequestMethod.PUT})
    public void updateEmployeePosition(@PathVariable Long employeeId, @PathVariable Long positionId){
        employeeService.updateEmployeePosition(employeeId, positionId);
    }
    @DeleteMapping(path="/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId){
        employeeService.deleteEmployee(employeeId);
    }
}
