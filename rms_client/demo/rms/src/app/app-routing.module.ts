import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './auth/auth.guard';
import { HomeComponent } from './home/home.component';
import { UserComponent } from './order-feature/user/user.component';
import { RegisterComponent } from './register/register.component';
import MenuComponent from './order-feature/menu/menu.component';
import { OrderDetaiilsComponent } from './order-feature/order-detaiils/order-detaiils.component';
import { CustomerRegistrationComponent } from './customer-registration/customer-registration.component';
import { BillComponent } from './order-feature/bill/bill.component';
import { CanDeactivateGuard } from './auth/bill-deactivate.guard';
import { TableBillComponent } from './reservation-feature/table-bill/table-bill.component';
import { CustomerCheckComponent } from './customer-check/customer-check.component';
import { OrderComponent } from './order-feature/order/order.component';
import { AdminComponent } from './reservation-feature/admin/admin.component';
import { ReservationListComponent } from './reservation-feature/reservation-list/reservation-list.component';
import { ReserveTablesComponent } from './reservation-feature/reserve-tables/reserve-tables.component';
import { TablesComponent } from './reservation-feature/tables/tables.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AuthGuard],
    data: { roles: ['Manager'] },
  },
  {
    path: 'user',
    component: UserComponent,
    canActivate: [AuthGuard],
    data: { roles: ['Waiter'] },
  },
  { path: 'login', component: LoginComponent },
  { path: 'forbidden', component: ForbiddenComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'menu', component: MenuComponent    , data: { roles: ['Waiter'] },    canActivate: [AuthGuard],

},
  { path: 'order-detaiils', component: OrderDetaiilsComponent,data: { roles: ['Waiter'] },    canActivate: [AuthGuard], },
  { path: 'customer-registration', component: CustomerRegistrationComponent },
  {
    path: 'bill',
    component: BillComponent,
    canDeactivate: [CanDeactivateGuard],
  },
  { path: 'tables', component: TablesComponent },
  { path: 'reserve-tables', component: ReserveTablesComponent },
  { path: 'table-bill', component: TableBillComponent ,canDeactivate: [CanDeactivateGuard]},
  { path: 'reservation-list/:id', component: ReservationListComponent },
  { path: 'customer-check', component: CustomerCheckComponent },
  {path:'order/:id',component:OrderComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
