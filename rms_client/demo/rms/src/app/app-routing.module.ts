import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './auth/auth.guard';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { UserComponent } from './user/user.component';
import { RegisterComponent } from './register/register.component';
import MenuComponent from './menu/menu.component';
import { OrderDetaiilsComponent } from './order-detaiils/order-detaiils.component';
import { CustomerRegistrationComponent } from './customer-registration/customer-registration.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'admin', component: AdminComponent, canActivate:[AuthGuard], data:{roles:['Manager']}} ,
  { path: 'user', component: UserComponent  ,canActivate:[AuthGuard], data:{roles:['Waiter']} },
  { path: 'login', component: LoginComponent },
  { path: 'forbidden', component: ForbiddenComponent },
  {path:'register',component:RegisterComponent}
  ,{path:'menu',component:MenuComponent},
  {path:'order-detaiils',component:OrderDetaiilsComponent},
  {path:'customer-registration',component:CustomerRegistrationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
