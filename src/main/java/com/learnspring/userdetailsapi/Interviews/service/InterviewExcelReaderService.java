package com.learnspring.userdetailsapi.Interviews.service;



import com.learnspring.userdetailsapi.Interviews.model.interviewInfo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import static com.learnspring.userdetailsapi.common.ExcelUtil.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class InterviewExcelReaderService {
    public List<interviewInfo> readExcelFile(MultipartFile file) throws Exception {
        try (var workbook = new XSSFWorkbook(file.getInputStream())) {
            var sheet = workbook.getSheetAt(0); // Assuming first sheet

            return StreamSupport.stream(sheet.spliterator(), false) // Convert the sheet into a Java 8 stream
                    .skip(1) // Skip the header row
                    .map(row -> {
                        var interview = new interviewInfo();
                        interview.setCandidateName(getStringCellValue(row, 0));
                        interview.setPosition(getStringCellValue(row, 1));
                        interview.setInterviewDate(getDateCellValue(row, 2));
                        interview.setStatus(getStringCellValue(row, 3));
                        interview.setInterviewerName(getStringCellValue(row, 4));
                        interview.setLocation(getStringCellValue(row, 5));
                        interview.setFeedback(getStringCellValue(row, 6));
                        interview.setRating(getIntegerCellValue(row, 7));
                        interview.setComments(getStringCellValue(row, 8));
                        return interview;
                    })
                    .collect(Collectors.toList());
        }
    }
}
