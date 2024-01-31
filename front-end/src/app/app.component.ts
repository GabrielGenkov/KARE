import { Component, ViewEncapsulation } from '@angular/core';
import {NavigationEnd, Router} from "@angular/router";
import {filter} from "rxjs";
import {CookieService} from 'ngx-cookie';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AppComponent {
  title = 'front-end';
  constructor(private router: Router, private cookieService: CookieService) {
    this.router.events.pipe(
      filter(event=> event instanceof NavigationEnd)
    ).subscribe(()=>{
      this.updateNavbarVisibility();
    });
  }
  isRestrictedRoute(): boolean {
    const allowedRoutes = ['/main', '/create-workout', '/workout-list', '/history'];

    if (allowedRoutes.includes(this.router.url)) {
      // This is an allowed route, so the method can be shown
      return false;
    } else {
      // This is a restricted route, so the method should be hidden
      return true;
    }
  }
  private updateNavbarVisibility(): void{
  }

  logout(): void {
    // Clear the cookies
    this.cookieService.remove('token');
    this.cookieService.remove('userId');

    // Navigate to the login page
    this.router.navigate(['/login']);
  }
}
