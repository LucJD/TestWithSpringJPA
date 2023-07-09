package com.example.Company.Benefit;

import com.example.Company.Exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BenefitService {
    private final BenefitRepository benefitRepository;
    @Autowired
    public BenefitService(BenefitRepository benefitRepository){
        this.benefitRepository = benefitRepository;
    }
    public List<Benefit> getBenefits() {
        return benefitRepository.findAll();
    }

    public Benefit getBenefitById(Long id) {
        return benefitRepository.findById(id).orElseThrow(() -> new ApiRequestException("Benefit by id doesn't exist: " + id));
    }

    public void addNewBenefit(Benefit benefit) {
        Optional<Benefit> benefitByName = benefitRepository.findByName(benefit.getName());
        if(benefitByName.isPresent()){
            throw new ApiRequestException("Benefit by name " + benefit.getName() + " already exists.");
        }else{
            benefitRepository.save(benefit);
            System.out.println("Benefit created.\n" + benefit);
        }
    }

    public void deleteBenefit(Long benefitId) {
        Benefit benefit = getBenefitById(benefitId);
        benefitRepository.delete(benefit);
        System.out.println("Benefit deleted.");
    }
}
