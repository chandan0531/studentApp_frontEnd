import { NgModule,APP_INITIALIZER } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StudentListComponent } from './student-list/student-list.component';
import {HttpClientModule, HttpContextToken, HTTP_INTERCEPTORS} from '@angular/common/http'
import{FormsModule} from '@angular/forms';
import { UpdateStudentComponent } from './update-student/update-student.component';
import { SaveCourseComponent } from './save-course/save-course.component';
import { CourseListComponent } from './course-list/course-list.component';
import { UpdateCourseComponent } from './update-course/update-course.component';
import { AddStudentComponent } from './add-student/add-student.component';
import { AddressListComponent } from './address-list/address-list.component';
import { SaveAddressComponent } from './save-address/save-address.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import{MatButtonModule} from '@angular/material/button'
import{MatIconModule} from '@angular/material/icon';
import { LoginpageComponent } from './loginpage/loginpage.component'
import{IntercetorurlInterceptor} from './intercetorurl.interceptor'
import { User } from './user';

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
    SaveAddressComponent,
    LoginpageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatSlideToggleModule,
    MatButtonModule,
    MatIconModule
  ],
  providers: [
    {provide:HTTP_INTERCEPTORS,useClass:IntercetorurlInterceptor, multi:true},

    LoginpageComponent, User
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
