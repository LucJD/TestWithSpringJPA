package com.example.Company.Employee;

import com.example.Company.Benefit.Benefit;
import com.example.Company.Benefit.BenefitRepository;
import com.example.Company.Exception.ApiRequestException;
import com.example.Company.Position.Position;
import com.example.Company.Position.PositionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BenefitRepository benefitRepository;
    private final PositionRepository positionRepository;


    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, BenefitRepository benefitRepository, PositionRepository positionRepository){this.employeeRepository = employeeRepository;
    this.benefitRepository = benefitRepository;
        this.positionRepository = positionRepository;
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ApiRequestException("Employee by id doesn't exist: " + id));
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    //throw if not found
    public Benefit getEmployeeBenefitById(Long employeeId, Long benefitId){
        Employee employee = getEmployeeById(employeeId);
        Benefit benefit = employee.getBenefits().stream().filter(b -> b.getId().equals(benefitId)).findAny().orElseThrow(() -> new ApiRequestException("This employee with id "+ employeeId + " does not have benefit with id + " + benefitId));
        return benefit;
    }

    //do not throw if not found
    public boolean checkIfEmployeeHasBenefitById(Long employeeId, Long benefitId){
        Employee employee = getEmployeeById(employeeId);
        boolean found = employee.getBenefits().stream().anyMatch(b -> b.getId().equals(benefitId));
        return found;
    }

    @Transactional
    public void updateEmployee(Long employeeId, String employeeName){
        Employee employee = getEmployeeById(employeeId);
        if(employeeName != null && employeeName.length() > 0 && !Objects.equals(employeeName, employee.getName())){
            employee.setName(employeeName);
        }
    }

    /*
    updateEmployee
    * Long benefitId Nullable if not null update or delete benefit
    * Boolean deleteId Nullable if not null use with benefitId as below
    * *** > check if benefitId is not null and exists in benefit table
    * *** >> if all true, check if benefit exists in employee table, check if deleteId == true, delete benefit by benefitId
    * *** >> if deleteId is null or == false, add benefit by benefitId
    * */
    public void updateEmployeeBenefits(Long employeeId, Long benefitId) {
        Employee employee = getEmployeeById(employeeId);
        if(benefitRepository.existsById(benefitId)){
            Benefit benefit = benefitRepository.getReferenceById(benefitId);
            benefit.addEmployeeWithBenefit(employee);
            benefitRepository.save(benefit);
            System.out.println("Employee updated.\n" + employee.getBenefits());
        }else{
            throw new ApiRequestException("Benefit with id " + benefitId + " does not exist. Cannot update employee.");
        }
    }


    public void updateEmployeePosition(Long employeeId, Long positionId) {
        Employee employee = getEmployeeById(employeeId);
        if (positionRepository.existsById(positionId)){
            employee.setPosition(positionRepository.getReferenceById(positionId));
            employeeRepository.save(employee);
            System.out.println("Position updated. \n"+ employee);
        }else{
            throw new ApiRequestException("Position with id " + positionId + " does not exist. Cannot update employee position");
        }
    }

    public void addNewEmployee(Employee employee) {
        employeeRepository.save(employee); //employee names do not need to be unique, so no checking for unique name
        System.out.println("Employee created.\n" + employee);
    }

    public void deleteEmployee(Long employeeId) {
        Employee employee = getEmployeeById(employeeId);
        employeeRepository.delete(employee);
        System.out.println("Employee deleted.");
    }
}
