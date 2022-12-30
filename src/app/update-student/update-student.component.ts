import { Component,OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentDataService } from '../servies/student-data.service';
import { Student } from '../student';

@Component({
  selector: 'app-update-student',
  templateUrl: './update-student.component.html',
  styleUrls: ['./update-student.component.css']
})
export class UpdateStudentComponent implements OnInit{

  id :any;
  student: Student = new Student();

  constructor(private studentService:StudentDataService, 
    private route: ActivatedRoute,
  private router: Router ) {}
  ngOnInit(): void {
    
    this.id = this.route.snapshot.params['id'];
    this.studentService.getStudentByStudentId(this.id).subscribe(data=>{
      this.student = data;
      console.log("data : " + data.name + this.id)
    }, error=> console.log(error))

    // this.studentService.getStudentByStudentId(this.id).subscribe((data=>{
    //   this.student = data;
    //   console.log("data : " + data.name)
    // }))


  }

//update
onSubmit(){

  this.studentService.updateStudentByStudentId(this.student).subscribe(data=>{
    this.goToStudentList();
  }, error=>
  alert(error.error.message)
  // console.log("error : " + error.error.message)
  )
  

}
  
goToStudentList(){
  this.router.navigate(['/students']);
}


}
