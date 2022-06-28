package com.grootan.excelupload.controller;

import com.grootan.excelupload.service.ExcelUploadService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@SecurityRequirement(name = "ExcelService_Authorization")
@RequestMapping("/api")
public class CSVUploadController {
    @Autowired
    ExcelUploadService excelUploadService;

    @PostMapping(value = "/csv/upload", produces = "text/csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Gets a CSV Format File", description = "Uploads a CSV file data to Database")
    public ResponseEntity<Object> uploadFile(@RequestParam("UploadCSV") MultipartFile excelDatafile) {
        String message = "";

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(excelDatafile.getInputStream());

            excelUploadService.processFile(workbook);
            message = "You successfully uploaded " + excelDatafile.getOriginalFilename() + " !!";

            log.info("CSV Data Imported to Database");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            log.error(e.getMessage());
            message = "FAILED to upload, Because - " + excelDatafile.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message + e.getMessage());
        }
    }

    @Hidden
    @GetMapping("/swaggerTerms")
    public String swaggerTerms() {
        return "These terms and conditions outline the rules and regulations for the use of <b>Excel Upload</b> API,\n" +
                "    located at <a href=\"https://www.grootan.com/\">Official Site</a>";
    }
}
