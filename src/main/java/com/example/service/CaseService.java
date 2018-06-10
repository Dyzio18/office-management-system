package com.example.service;


import com.example.model.Case;

import java.util.List;

public interface CaseService {
    List<Case> getAll();
    void saveCase(Case myCase);
    Case findById(Long case_id);
}
