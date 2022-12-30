import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {StudentDataService} from '../servies/student-data.service'
import{Student} from '../student'

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit{

students:any = Student;


constructor(private studentService:StudentDataService, private router: Router){}

  ngOnInit(): void {
    
    this.getStudent()
  }

  getStudent(){
    this.studentService.getStudents().subscribe((data=>{
      this.students = data;
      console.log(data)
    }))
    
  }

// update page
  updateStudent(x:any){
    console.log(x)
    this.router.navigate(['updateStudent', x])
  }

  deletStudentById(x:any){
    this.studentService.deleteStudentBystudentId(x).subscribe((data=>{
      console.log(data);
   
      this.getStudent();
    }))
  }

  addAddressByStudentId(x:any){
  // console.log("hi ")
  this.router.navigate(['saveAddress',x])
  }


}
