package com.example.demo.controllerclasses;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtoclasses.StudentResDTO;
import com.example.demo.dtoclasses.StudentRespDTO;
import com.example.demo.entityclasses.Student;
import com.example.demo.serviceclasses.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController
{

	@Autowired
    private StudentService service;


    @PostMapping("/add")
    public StudentResDTO addStudent(@RequestBody Student student) 
    {
    	
    	Student s=service.addStudent(student);
    	StudentResDTO res=new StudentResDTO();
    	
    	if(s!=null)
    	{
    		res.setStatus("OK");
		    res.setMessage("Student data added Successfully");
		    res.setDto(s);    
    	}
    	else
    	{
    		res.setStatus("Error");
    		res.setMessage("Unable to add the student data");
    	}
    	return res;
    }
	
    @GetMapping("/all")
    public StudentRespDTO getAllStudents() 
    {
       List<Student> s= service.getAllStudents();
       StudentRespDTO res=new StudentRespDTO();
       if(s!=null)
       {
    	   res.setStatus("ok");
    	   res.setMessage("Student list fetched successfully");
    	   res.setDto(s);
       }
       else
       {
    	   res.setStatus("Error");
    	   res.setMessage("No student list");
       }
       return res;
    }

    @GetMapping("/{studentId}")
    public StudentResDTO getStudentById(@PathVariable Long studentId) 
    {
       Optional<Student> student= service.getStudentById(studentId);
       StudentResDTO res=new StudentResDTO();
       
       if (student.isPresent()) 
       {
           res.setStatus("OK");
           res.setMessage("Student found");
           res.setDto(student.get());
       } 
       else 
       {
           res.setStatus("ERROR");
           res.setMessage("Student not found with id " + studentId);
       }
       return res;
    }

    @PutMapping("/update/{studentId}")
    public StudentResDTO updateStudent(@PathVariable Long studentId, @RequestBody Student student) 
    {
    	  Student updatedStudent = service.updateStudent(studentId, student);
          StudentResDTO res = new StudentResDTO();

          if (updatedStudent != null) 
          {
              res.setStatus("OK");
              res.setMessage("Student updated successfully");
              res.setDto(updatedStudent);
          } 
          else 
          {
              res.setStatus("ERROR");
              res.setMessage("Student not found with id " + studentId);
          }
          
          return res;
    }

    @DeleteMapping("/delete/{studentId}")
    public StudentResDTO deleteStudent(@PathVariable Long studentId) 
    {
    	boolean deleted = service.deleteStudent(studentId);
        StudentResDTO res = new StudentResDTO();

        if (deleted) 
        {
            res.setStatus("OK");
            res.setMessage("Student deleted successfully");
        } 
        else 
        {
            res.setStatus("ERROR");
            res.setMessage("Student not found with id " + studentId);
        }
        return res;
    }
}
