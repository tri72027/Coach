import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import decode from 'jwt-decode';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuardService implements CanActivate {

  constructor(public auth: AuthService, public router: Router) { }
  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRole = route.data.expectedRole;
    const token = sessionStorage.getItem("auth-token")
    const payload = decode(token)
    if (!this.auth.isAuthenticated() || payload['role'] != expectedRole) {
      this.router.navigate(['']);
      return false
    }
    return true
  }
}
