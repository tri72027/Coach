import { NgModule } from '@angular/core';
import { Routes, RouterModule ,CanActivate} from '@angular/router';
import { ChooseTripComponent } from './choose-trip/choose-trip.component';
import { InfoCustomerComponent } from './info-customer/info-customer.component';
import { InfoTicketComponent } from './info-ticket/info-ticket.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { PaidSuccessComponent } from './paid-success/paid-success.component';
import { SearchRouteComponent } from './search-route/search-route.component';
import { ManagerTicketComponent } from './Manager/ManagerTicket/manager-ticket/manager-ticket.component';
import { ManagerRouteComponent } from './Manager/ManagerRoute/manager-route/manager-route.component';
import { ManagerTripComponent } from './Manager/ManagerTrip/manager-trip/manager-trip.component';
import { ManagerCarComponent } from './Manager/ManagerCar/manager-car/manager-car.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { AuthGuardService} from './Services/Security/auth-guard.service';
import { RoleGuardService } from './Services/Security/role-guard.service';


const routes: Routes = [
  {
    path: 'trip',
    component: ChooseTripComponent,
  },
  {
    path: '',
    component: SearchRouteComponent,
  },
  {
    path: 'infocustomer',
    component: InfoCustomerComponent
  },
  {
    path: 'infoticket',
    component: InfoTicketComponent
  },
  {
    path: 'paidsuccess',
    component: PaidSuccessComponent
  }, {
    path: 'ticketmanager',
    component: ManagerTicketComponent,

    canActivate: [RoleGuardService],
    data:
    {
      expectedRole: 'ROLE_ADMIN'
    }

  },{
    path: 'routemanager',
    component: ManagerRouteComponent
  },{
    path: 'tripmanager',
    component: ManagerTripComponent
  }, {
    path: 'carmanager',
    component: ManagerCarComponent
  }, {
    path: 'register',
    component: RegisterComponent
  }, {
    path: 'login',
    component: LoginComponent
  },{
    path: '**',
    component: NotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
