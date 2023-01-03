import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import {HttpClient} from '@angular/common/http'
import { Student } from '../student';
import { Course } from '../course';
import { Address } from '../address';
import { StudentDto } from '../student-dto';
import { CourseResponse } from '../course-response';
import { StudentResponse } from '../student-response';

@Injectable({
  providedIn: 'root'
})
export class StudentDataService {

  url = "http://localhost:8080/api/students";

  urlC = "http://localhost:8080/api/course";

  urlA = "http://localhost:8080/api/address";

  urlP = "http://localhost:8080/api/coursePage";

  urlSP = "http://localhost:8080/api/studentPageCriteria";

  constructor( private httpClient:HttpClient) { }


  getStudents():Observable<Student>{
    return this.httpClient.get<Student>(`${this.url}`)
  }

// get student by Id
  getStudentByStudentId(id:any):Observable<Student> {
    return this.httpClient.get<Student>(`${this.url}/${id}`)
  }
  

  // add student by course id
  addStudentByCourseId(StudentDto:StudentDto):Observable<Object>{
    return this.httpClient.post(`${this.url}`,StudentDto)
  }


  // update student by Student id
  updateStudentByStudentId(student:Student):Observable<Object>{
    return this.httpClient.put(`${this.url} `, student);
  }

// delete student by Id
deleteStudentBystudentId(id:any):Observable<Object>{

  return this.httpClient.delete(`${this.url}/${id}`)
}


// get All courses
getAllCourses():Observable<Course>{
  return this.httpClient.get<Course>(`${this.urlC}`)
}

//add course
addCourse(course:Course):Observable<Object>{

  return this.httpClient.post(`${this.urlC}`, course)
}

//get course By Id
getCourseByCourseId(id:any):Observable<Course>{
  return this.httpClient.get<Course>(`${this.urlC}/${id}`)
}


// get All Address
getAllAddress():Observable<Address>{
  return this.httpClient.get<Address>(`${this.urlA}`)
}


//Save address by Student ID
saveAddressByStudentId(id:any, address:Address):Observable<Object>{

  return this.httpClient.post(`${this.urlA}/${id}`,address);
}


// get All courses by page
getAllCoursesByPage(title:any, pageNo:any, pageSize:any, sortBy:any,sortDir:any):Observable<CourseResponse>{
  return this.httpClient.get<CourseResponse>(`${this.urlP}?title=${title}&pageNo=${pageNo}&pageSize=${pageSize}&sortBy=${sortBy}&sortDir=${sortDir}`)
}

//http://localhost:8080/api/coursePage?pageNo=0&pageSize=2&sortBy=duration&sortDir=desc


// get All students by Page
getAllStudentByPage(title:any, pageNo:any, pageSize:any, sortBy:any,sortDir:any):Observable<StudentResponse>{

return this.httpClient.get<StudentResponse>(`${this.urlSP}?title=${title}&pageNo=${pageNo}&pageSize=${pageSize}&sortBy=${sortBy}&sortDir=${sortDir}`)
}


}
