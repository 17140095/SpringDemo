package com.springcrud.model;

import com.springcrud.utils.exceptions.NoRecordFound;
import com.springcrud.utils.exceptions.RecordAlreadyExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
      }
    public List<Student> getAll() {
        return this.repository.findAll();
    }
    public Student getById(Long studentId) throws NoRecordFound {
        Optional<Student> student = this.repository.findStudentById(studentId);
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new NoRecordFound("Student of id " + studentId + " not found");
        }
    }
    public Student deleteStudent(Long studentId) throws NoRecordFound {
        Optional<Student> toDelete = this.repository.findStudentById(studentId);
        if (toDelete.isPresent()) {
            this.repository.delete(toDelete.get());
            return toDelete.get();
        } else {
            throw new NoRecordFound("No student found of this "+studentId+" ID.");
        }
    }

    public Student addStudent(Student student) throws RecordAlreadyExist {
        if (this.repository.findStudentByEmail(student.getEmail()).isPresent()) {
            throw new RecordAlreadyExist("This email is already exist");
        } else {
            return this.repository.save(student);
        }
    }

    public Student updateStudent(long studentId, Student toUpdate) throws NoRecordFound {
       Optional<Student> optionalStudent = this.repository.findStudentById(studentId);
       if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.setEmail(toUpdate.getEmail());
            existingStudent.setName(toUpdate.getName());
            existingStudent.setDob(toUpdate.getDob());
            existingStudent.setAddress(toUpdate.getAddress());
            return this.repository.save(existingStudent);
        } else {
           throw new NoRecordFound("Student of id " + studentId + " not exist");
       }
    }
}
