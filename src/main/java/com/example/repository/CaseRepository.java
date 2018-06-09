package com.example.repository;

import com.example.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("caseRepository")
public interface CaseRepository extends JpaRepository<Case,Long> {
   Case findById(Long Id);
}
