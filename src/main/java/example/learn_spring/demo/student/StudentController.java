package example.learn_spring.demo.student;

import example.learn_spring.demo.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getStudents() {
        try {
            List<Student> students = studentService.getStudents();
            ApiResponse<List<Student>> response = new ApiResponse<List<Student>>();
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Students fetched successfully");
            response.setData(students);
            return ResponseEntity.ok(response);
        } catch (IllegalStateException ex) {
            ApiResponse<List<Student>> response = new ApiResponse<List<Student>>();
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setError(ex.getMessage());
            response.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> registerNewStudent(@RequestBody Student student) {
        try {
            Student newStudent = studentService.addNewStudent(student);
            ApiResponse<Student> response = new ApiResponse<Student>();
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Student created successfully");
            response.setData(newStudent);
            return ResponseEntity.ok(response);
        } catch (IllegalStateException ex) {
            ApiResponse<Student> response = new ApiResponse<Student>();
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setError(ex.getMessage());
            response.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(@RequestBody Student student, @PathVariable Long studentId) {
        try {
            Student updated = studentService.updateStudent(student, studentId);
            ApiResponse<Student> response = new ApiResponse<Student>();
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Student updated successfully");
            response.setData(updated);
            return ResponseEntity.ok(response);
        } catch (IllegalStateException ex) {
            ApiResponse<Student> response = new ApiResponse<Student>();
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setError(ex.getMessage());
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<ApiResponse<Student>> deleteStudent(@PathVariable Long studentId) {
        try {
            Student updated = studentService.deleteStudent(studentId);
            ApiResponse<Student> response = new ApiResponse<Student>();
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Student deleted successfully");
            response.setData(null);
            return ResponseEntity.ok(response);
        } catch (IllegalStateException ex) {
            ApiResponse<Student> response = new ApiResponse<Student>();
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setError(ex.getMessage());
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
