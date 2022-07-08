package com.tmasolutions.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmasolutions.file.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/utils")
public class UtilsController {
    @Autowired
    StorageService storageService;

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestPart(required = true)MultipartFile file)
            throws JsonMappingException, JsonProcessingException {
        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSSS_");
        String currentDateTime = dateFormatter.format(new Date());
        storageService.saveAs(file, "uploads/" + currentDateTime + file.getOriginalFilename());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree("{\"Success\": \"True\"}");
        return ResponseEntity. ok(json);
    }
}
