//package com.tmasolutions.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.tmasolutions.file.StorageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@RestController
//@RequestMapping("/utils")
//public class UtilsController {
//    @Autowired
//    StorageService storageService;
//
//    @PostMapping("/uploadFile")
//    public ResponseEntity<?> uploadFile(@RequestPart(required = true)MultipartFile file)
//            throws JsonMappingException, JsonProcessingException {
//        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS_");
//        String currentDateTime = dateFormatter.format(new Date());
//        storageService.saveAs(file, "uploads/" + currentDateTime + file.getOriginalFilename());
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode json = mapper.readTree("{\"Success\": \"True\"}");
//        return ResponseEntity. ok(json);
//    }
//}
package com.tmasolutions.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmasolutions.factory.doc.MailMergeBaocao;
import com.tmasolutions.factory.excel.BookExcelService;
import com.tmasolutions.utils.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/utils")
public class UtilsController {
    @Autowired
    MailMergeBaocao mailMergeToTrinhThanhToan;
    @PostMapping("/mailMergeToTrinhThanhToan")
    public ResponseEntity<Resource> mailMergeToTrinhThanhToan() throws Exception {
        String pdfPath = "";
        try {
            pdfPath = mailMergeToTrinhThanhToan.MailMergeData();
            File file = new File(pdfPath);
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(file.toPath()));

            HttpHeaders headers = new HttpHeaders();
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=to-trinh-thanh-toan.pdf";
            headers.set(headerKey, headerValue);

            return ResponseEntity.ok().headers(headers).contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
        } finally {
            File file = new File(pdfPath);
            Files.delete(file.toPath());
        }

    }



    @Autowired
    com.tmasolutions.factory.file.StorageService filesStorageService;


    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestPart(required = true) MultipartFile file)
            throws JsonMappingException, JsonProcessingException {


        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS_");
        String currentDateTime = dateFormatter.format(new Date());
        filesStorageService.saveAs(file, "uploads/" + currentDateTime + file.getOriginalFilename() );
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree("{\"Success\": \"True\"}");
        return ResponseEntity.ok(json);
    }

    @GetMapping("/dowloadfile")
    public ResponseEntity<Resource> dowloadfile() throws Exception {
        Resource file = filesStorageService.load("uploads/test.xlsx");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @Autowired
    BookExcelService fileService;

    @PostMapping("/importBook")
    public ResponseEntity<?> uploadBookFile(@RequestPart(required = true) MultipartFile file)
            throws JsonMappingException, JsonProcessingException {
        String message = "";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree("{\"Message\": \"Inccorrect excel format\"}");
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                fileService.importBookFromExcel(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                json = mapper.readTree("{\"Success\": \"True\"}");
                return ResponseEntity.ok(json);
            } catch (Exception e) {
                json = mapper.readTree("{\"Success\": \"False\", \"Message\"" + e.toString() + "}");
                return ResponseEntity.ok(json);
            }
        }
        return ResponseEntity.ok(json);
    }

    @GetMapping("/exportAllBook")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=book_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        fileService.exportAllBook(response);
    }

}