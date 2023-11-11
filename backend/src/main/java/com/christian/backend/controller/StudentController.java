package com.christian.backend.controller;

import com.christian.backend.model.StudentEntry;
import com.christian.backend.service.DatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final DatabaseService databaseService;

    @GetMapping
    public List<StudentEntry> getStudents() {
        return databaseService.getStudents();
    }

    @GetMapping("/{id}")
    public StudentEntry getStudentById(@PathVariable String id){
        return databaseService.getStudentById(id);
    }

    @PostMapping("/student")
    public String postStudent(@RequestBody StudentEntry studentEntry) {
        databaseService.saveStudent(studentEntry);
        return "Student has been added!";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable String id){
        databaseService.deleteStudent(id);
        return "Student " + id + " has been deleted!";
    }

    @PutMapping("/{id}")
    public String updateStudent(@PathVariable String id, @RequestBody StudentEntry studentEntry){
        databaseService.updateStudent(id, studentEntry);
        return "Student " + id + " has been updated!";
    }
}
