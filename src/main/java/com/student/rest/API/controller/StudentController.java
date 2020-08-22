package com.student.rest.API.controller;

import com.student.rest.API.exception.StudentNotFoundException;
import com.student.rest.API.model.Student;
import com.student.rest.API.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    private Student findNotesById(Integer id) throws StudentNotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    @GetMapping
    public Iterable<Student> getAllNotes(Map<String, Object> model){
        Iterable<Student> students = studentRepository.findAll();
        model.put("students", students);
        return students;
    }

    @DeleteMapping("{id}")
    public void deleteNote(@PathVariable Integer id) throws StudentNotFoundException {
        studentRepository.delete(findNotesById(id));
    }

    @GetMapping("{id}")
    public Student getById(@PathVariable Integer id) throws StudentNotFoundException {
        return findNotesById(id);
    }

    @PostMapping
    public Student addNote(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("{id}")
    public Student updateNote(@PathVariable Integer id, @RequestBody Student studentDetails) throws StudentNotFoundException {

        Student student = findNotesById(id);
        student.setName(studentDetails.getName());
        student.setDateOfBirth(studentDetails.getDateOfBirth());
        student.setUniversity(studentDetails.getUniversity());

        return studentRepository.save(student);
    }

}
