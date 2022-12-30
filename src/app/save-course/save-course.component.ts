import { Component , OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { Course } from '../course';
import {StudentDataService} from '../servies/student-data.service'

@Component({
  selector: 'app-save-course',
  templateUrl: './save-course.component.html',
  styleUrls: ['./save-course.component.css']
})
export class SaveCourseComponent implements OnInit{

  course: Course = new Course();

  constructor(private studentService:StudentDataService, private router:Router){}

  ngOnInit(): void {
    
  }


saveCourse(){
  this.studentService.addCourse(this.course).subscribe((data=>{
    console.log(data);
    
  }))
  // this.getToCourseList()
  
}

// getToCourseList(){
//   this.router.navigate(['/courses'])
// }

onSubmit(){
  this.saveCourse();
  this.router.navigate(['courses'])
}



}
