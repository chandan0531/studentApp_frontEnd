import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddStudentComponent } from './add-student/add-student.component';
import { AddressListComponent } from './address-list/address-list.component';
import { AuthguardGuard } from './authguard.guard';
import { CourseListComponent } from './course-list/course-list.component';
import { LoginpageComponent } from './loginpage/loginpage.component';
import { SaveAddressComponent } from './save-address/save-address.component';
import { SaveCourseComponent } from './save-course/save-course.component';
import { StudentListComponent } from './student-list/student-list.component';
import { UpdateCourseComponent } from './update-course/update-course.component';
import { UpdateStudentComponent } from './update-student/update-student.component';

const routes: Routes = [
  {
    path: 'students',canActivate:[AuthguardGuard],
    component: StudentListComponent
  },
  {
    path: 'updateStudent/:id',
    component: UpdateStudentComponent
  },
  {
    path: 'saveStudent/:id',
    component: AddStudentComponent
  },
  {
    path: 'saveAddress/:id',
    component: SaveAddressComponent
  },
  {
    path: 'savecourses',canActivate:[AuthguardGuard],
    component: SaveCourseComponent
  },
  {
    path: 'courses', canActivate:[AuthguardGuard],
    component: CourseListComponent
  },
  {
    path: 'updateCourse/:id',
    component: UpdateCourseComponent
  },
  {
    path: 'addresses',
    component: AddressListComponent
  },
  {
    path: 'login',
    component: LoginpageComponent
  },
  {
    path: '',
    redirectTo: 'login', pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
