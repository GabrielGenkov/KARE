import { Component, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { PostDataService } from '../../services/post-data.service';
import { GetUser,  } from '../models/user.model';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent {

  user: GetUser = {password:'',email:''}

  constructor(
    private post: PostDataService,
    private router: Router,
    private http: HttpClient,
    ) {}

  loginCheck() {
    const userCredentials = { email: this.user.email, password: this.user.password };

    this.post.login(userCredentials).subscribe(
      (response: any) => {
        console.log('User logged in successfully:', response);
        this.navigateToMain();  // Optionally navigate to main after successful login
      },
      (error) => {
        console.error('Error logging in:', error);
        alert('Invalid email or password');
      }
    );
  }
  private navigateToMain() {
    this.router.navigate(['/main']);
  }
}
