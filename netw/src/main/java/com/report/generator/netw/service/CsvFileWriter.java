package com.report.generator.netw.service;

import com.opencsv.CSVWriter;
import com.report.generator.netw.model.OutputRecord;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class CsvFileWriter {

    public void writeOutputFile(String filePath, List<OutputRecord> outputRecords) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            String[] header = {"outfield1", "outfield2", "outfield3", "outfield4", "outfield5"};
            writer.writeNext(header);

            for (OutputRecord record : outputRecords) {
                String[] data = {
                        record.getOutfield1(),
                        record.getOutfield2(),
                        record.getOutfield3(),
                        record.getOutfield4().toString(),
                        record.getOutfield5().toString()
                };
                writer.writeNext(data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
