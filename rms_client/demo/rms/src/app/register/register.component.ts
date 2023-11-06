import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoginPayload } from '../LoginPayload';
import { RegisterPayload } from '../RegisterPayload';
import { UserService } from '../services/user-services/user.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  constructor(private userService: UserService, private route: Router) {}
  data: RegisterPayload;
  loginData: LoginPayload;
  r: any;
  role:any;

  ngOnInit(): void {
      this.role=[];

  }
  register(registerForm: NgForm) {
    // console.log(registerForm.value);
    if (registerForm.valid) {
      this.role.push(registerForm.value.Role);
      this.data = new RegisterPayload(
        registerForm.value.username,
        registerForm.value.email,
        registerForm.value.password,
        this.role
      );
      console.log(this.data);
      this.r=JSON.stringify(this.data);
      console.log(this.r);
      
      this.userService.register(this.r).subscribe(
        (result) => {
          console.log(result);
          alert('Registration Successful. Login Again');
          this.route.navigate(['/login']);
        },
        (err) => {
          console.log(err);
        }
      );
    } else {
      alert('Please enter valid details');
    }
  }
}
