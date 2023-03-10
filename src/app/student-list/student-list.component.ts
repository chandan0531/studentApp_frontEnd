import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {StudentDataService} from '../servies/student-data.service'
import{Student} from '../student'
import { StudentResponse } from '../student-response';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit{

  //pagination
  title="";
  pageNo=0;
  pageSize=3;
  sortBy="studentId";
  sortDir = "asc";
  studentResponse:any= StudentResponse;
  pages:any;


students:any = Student;


constructor(private studentService:StudentDataService, private router: Router){}

  ngOnInit(): void {
    
    this.getStudent()
    this.getAllStudentByPagination();
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
    }),error=>alert("UnAuthoried person"))
  }


  addAddressByStudentId(x:any){
  // console.log("hi ")
  this.router.navigate(['saveAddress',x])
  }



  //get All Student by Pagination
  getAllStudentByPagination(){
  
    this.studentService.getAllStudentByPage(this.title,this.pageNo, this.pageSize, this.sortBy, this.sortDir).subscribe(data=>{
      console.log("StudentPage: ", data);
      this.studentResponse = data;
      this.pages = new Array(data['totalPage'])
    },error=> console.log(error))
  }



  setPageNo(val:any){
    this.pageNo= val;
    console.log(this.pageNo, "page no")
    console.log(val, " val")
    this.getAllStudentByPagination();
  }


  setPageNoNext(){
    if(this.pages.length  > this.pageNo){
      console.log(this.pages.length , "length")
     
      this.pageNo++;
      console.log(this.pageNo , " next")
      this.getAllStudentByPagination();
    }
  
   }
   setPageNoPre(){
    if(this.pageNo>0){
      console.log(this.pageNo , " pre")
      this.pageNo--;
      this.getAllStudentByPagination();
    }
    
   }


}
