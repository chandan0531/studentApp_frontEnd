import { Injectable, OnInit } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';


import { LoginpageComponent } from './loginpage/loginpage.component';
import { User } from './user';

@Injectable()
export class IntercetorurlInterceptor implements HttpInterceptor {


  user1: any;

  constructor(private loginpage: LoginpageComponent, private userlg: User) { }

  // ngOnInit(): void {
  //   this.user1 = this.loginpage.username;
  //   console.log(this.user1, "new user chchchc")

  // }

  createBasicAuthToken() {

    let username = localStorage.getItem("username")
    let password = localStorage.getItem("password")

    // this.user1 = this.loginpage.username;
    console.log(this.loginpage.username, "new user chchchc")
    return 'Basic ' + window.btoa(username + ":" + password);

  }


  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    let reqUrl: String = request.url;
    if (!request.headers.has('authorization')) {
      request = request.clone({ headers: request.headers.set('authorization', this.createBasicAuthToken()) });
    }
    console.log(request, "request by my")


    return next.handle(request);
  }
}
