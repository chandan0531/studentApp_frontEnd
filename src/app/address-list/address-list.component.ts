import { Component , OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { Address } from '../address';
import { StudentDataService } from '../servies/student-data.service';

@Component({
  selector: 'app-address-list',
  templateUrl: './address-list.component.html',
  styleUrls: ['./address-list.component.css']
})
export class AddressListComponent implements OnInit {

  address:any= Address;

  constructor(private studentService:StudentDataService, private router: Router){}

  ngOnInit(): void {
    this.getAllAddress();
  }

getAllAddress(){
  this.studentService.getAllAddress().subscribe(data=>{
    this.address = data;
    console.log("Address: " + this.address[0].student.studentId)
  })
}


}
