package com.example.Company.Benefit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/benefits")
public class BenefitController {

    private final BenefitService benefitService;

    @Autowired
    public BenefitController(BenefitService benefitService){ this.benefitService = benefitService;}

    @GetMapping
    public List<Benefit> getAllBenefits(){
        return benefitService.getBenefits();
    }
    @GetMapping("/{benefitId}")
    public Benefit getBenefitById(@PathVariable Long benefitId){
        return benefitService.getBenefitById(benefitId);
    }

    @PostMapping
    public void addNewBenefit(@RequestBody Benefit benefit){
        benefitService.addNewBenefit(benefit);
    }

    @DeleteMapping(path="/{benefitId}")
    public void deleteBenefit(@PathVariable Long benefitId){
        benefitService.deleteBenefit(benefitId);
    }

}
