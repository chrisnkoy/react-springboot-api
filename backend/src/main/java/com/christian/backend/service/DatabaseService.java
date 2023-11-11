package com.christian.backend.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.christian.backend.model.StudentEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class DatabaseService {

    private final DynamoDBMapper dynamoDBMapper;
    public List<StudentEntry> getStudents() {
        DynamoDBScanExpression expression = new DynamoDBScanExpression();
        return dynamoDBMapper.scan(StudentEntry.class, expression);
    }

    public StudentEntry getStudentById(String id) {
        return dynamoDBMapper.load(StudentEntry.class, id);
    }

    public void saveStudent(StudentEntry studentEntry) {
        dynamoDBMapper.save(studentEntry);
    }

    public void deleteStudent(String id) {
        StudentEntry studentEntry = dynamoDBMapper.load(StudentEntry.class, id);
        dynamoDBMapper.delete(studentEntry);
    }

    public void updateStudent(String id, StudentEntry studentEntry) {
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression()
                .withExpectedEntry(
                        "studentId",
                        new ExpectedAttributeValue(
                                new AttributeValue().withS(id)
                        )
                );
        dynamoDBMapper.save(studentEntry, saveExpression);
    }

}
