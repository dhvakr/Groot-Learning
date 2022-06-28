package com.grootan.excelupload.service;

import com.grootan.excelupload.dao.ExcelDetailsDao;
import com.grootan.excelupload.domain.EntityClass;
import com.grootan.excelupload.utility.ExcelUtilityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ExcelUploadService {
    @Autowired
    ExcelDetailsDao collegeDetailsDao;

    @Autowired
    ExcelUtilityService utilityService;

    public static String columnName;

    @Transactional
    public void processFile(XSSFWorkbook workbook) {
        createTableByExcel(workbook);
        utilityService.save(uploadDetailsByExcel(workbook));
    }

    public List<String> createTableByExcel(XSSFWorkbook workbook) {
        List<String> tableNames = new ArrayList<>();

        XSSFSheet worksheet = workbook.getSheetAt(0);
        XSSFRow row = worksheet.getRow(0);
        log.info("---------------------");
        for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
            columnName = row.getCell(i).getStringCellValue();
            tableNames.add(columnName);
            log.info("Column Names are " + columnName);
        }
        log.info("---------------------");

        return null;
    }

    public List<EntityClass> uploadDetailsByExcel(XSSFWorkbook workbook) {
        XSSFSheet worksheet = workbook.getSheetAt(0);
        List<EntityClass> candidates = new ArrayList<>();

        for (int iteration = 1; iteration < worksheet.getPhysicalNumberOfRows(); iteration++) {
            EntityClass collegeDetails1 = new EntityClass();
            XSSFRow row = worksheet.getRow(iteration);
            if (!Objects.isNull(row) && !Objects.isNull(row.getCell(0))) {
                collegeDetails1.setCollegeID((long) row.getCell(0).getNumericCellValue());
                collegeDetails1.setCollegeName(row.getCell(1).getStringCellValue());
                collegeDetails1.setCollegeType(row.getCell(2).getStringCellValue());

                candidates.add(collegeDetails1);
            }
            log.info("Making Transaction of " + iteration + " Row From Excel Sheet");
        }
        return candidates;
    }
}
