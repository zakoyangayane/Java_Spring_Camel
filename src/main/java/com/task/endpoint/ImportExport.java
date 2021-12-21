package com.task.endpoint;

import com.task.service.csv.ImportExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequiredArgsConstructor
@RequestMapping("/csv")
public class ImportExport {

    private final ImportExportService importExportService;

    File file = new File("csv/data.csv");
    FileInputStream input;
    MultipartFile multipartFile;

    {
        try {
            input = new FileInputStream(file);
            multipartFile = new MockMultipartFile("file",
                    file.getName(), "text/plain", Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(initialDelay = 15000, fixedRate = 45000)
    public void execute() {
        try {
            importExportService.importCsv(multipartFile);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/import")
    public ResponseEntity<?> importCsv(@RequestParam("file") MultipartFile file) throws TransformerException {
        return importExportService.importCsv(file);
    }
}
