import { Component,OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentDataService } from '../servies/student-data.service';
import { User } from '../user';

@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css']
})
export class LoginpageComponent {

  username:any;
  password:any;
  
  user:User=new User();
  object:any=[];
  loginFlag = false;


  constructor(private studentService:StudentDataService, private router:Router,
  private route:ActivatedRoute){}

  ngOnInit(): void {
    // this.getAutho();
    this.object.push("dfhsgfgsdk")
  }


  getAutho(){
    this.studentService.getAuthorised().subscribe(data=>{
      console.log(data, "authofjfjghf");
      this.router.navigate(['/students']);

    }, error=>
    alert("UnAuthorised")
    // console.log(error, "myerror")
    )


    
  }

  onSubmit(){

    
    localStorage.setItem("username", this.username);
    localStorage.setItem("password", this.password);
    
    console.log(this.username,"login user")
   
    this.getAutho();
   
      // this.router.navigate(['/students']);

  }

  // x:any;
  // getData(i:any){
  //   this.x = i;
  //   console.log(this.x)
  
  // }


  isLogInOrOut(){
    if(localStorage.getItem("username") && localStorage.getItem("password")){
      return true;
    }
    else{
      return false;
    }
  }


}
