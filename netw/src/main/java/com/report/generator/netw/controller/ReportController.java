package com.report.generator.netw.controller;

import com.report.generator.netw.model.InputRecord;
import com.report.generator.netw.model.OutputRecord;
import com.report.generator.netw.model.ReferenceRecord;
import com.report.generator.netw.service.CsvFileReader;
import com.report.generator.netw.service.CsvFileWriter;
import com.report.generator.netw.service.ReportGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private CsvFileReader csvFileReader;

    @Autowired
    private ReportGenerator reportGenerator;

    @Autowired
    private CsvFileWriter csvFileWriter;

    @PostMapping("/generate")
    public ResponseEntity<String> generateReport(@RequestParam("inputFilePath") String inputFilePath,
                                                 @RequestParam("referenceFilePath") String referenceFilePath) {
        try {
            log.info("Generating report for input file: {} and reference file: {}", inputFilePath, referenceFilePath);
            List<InputRecord> inputRecords = csvFileReader.readInputFile(inputFilePath);
            List<ReferenceRecord> referenceRecords = csvFileReader.readReferenceFile(referenceFilePath);

            List<OutputRecord> outputRecords = reportGenerator.generateReport(inputRecords, referenceRecords);

            csvFileWriter.writeOutputFile("path/to/output.csv", outputRecords);

            return ResponseEntity.ok("Report generated successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error generating report");
        }
    }
}
