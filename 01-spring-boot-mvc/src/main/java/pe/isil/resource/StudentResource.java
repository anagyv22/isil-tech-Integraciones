package pe.isil.resource;


import org.springframework.web.bind.annotation.*;
import pe.isil.model.Student;
import pe.isil.service.StudentService;

import java.util.List;

@RequestMapping("/api")
@RestController
public class StudentResource {
    private final StudentService studentService;

    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAll(){
        List<Student> studentList = studentService.getAll();
        return studentList;
    }

    @GetMapping("/students/{documentNumber}")
    public Student getByDocumentNumber(@PathVariable String documentNumber){
        Student currentStudent = studentService.findByDocumentNumber(documentNumber);
        return currentStudent;
    }

    @PostMapping("/students")
    public Student save(@RequestBody Student student){
        studentService.saveStudent(student);
        return student;
    }


}
