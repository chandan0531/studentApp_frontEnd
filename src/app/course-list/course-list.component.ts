import { Component,OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { Course } from '../course';
import { CourseResponse } from '../course-response';
import { StudentDataService } from '../servies/student-data.service';
import { Student } from '../student';


@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit{

  //pagination
  title="";
  pageNo=0;
  pageSize=3;
  sortBy="courseId";
  sortDir = "asc";
  courseRespone:any= CourseResponse;
  pages:any;

  flag = true;
  buttonTextD = "sort";
  buttonTextDes = "sort"
  buttonTextCI ="sort"
  buttonTextCN = "sort"
  buttonTextT = "sort"
  buttonTextCT = "sort"

  courses:any=Course;
  students:any=Student;

  

  constructor(private studentService:StudentDataService,
    private router: Router){}

  ngOnInit(): void {
    
    this.getCourse();

    this.getAllStudent();

    this.getAllCourseByPagination();
  
  }

// get All courses;
  getCourse(){
    this.studentService.getAllCourses().subscribe(data=>{

      // console.log(data);
      this.courses = data;
    })
  }

  // get All Student;
  getAllStudent(){
    this.studentService.getStudents().subscribe(data=>{
      this.students = data;
      console.log("Student data : " + this.students[0].course.courseId)
    })
  }



  updateCourseById(x:any){

    this.router.navigate(['updateCourse',x]);
    
  }


  addStudentByCourseId(x:any){

    var flag = true;

    for(var i =0; i<this.students.length; i++)
    {
      if(this.students[i].course.courseId==x)
      {
        flag = false;
        alert("Student is alraedy added with courseId : " + x)
      }
    }
    if(flag==true)
      this.router.navigate(['saveStudent',x])
    else
    flag = true;
    
  }


// get All Course By Pagination
 getAllCourseByPagination(){

  this.studentService.getAllCoursesByPage(this.title,this.pageNo, this.pageSize, this.sortBy, this.sortDir).subscribe(data=>{
   this.courseRespone = data.content;
   this.pages = new Array(data['totalPages']);
   console.log("total pages: " , this.pages )
  //  console.log("page data1 : " , this.courseRespone)
    // console.log("page data : " , data)
  }, error => console.log(error.error))
 }


 sortByDuration(){
  this.buttonTextDes = "sort"
  this.buttonTextCI = "sort"
  this.buttonTextCN = "sort"
  this.buttonTextT = "sort"
  this.buttonTextCT = "sort"
  if(this.flag===true){
    // this.sortBy = "duration"
    // this.sortDir = "dsc"
    this.buttonTextD = "dsc"
    this.flag = false;
    // this.getAllCourseByPagination();

    this.courseRespone.sort((a:any,b:any)=>{
      if(a.duration < b.duration){
        return 1;
      }
      else{
        return -1;
      }
    })
    

  }
  else{
    // this.sortBy = "duration"
    // this.sortDir = "asc"
    this.buttonTextD = "asc"
    this.flag = true;
    // this.getAllCourseByPagination();
    this.courseRespone.sort((a:any,b:any)=>{
      if(a.duration > b.duration){
        return 1;
      }
      else{
        return -1;
      }
    })

  }
  
 }

 sortByDescription(){
  this.buttonTextD = "sort"
  this.buttonTextCI = "sort"
  this.buttonTextCN = "sort"
  this.buttonTextT = "sort"
  this.buttonTextCT = "sort"
  if(this.flag===true){
    this.buttonTextDes = "dsc"
    this.flag = false;
   
    this.courseRespone.sort((a:any,b:any)=>{
      if(a.description < b.description) return 1;
      else return -1;
    })

  }
  else{
    this.buttonTextDes = "asc"
    this.flag = true;

    this.courseRespone.sort((a:any,b:any)=>{
      if(a.description > b.description) return 1;
      else return -1;
    })
  }
 }

 sortByCourseId(){
  this.buttonTextD = "sort"
  this.buttonTextDes = "sort"
  this.buttonTextCN = "sort"
  this.buttonTextT = "sort"
  this.buttonTextCT = "sort"
  if(this.flag===true){
    this.buttonTextCI = "dsc"
    this.flag = false;
    this.courseRespone.sort((a:any,b:any)=>{
      if(a.courseId < b.courseId) return 1;
      else return -1;
    })
  }
  else{
    this.buttonTextCI = "asc"
    this.flag = true;
    this.courseRespone.sort((a:any,b:any)=>{
      if(a.courseId > b.courseId) return 1;
      else return -1;
    })
  }
 }


 sortByCourseName(){
  this.buttonTextD = "sort"
  this.buttonTextDes = "sort"
  this.buttonTextCI = "sort"
  this.buttonTextT = "sort"
  this.buttonTextCT = "sort"
  if(this.flag===true){
    this.buttonTextCN = "dsc"
    this.flag = false;
    this.courseRespone.sort((a:any,b:any)=>{
      if(a.courseName < b.courseName) return 1;
      else return -1;
    })
  }
  else{
    this.buttonTextCN = "asc"
    this.flag = true;
    this.courseRespone.sort((a:any,b:any)=>{
      if(a.courseName > b.courseName) return 1;
      else return -1;
    })
  }
 }

 sortByTopic(){
  this.buttonTextD = "sort"
  this.buttonTextDes = "sort"
  this.buttonTextCI = "sort"
  this.buttonTextCN = "sort"
  this.buttonTextCT = "sort"
  if(this.flag===true){
    this.buttonTextT = "dsc"
    this.flag = false;
    this.courseRespone.sort((a:any,b:any)=>{
      if(a.topics < b.topics) return 1;
      else return -1;
    })
  }
  else{
    this.buttonTextT = "asc"
    this.flag = true;
    this.courseRespone.sort((a:any,b:any)=>{
      if(a.topics > b.topics) return 1;
      else return -1;
    })
  }

 }

 sortByCourseType(){
  this.buttonTextD = "sort"
  this.buttonTextDes = "sort"
  this.buttonTextCI = "sort"
  this.buttonTextT = "sort"
  if(this.flag===true){
    this.buttonTextCT = "dsc"
    this.flag = false;
    this.courseRespone.sort((a:any,b:any)=>{
      if(a.courseType < b.courseType) return 1;
      else return -1;
    })
  }
  else{
    this.buttonTextCT = "asc"
    this.flag = true;
    this.courseRespone.sort((a:any,b:any)=>{
      if(a.courseType > b.courseType) return 1;
      else return -1;
    })
  }

 }


 setPageNo(i:any){
  this.pageNo = i;
  this.getAllCourseByPagination();
 

 }


 setPageNoNext(){
  if(this.pages.length  > this.pageNo + 1){
    this.pageNo++;
    this.getAllCourseByPagination();
  }

 }
 setPageNoPre(){
  if(this.pageNo>0){
    this.pageNo--;
    this.getAllCourseByPagination();
  }
  
 }


 getPageSize(val:any){
  console.log(val)
  this.pageSize = val;
  this.getAllCourseByPagination();
  
 }


 getData(box:any){

  // console.log(this.pageNo, "my check")
  this.title = box;
  this.pageNo=0;
  this.getAllCourseByPagination();
 }


}
