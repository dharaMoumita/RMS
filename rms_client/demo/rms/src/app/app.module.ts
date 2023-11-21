import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { UserComponent } from './user/user.component';
import { AuthInterceptor } from './auth/auth.interceptor';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './register/register.component';
import MenuComponent from './menu/menu.component';
import { OrderDetaiilsComponent } from './order-detaiils/order-detaiils.component';
import { CustomerRegistrationComponent } from './customer-registration/customer-registration.component';
import { BillComponent } from './bill/bill.component';
import { TablesComponent } from './tables/tables.component';
import { ReserveTablesComponent } from './reserve-tables/reserve-tables.component';
import { TableHeaderComponent } from './table-header/table-header.component';
import { TableBillComponent } from './table-bill/table-bill.component';
import { ReservationListComponent } from './reservation-list/reservation-list.component';
import { CustomerCheckComponent } from './customer-check/customer-check.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    LoginComponent,
    ForbiddenComponent,
    AdminComponent,
    UserComponent,
    RegisterComponent,
    HeaderComponent,
    MenuComponent,
    OrderDetaiilsComponent,
    CustomerRegistrationComponent,
    BillComponent,
    TablesComponent,
    ReserveTablesComponent,
    TableHeaderComponent,
    TableBillComponent,
    ReservationListComponent,
    CustomerCheckComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule,
    FormsModule,
    BrowserAnimationsModule,
    MatDialogModule

  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
