import { Component,OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentDataService } from '../servies/student-data.service';
import { Student } from '../student';
import { StudentDto } from '../student-dto';

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.css']
})
export class AddStudentComponent implements OnInit {

  //mobile no. error
  message:any;

//All student list
  students:any=Student;

id:any;
student:Student = new Student();


studentDto:StudentDto = new StudentDto;

  constructor(private studentService:StudentDataService, private router:Router,
    private route:ActivatedRoute){}


  ngOnInit(): void {
    
    this.id =  this.route.snapshot.params['id'];
    this.studentService.getCourseByCourseId(this.id).subscribe(data=>{
      console.log(data + this.id)
    },error=> console.log(error))

    this.getAllStudent();

    this.studentDto.courseId = this.id;
  }




  saveStudentByCourseId(){
    this.studentService.addStudentByCourseId(this.studentDto).subscribe(data=>{
      console.log(data  + "student saved")
    }, error=>
    this.message = error.error.message
    // console.log("My error: " + error.error.message)
    // alert(error.error.message)
 
    )
    // console.log(this.message)
    if(this.message.length > 0)
    alert(this.message)
    // console.log(this.message + " vvgvgvg")
    else  
    this.goToStudentList();
  }


  onSubmit(){
    // console.log("chandan check : " +  this.student.cellPhone)

    // let flag = true;
    // for(var i=0; i<this.students.length; i++)
    // {
    //   if(this.students[i].cellPhone == this.student.cellPhone)
    //   {
    //     flag = false;
    //     alert("Student is already available with mobile no. : " + this.student.cellPhone)
    //   }
    // }

    // if(flag==true)
    this.saveStudentByCourseId();
    // else
    // flag= false;
    
  }



  goToStudentList(){
    this.router.navigate(['/students']);
  }



//get all students
getAllStudent(){
  this.studentService.getStudents().subscribe(data=>{
    this.students = data;
    console.log("Student data : " + this.students[0].cellPhone)
  })
}


}
