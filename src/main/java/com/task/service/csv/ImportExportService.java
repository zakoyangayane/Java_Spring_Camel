package com.task.service.csv;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.TransformerException;

public interface ImportExportService {

    ResponseEntity<?> importCsv(MultipartFile file) throws TransformerException;
}
