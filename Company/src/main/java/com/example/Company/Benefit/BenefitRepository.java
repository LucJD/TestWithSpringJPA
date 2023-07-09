package com.example.Company.Benefit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BenefitRepository extends JpaRepository<Benefit, Long> {

    @Query("SELECT b FROM Benefit b WHERE b.name=?1")
    Optional<Benefit> findByName(String name);
}
