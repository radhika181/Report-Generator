package com.report.generator.netw.service;

import com.report.generator.netw.model.InputRecord;
import com.report.generator.netw.model.OutputRecord;
import com.report.generator.netw.model.ReferenceRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReportGenerator {

    public List<OutputRecord> generateReport(List<InputRecord> inputRecords, List<ReferenceRecord> referenceRecords) {
        log.info("Generating report");
        Map<String, ReferenceRecord> referenceMap = referenceRecords.stream()
                .collect(Collectors.toMap(
                        record -> record.getRefkey1() + "_" + record.getRefkey2(),
                        record -> record
                ));

        return inputRecords.stream().map(inputRecord -> {
            String refKey = inputRecord.getRefkey1() + "_" + inputRecord.getRefkey2();
            ReferenceRecord referenceRecord = referenceMap.get(refKey);

            OutputRecord outputRecord = new OutputRecord();
            outputRecord.setOutfield1(inputRecord.getField1() + inputRecord.getField2());
            outputRecord.setOutfield2(referenceRecord.getRefdata1());
            outputRecord.setOutfield3(referenceRecord.getRefdata2() + referenceRecord.getRefdata3());

            BigDecimal maxField5AndRefData4 = inputRecord.getField5().max(referenceRecord.getRefdata4());
            outputRecord.setOutfield4(new BigDecimal(inputRecord.getField3()).multiply(maxField5AndRefData4));
            outputRecord.setOutfield5(maxField5AndRefData4);

            return outputRecord;
        }).collect(Collectors.toList());
    }
}
