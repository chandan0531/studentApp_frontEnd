import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginpageComponent } from './loginpage/loginpage.component';

@Injectable({
  providedIn: 'root'
})
export class AuthguardGuard implements CanActivate {

  constructor(private loginpage:LoginpageComponent){}
  // canActivate(
  //   route: ActivatedRouteSnapshot,
  //   state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
  //   return true;


  // }
  canActivate(){
    return this.loginpage.isLogInOrOut();
  }


  
}
