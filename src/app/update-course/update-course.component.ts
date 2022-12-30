import { Component,OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from '../course';
import { StudentDataService } from '../servies/student-data.service';

@Component({
  selector: 'app-update-course',
  templateUrl: './update-course.component.html',
  styleUrls: ['./update-course.component.css']
})
export class UpdateCourseComponent implements OnInit {

id:any;
course:Course = new Course();


  constructor(private studentService:StudentDataService, 
    private route: ActivatedRoute,
  private router: Router ) {}

  ngOnInit(): void {
   this.id =  this.route.snapshot.params['id'];
    this.studentService.getCourseByCourseId(this.id).subscribe(data=>{
      this.course = data;
      console.log(data)
    },error=> console.log(error))

  }


  onSubmit(){
    
  }
}
