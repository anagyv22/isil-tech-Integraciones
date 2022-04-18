package pe.isil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pe.isil.model.Student;
import pe.isil.service.StudentService;

import java.util.List;


@Controller
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String getStudentList(Model model){

        List<Student> students = studentService.getAll();

        model.addAttribute("students", students);

        return "student";
    }
    @GetMapping("/students/add")
    public String addStudent(Model model){

        model.addAttribute("student", new Student());

        return "student-add";
    }

    @PostMapping("/students/save")
    public String saveStudent(Student student, Model model){

        studentService.saveStudent(student);

        return "redirect:/students";
    }

    @GetMapping("/students/edit/{documentNumber}")
    public String studentEdit(@PathVariable String documentNumber, Model model){

        Student currentStudent = studentService.findByDocumentNumber(documentNumber);

        model.addAttribute("currentStudent", currentStudent);

        return "student-edit";
    }

    @PostMapping("/students/update")
    public String studentUpdate(Student student){

        studentService.updateStudent(student);

        return "redirect:/students";
    }

    @GetMapping("/students/delete/{documentNumber}")
    public String studentDelete(@PathVariable String documentNumber){

        Student currentStudent = studentService.findByDocumentNumber(documentNumber);
        if (currentStudent != null)
            studentService.removeStudent(currentStudent);

        return "redirect:/students";
    }


}
