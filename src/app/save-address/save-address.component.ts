import { Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from '../address';
import { Course } from '../course';
import { StudentDataService } from '../servies/student-data.service';

@Component({
  selector: 'app-save-address',
  templateUrl: './save-address.component.html',
  styleUrls: ['./save-address.component.css']
})
export class SaveAddressComponent implements OnInit{
id:any;
address:Address = new Address();
  constructor(private studentService:StudentDataService, private router:Router,
    private route:ActivatedRoute){}

ngOnInit(): void {
   
  this.id =  this.route.snapshot.params['id'];
}



addAddress(){
  this.studentService.saveAddressByStudentId(this.id,this.address).subscribe(data=>{
    console.log(data);
  })

  this.goToStudentList();
}

onSubmit(){
this.addAddress()

}


goToStudentList(){
  this.router.navigate(['/students']);
}


}
