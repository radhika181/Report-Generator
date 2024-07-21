package com.report.generator.netw.service;


import com.report.generator.netw.model.InputRecord;
import com.report.generator.netw.model.OutputRecord;
import com.report.generator.netw.model.ReferenceRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReportGeneratorTest {

    @Autowired
    private ReportGenerator reportGenerator;

    @Test
    public void testGenerateReport() {
        InputRecord inputRecord = new InputRecord();
        inputRecord.setField1("value1");
        inputRecord.setField2("value2");
        inputRecord.setField3("3");
        inputRecord.setField4(new BigDecimal("4"));
        inputRecord.setField5(new BigDecimal("5"));
        inputRecord.setRefkey1("refKey1");
        inputRecord.setRefkey2("refKey2");

        ReferenceRecord referenceRecord = new ReferenceRecord();
        referenceRecord.setRefkey1("refKey1");
        referenceRecord.setRefdata1("refData1");
        referenceRecord.setRefkey2("refKey2");
        referenceRecord.setRefdata2("refData2");
        referenceRecord.setRefdata3("refData3");
        referenceRecord.setRefdata4(new BigDecimal("6"));

        List<OutputRecord> outputRecords = reportGenerator.generateReport(
                List.of(inputRecord),
                List.of(referenceRecord)
        );

        assertNotNull(outputRecords);
        assertFalse(outputRecords.isEmpty());
        OutputRecord outputRecord = outputRecords.get(0);
        assertEquals("value1value2", outputRecord.getOutfield1());
        assertEquals("refData1", outputRecord.getOutfield2());
        assertEquals("refData2refData3", outputRecord.getOutfield3());
        assertEquals(new BigDecimal("18"), outputRecord.getOutfield4());
        assertEquals(new BigDecimal("6"), outputRecord.getOutfield5());
    }
}
