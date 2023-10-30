import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoginPayload } from '../LoginPayload';
import { RegisterPayload } from '../RegisterPayload';
import { UserService } from '../services/user.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{
  constructor(private userService:UserService,private route:Router){}
  data: RegisterPayload;
  loginData: LoginPayload;
  ngOnInit(): void {
  }
  register(registerForm: NgForm) {
    console.log(registerForm.value);

    this.data=new RegisterPayload(registerForm.value.username,registerForm.value.email,registerForm.value.password);
    console.log(this.data);
    this.userService.register(this.data).subscribe(
      (result)=>{
        console.log(result);
        alert("Registration Successful. Login Again");
        this.route.navigate(['/login']);
      },
      (err)=>{
        console.log(err);
        
      }
    )

  }

}
