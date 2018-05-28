package com.example.service;

import com.example.model.Case;
import com.example.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("caseService")
public class CaseServiceImpl implements CaseService {

    @Autowired
    private CaseRepository caseRepository;

    @Override
    public List <Case> getAll(){
        return caseRepository.findAll();
    }

    @Override
    public Case findById(Integer case_id) {
        return caseRepository.findById(case_id);
    }

    @Override
    public void saveCase(Case myCase){
        Case newCase = new Case();
        newCase.setClientName(myCase.getClientName());
        caseRepository.save(newCase);
    }
}
