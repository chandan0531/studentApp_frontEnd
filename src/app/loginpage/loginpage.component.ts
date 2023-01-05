import { Component,OnInit, Output,EventEmitter } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentDataService } from '../servies/student-data.service';

@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css']
})
export class LoginpageComponent {

  username:any;
  password:any;

@Output() event = new EventEmitter<any>();

  constructor(private studentService:StudentDataService, private router:Router,
  private route:ActivatedRoute){}

  ngOnInit(): void {
    this.getAutho();
    
  }


  getAutho(){
    this.studentService.getAuthorised().subscribe(data=>{
      console.log(data, "autho");
    }, error=>console.log(error))
  }

  onSubmit(){
    this.router.navigate(['/students']);
    localStorage.setItem("username", this.username);
    localStorage.setItem("password", this.password);
    this.sendData()
  }


  sendData(){
    this.event.emit(this.username)
  }

}
