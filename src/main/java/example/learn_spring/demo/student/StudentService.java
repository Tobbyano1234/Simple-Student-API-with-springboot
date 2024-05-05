package example.learn_spring.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student addNewStudent(Student student) {
        Optional<Student> optionalStudent = studentRepository.findStudentByEmail(student.getEmail());
        if (optionalStudent.isPresent()) {
            throw new IllegalStateException("email already used");
        }
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(Student student, Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isEmpty()) {
            throw new IllegalStateException("student not found");
        }

        if (student.getName() != null && student.getName().length() > 0 && !Objects.equals(student.getName(), optionalStudent.get().getName())) {
            optionalStudent.get().setName(student.getName());
            optionalStudent.get().setEmail(student.getEmail());
        }

        return studentRepository.findById(studentId).orElseThrow(() ->
                new IllegalStateException("student not found"));
    }

    public Student deleteStudent(Long studentId) {
        boolean studentExist = studentRepository.existsById(studentId);
        if (!studentExist) {
            throw new IllegalStateException("Student not found");
        }
        studentRepository.deleteById(studentId);
        return null;
    }
}
