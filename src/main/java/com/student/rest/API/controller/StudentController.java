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

    private Student findNotesById(String id) throws StudentNotFoundException {
        return studentRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new StudentNotFoundException(id));
    }

    @GetMapping
    public Iterable<Student> getAllNotes(Map<String, Object> model){
        Iterable<Student> students = studentRepository.findAll();
        model.put("students", students);
        return students;
    }

    @CrossOrigin
    @DeleteMapping("{id}")
    public void deleteNote(@PathVariable String id) throws StudentNotFoundException {
        studentRepository.delete(findNotesById(id));
    }

    @CrossOrigin
    @GetMapping("{id}")
    public Student getById(@PathVariable String id) throws StudentNotFoundException {
        return findNotesById(id);
    }

    @PostMapping
    public Student addNote(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @CrossOrigin
    @PutMapping("{id}")
    public Student updateNote(@PathVariable String id, @RequestBody Student studentDetails) throws StudentNotFoundException {

        Student student = findNotesById(id);
        student.setName(studentDetails.getName());
        student.setDateOfBirth(studentDetails.getDateOfBirth());
        student.setUniversity(studentDetails.getUniversity());

        return studentRepository.save(student);
    }

}
