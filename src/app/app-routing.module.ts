import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddStudentComponent } from './add-student/add-student.component';
import { AddressListComponent } from './address-list/address-list.component';
import { CourseListComponent } from './course-list/course-list.component';
import { SaveAddressComponent } from './save-address/save-address.component';
import { SaveCourseComponent } from './save-course/save-course.component';
import { StudentListComponent } from './student-list/student-list.component';
import { UpdateCourseComponent } from './update-course/update-course.component';
import { UpdateStudentComponent } from './update-student/update-student.component';

const routes: Routes = [
  {
    path : 'students',
    component: StudentListComponent
  },
  {
    path:'updateStudent/:id',
    component : UpdateStudentComponent
  },
  {
    path :'saveStudent/:id',
    component:AddStudentComponent
  },
  {
    path: 'saveAddress/:id',
    component:SaveAddressComponent
  },
  {
    path:'savecourses',
    component: SaveCourseComponent
  },
  {
    path:'courses',
    component:CourseListComponent
  },
  {
    path:'updateCourse/:id',
    component:UpdateCourseComponent
  },
  {
    path:'addresses',
    component:AddressListComponent
  },
  {
    path:'', 
    redirectTo: 'students',pathMatch:'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
