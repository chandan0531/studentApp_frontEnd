import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StudentListComponent } from './student-list/student-list.component';
import {HttpClientModule} from '@angular/common/http'
import{FormsModule} from '@angular/forms';
import { UpdateStudentComponent } from './update-student/update-student.component';
import { SaveCourseComponent } from './save-course/save-course.component';
import { CourseListComponent } from './course-list/course-list.component';
import { UpdateCourseComponent } from './update-course/update-course.component';
import { AddStudentComponent } from './add-student/add-student.component';
import { AddressListComponent } from './address-list/address-list.component';
import { SaveAddressComponent } from './save-address/save-address.component';

@NgModule({
  declarations: [
    AppComponent,
    StudentListComponent,
    UpdateStudentComponent,
    SaveCourseComponent,
    CourseListComponent,
    UpdateCourseComponent,
    AddStudentComponent,
    AddressListComponent,
    SaveAddressComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
