import { Component,OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentDataService } from './servies/student-data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'studentcourse';

  constructor(private studentService:StudentDataService, private router:Router,
    private route:ActivatedRoute){}
    // btnstate: boolean=false;

    local=localStorage.getItem("username");

    ngOnInit(): void {
      if(localStorage.getItem("username")=="chandan"|| localStorage.getItem("username")=="deepak"){
        this.local = localStorage.getItem("username");
      }
      // if(localStorage.getItem("username")){
      //   this.btnstate = true;
      
      // }
      // else{
      //   this.btnstate = false;
      // }
      // console.log(this.btnstate)
    }

  getIntLoginpage(){
  this.router.navigate(['/login']);
  // localStorage.setItem("username", "null")
  // localStorage.setItem("password", "null")
  localStorage.clear();
  
  }


  addresslink(){
    if(localStorage.getItem("username")){
      this.router.navigate(['/addresses']);
    }
  }

  studentslink(){
    if(localStorage.getItem("username")){
      this.router.navigate(['/students']);
    }
  }
}
