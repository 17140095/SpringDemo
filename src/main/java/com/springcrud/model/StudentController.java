package com.springcrud.model;

import com.springcrud.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = Route.STUDENT_ROUTE)
public class StudentController extends BaseController {
    private final StudentService service;

    @Autowired
    StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }
    @GetMapping(path = "{studentId}")
    public ResponseEntity<Map> getById(@PathVariable Long studentId) {
        try {
            paylaod = service.getById(studentId);
            responseCode = "00";
            responseDesc = "Success";
        }catch (IllegalStateException e) {
            responseCode = "404";
            responseDesc = e.getMessage();
        }
        return ResponseEntity.status(HttpStatus.OK).body(getResponse());
    }
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.OK).body(service.addStudent(student));
    }

    @PutMapping(path = "{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable long studentId, @RequestBody Student student){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateStudent(studentId, student));
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<Student>  delete(@PathVariable Long studentId) {
        service.deleteStudent(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteStudent(studentId));
    }
}
