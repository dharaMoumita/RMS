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
import { UserComponent } from './order-feature/user/user.component';
import { AuthInterceptor } from './auth/auth.interceptor';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './register/register.component';
import MenuComponent from './order-feature/menu/menu.component';
import { OrderDetaiilsComponent } from './order-feature/order-detaiils/order-detaiils.component';
import { CustomerRegistrationComponent } from './customer-registration/customer-registration.component';
import { BillComponent } from './order-feature/bill/bill.component';
import { TableHeaderComponent } from './reservation-feature/table-header/table-header.component';
import { TableBillComponent } from './reservation-feature/table-bill/table-bill.component';
import { CustomerCheckComponent } from './customer-check/customer-check.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';
import { OrderComponent } from './order-feature/order/order.component';
import { DatePipe } from '@angular/common';
import { AdminComponent } from './reservation-feature/admin/admin.component';
import { ReservationListComponent } from './reservation-feature/reservation-list/reservation-list.component';
import { ReserveTablesComponent } from './reservation-feature/reserve-tables/reserve-tables.component';
import { TablesComponent } from './reservation-feature/tables/tables.component';
import { SpinnerComponent } from './spinner/spinner.component';
import { LoadingInterceptor } from './auth/loading.interceptor';
import { OrderHeaderComponent } from './order-feature/order-header/order-header.component';
import { BackButtonComponent } from './back-button/back-button.component';

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
    CustomerCheckComponent,
    OrderComponent,
    SpinnerComponent,
    OrderHeaderComponent,
    BackButtonComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule,
    FormsModule,
    BrowserAnimationsModule,
    MatDialogModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    DatePipe,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: LoadingInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
