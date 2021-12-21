package com.task.endpoint;

import com.task.service.csv.ImportExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping("/csv")
public class ImportExport {

    private ImportExportService importExportService;

    File file = new File("data.csv");
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

    public void execute() {
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(() -> {
            try {
                importExportService.importCsv(multipartFile);
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }


    @PostMapping("/import")
    public ResponseEntity<?> importCsv(@RequestParam("file") MultipartFile file) throws TransformerException {
        return importExportService.importCsv(file);
    }
}
