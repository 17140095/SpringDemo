package com.springcrud.model;

import com.springcrud.Route;
import com.springcrud.utils.enums.ApiResponse;
import com.springcrud.utils.exceptions.NoRecordFound;
import com.springcrud.utils.exceptions.RecordAlreadyExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
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
    public ResponseEntity<Map> getAll() {
        paylaod = service.getAll();
        apiResponse = ApiResponse.SUCCESS;
        return ResponseEntity.status(HttpStatus.OK).body(getResponse());
    }
    @GetMapping(path = "{studentId}")
    public ResponseEntity<Map> getById(@PathVariable Long studentId) {
        try {
            paylaod = service.getById(studentId);
            apiResponse = ApiResponse.SUCCESS;
        }catch (NoRecordFound e) {
            apiResponse = ApiResponse.NO_RECORD.setMsg(e.getMessage());
        } catch (Exception e) {
            apiResponse = ApiResponse.UNEXPECTED;
        }
        return ResponseEntity.status(HttpStatus.OK).body(getResponse());
    }
    @PostMapping
    public ResponseEntity<Map> addStudent(@RequestBody Student student) {
        try {
            Map<String,Student> toReturn = new LinkedHashMap<>();
            toReturn.put("New Student", service.addStudent(student));
            apiResponse = ApiResponse.SUCCESS.setMsg("Student added successfully");
            paylaod = toReturn;
        } catch (RecordAlreadyExist e) {
            apiResponse = ApiResponse.ALREADY_EXIST.setMsg(e.getMessage());
        } catch (Exception e) {
            apiResponse = ApiResponse.UNEXPECTED;
        }
        return ResponseEntity.status(HttpStatus.OK).body(getResponse());
    }

    @PutMapping(path = "{studentId}")
    public ResponseEntity<Map> updateStudent(@PathVariable long studentId, @RequestBody Student student){
        try {
            Map<String,Student> toReturn = new LinkedHashMap<>();
            toReturn.put("Updated Student", service.updateStudent(studentId, student));
            apiResponse = ApiResponse.SUCCESS.setMsg("Student updated successfully");
            paylaod = toReturn;
        } catch (NoRecordFound e) {
            apiResponse = ApiResponse.NO_RECORD.setMsg(e.getMessage());
        } catch (Exception e) {
            apiResponse = ApiResponse.UNEXPECTED;
        }

        return ResponseEntity.status(HttpStatus.OK).body(getResponse());
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<Map>  delete(@PathVariable Long studentId) {
        try {
            Map<String,Student> toReturn = new LinkedHashMap<>();
            toReturn.put("Deleted Student", service.deleteStudent(studentId));
            apiResponse = ApiResponse.SUCCESS.setMsg("Student deleted successfully");
            paylaod = toReturn;
        } catch (NoRecordFound e) {
            apiResponse = ApiResponse.NO_RECORD.setMsg(e.getMessage());
        } catch (Exception e) {
            apiResponse = ApiResponse.UNEXPECTED;
        }
        return ResponseEntity.status(HttpStatus.OK).body(getResponse());
    }
}
