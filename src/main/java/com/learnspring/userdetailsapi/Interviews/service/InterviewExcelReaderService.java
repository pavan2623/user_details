package com.learnspring.userdetailsapi.Interviews.service;

import com.learnspring.userdetailsapi.Interviews.model.interviewInfo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


import static com.learnspring.userdetailsapi.common.ExcelUtil.*;

@Service
public class InterviewExcelReaderService {
    public List<interviewInfo> readExcelFile(MultipartFile file) throws Exception {
        try (var workbook = new XSSFWorkbook(file.getInputStream())) {
            var sheet = workbook.getSheetAt(2);
            return StreamSupport.stream(sheet.spliterator(), false)
                    .skip(1) // Skip the header row
                    .filter(Objects::nonNull)
                    .map(row -> {
                        var interview = new interviewInfo();
                        interview.setRecruiterName(getStringCellValue(row, 0));
                        interview.setRound(getStringCellValue(row, 1));
                        interview.setInterviewDate(getDateCellValue(row, 2));
                        interview.setTime(row.getCell(3).toString()); // Changed to use .toString()
                        interview.setMode(row.getCell(4).toString());
                        interview.setConsultantName(getStringCellValue(row, 5));
                        interview.setOwnSupport(row.getCell(6).toString()); // Changed to use .toString()
                        interview.setTechnology(getStringCellValue(row, 7));
                        interview.setJobTitle(row.getCell(8).toString()); // Changed to use .toString()
                        interview.setClientType(getStringCellValue(row, 9));
                        interview.setClientName(getStringCellValue(row, 10));
                        interview.setLocation(getStringCellValue(row, 11));
                        interview.setRate(getStringCellValue(row, 12));
                        interview.setVendor(getStringCellValue(row, 13));
                        interview.setFeedback(getStringCellValue(row, 14));
                        interview.setComments(getStringCellValue(row, 15));
                        return interview;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("Error processing Excel file: " + e.getMessage());
        }
    }
}
