package com.grootan.excelupload.utility;

import com.grootan.excelupload.dao.ExcelDetailsDao;
import com.grootan.excelupload.domain.EntityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelUtilityService {
    @Autowired
    ExcelDetailsDao collegeDetailsDao;

    public List<EntityClass> findAll() {
        return collegeDetailsDao.findAll();
    }

    // To save to DB
    public void save(EntityClass candidate) {
        collegeDetailsDao.save(candidate);
    }

    public void save(List<EntityClass> candidates) {
        for (EntityClass collegeDetails : candidates) {
            save(collegeDetails);
        }
    }
}
