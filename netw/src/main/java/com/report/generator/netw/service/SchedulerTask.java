package com.report.generator.netw.service;

import com.report.generator.netw.model.InputRecord;
import com.report.generator.netw.model.OutputRecord;
import com.report.generator.netw.model.ReferenceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SchedulerTask {


    @Autowired
    private CsvFileReader csvFileReader;

    @Autowired
    private ReportGenerator reportGenerator;

    @Autowired
    private CsvFileWriter csvFileWriter;


    @Scheduled(cron = "${report.schedule}") // Example: daily at midnight
    public void generateReports() {
        try {
            List<InputRecord> inputRecords = csvFileReader.readInputFile("path/to/input.csv");
            List<ReferenceRecord> referenceRecords = csvFileReader.readReferenceFile("path/to/reference.csv");

            List<OutputRecord> outputRecords = reportGenerator.generateReport(inputRecords, referenceRecords);

            csvFileWriter.writeOutputFile("path/to/output.csv", outputRecords);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
