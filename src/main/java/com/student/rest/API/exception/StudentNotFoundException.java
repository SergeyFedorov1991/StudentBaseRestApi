package com.student.rest.API.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends Exception {
  //  private String student_id;

    public StudentNotFoundException() {
    }

    public StudentNotFoundException(Integer student_id) {
        super(String.format("Student is not found with id : '%s'", student_id));
    }

}
