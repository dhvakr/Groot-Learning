package com.grootan.excelupload;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(name = "ExcelService_Authorization", scheme = "bearer", type = SecuritySchemeType.APIKEY, bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
public class ExcelUploadMainApplication {
    public static void main(String... args) {
        SpringApplication.run(ExcelUploadMainApplication.class, args);
    }
}
