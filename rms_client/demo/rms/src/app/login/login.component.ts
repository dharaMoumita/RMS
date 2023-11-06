import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from '../services/user-services/user.service';
import { Router } from '@angular/router';
import { UserAuthService } from '../services/user-services/user-auth.service';
import { LoginPayload } from '../LoginPayload';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  data: LoginPayload;

  hasAdminRole = false;
  hasUserRole = false;
  constructor(
    private userService: UserService,
    private userAuthService: UserAuthService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  login(loginForm: NgForm) {
    console.log(loginForm.value);

    this.data = new LoginPayload(
      loginForm.value.userName,
      loginForm.value.password
    );
    console.log(this.data);

    this.userService.login(this.data).subscribe(
      (response: any) => {
        console.log(response);

        this.userAuthService.setRoles(response.user.role);
        this.userAuthService.setToken(response.jwtToken);
        console.log(response.user.role);

        for (const i of response.user.role) {
          if (i.roleName === 'Manager') {
            this.hasAdminRole = true;
          } else if (i.roleName === 'Waiter') {
            this.hasUserRole = true;
          }
        }

        if (this.hasAdminRole) {
          this.router.navigate(['/admin']);
        } else {
          this.router.navigate(['/user']);
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
