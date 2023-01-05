import { Injectable, Input } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

import { LoginpageComponent } from './loginpage/loginpage.component'; 

@Injectable()
export class IntercetorurlInterceptor implements HttpInterceptor {


  user1: any;

  constructor() {   }



  createBasicAuthToken() {

    let username = localStorage.getItem("username")
    let password = localStorage.getItem("password")

    // console.log(username, password, "hi i am chandan")
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
