package pe.isil.service;

import org.springframework.stereotype.Service;
import pe.isil.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentService {

    List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student("00001", "Jose", "Ventura", "Arteaga", LocalDate.of(1985,3,31)),
                    new Student("00002", "Marcos", "Matos", "Matos", LocalDate.of(1990,2,10))
            )
    );

    public List<Student> getAll(){
        return students;
    }

    public void saveStudent(Student student){
        students.add(student);
    }

    public void removeStudent(Student student){
        students.remove(student);
    }

    public void updateStudent(Student student){
        String documentNumber = student.getDocumentNumber();
        Student currentStudent = findByDocumentNumber(documentNumber);
        int index = students.indexOf(currentStudent);
        students.set(index, student);
    }

    public Student findByDocumentNumber(String documentNumber) {

        return students.stream()
                .filter(s -> s.getDocumentNumber().equalsIgnoreCase(documentNumber))
                .findFirst()
                .orElseGet(null);
    }

}
