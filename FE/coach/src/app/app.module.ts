import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { NgSelectModule } from '@ng-select/ng-select';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { NgbModule, NgbDateAdapter, NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';
import { SearchRouteComponent } from './search-route/search-route.component';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { CustomAdapter, CustomDateParserFormatter } from './common/datepicker-adapter';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ChooseTripComponent } from './choose-trip/choose-trip.component';
import { SeatComponent } from './seat/seat.component';
import { InfoCustomerComponent } from './info-customer/info-customer.component';
import { InfoTicketComponent } from './info-ticket/info-ticket.component';
import { PaidSuccessComponent } from './paid-success/paid-success.component';
import { TableComponent } from './table/table.component';
import { HomeComponent } from './home/home.component';
import { MaintenanceSystemComponent } from './Modal/MaintenanceSystem/maintenance-system/maintenance-system.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { FormatTimePipe } from './common/FormatTimePipe';
import { ManagerTicketComponent } from './Manager/ManagerTicket/manager-ticket/manager-ticket.component';
import { DeleteComponent } from './Modal/Delete/delete/delete.component';
import { DeleteSuccessComponent } from './Modal/Delete/delete-success/delete-success.component';
import { UpdateComponent } from './Modal/ManagerTicket/update/update.component';

import { CommonModalComponent } from './Modal/Common/common-modal/common-modal.component';
import { DetailComponent } from './Modal/ManagerTicket/detail/detail.component';
import { ManagerRouteComponent } from './Manager/ManagerRoute/manager-route/manager-route.component';
import { AddnewAndUpdateRouteComponent } from './Modal/ManagerRoute/addnew-and-update-route/addnew-and-update-route.component';
import { ManagerTripComponent } from './Manager/ManagerTrip/manager-trip/manager-trip.component';
import { AddnewAndUpdateTripComponent } from './Modal/ManagerTrip/addnew-and-update-trip/addnew-and-update-trip.component';
import { ManagerCarComponent } from './Manager/ManagerCar/manager-car/manager-car.component';
import { AddnewAndUpdateCarComponent } from './Modal/Manager/MagagerCar/addnew-and-update-car/addnew-and-update-car.component';
import { authInterceptorProviders} from './Services/Web/connect-api.service';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component'
import { AuthService } from './Services/Security/auth.service';
import { AuthGuardService } from './Services/Security/auth-guard.service';
import {JwtHelperService, JwtModule} from '@auth0/angular-jwt'
export function tokenGetter() {
  return sessionStorage.getItem("auth-token");
}
@NgModule({
  declarations: [AppComponent,
    SearchRouteComponent,
    ChooseTripComponent,
    SeatComponent,
    InfoCustomerComponent,
    InfoTicketComponent,
    PaidSuccessComponent,
    ManagerTicketComponent,
    TableComponent,
    HomeComponent,
    MaintenanceSystemComponent,
    NotFoundComponent,
    FormatTimePipe,
    DeleteComponent,
    DeleteSuccessComponent,
    UpdateComponent,
    CommonModalComponent,
    DetailComponent,
    ManagerRouteComponent,
    AddnewAndUpdateRouteComponent,
    ManagerTripComponent,
    AddnewAndUpdateTripComponent,
    ManagerCarComponent,
    AddnewAndUpdateCarComponent,
    RegisterComponent,
    LoginComponent,



  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    NgSelectModule,
    FormsModule,
    HttpClientModule,
    CommonModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    JwtModule.forRoot({
      config: {
       tokenGetter: tokenGetter,
      allowedDomains: ["localhost:4200"]
      },
    })

  ],
  providers: [{ provide: NgbDateAdapter, useClass: CustomAdapter },
  { provide: NgbDateParserFormatter, useClass: CustomDateParserFormatter }, authInterceptorProviders],
  bootstrap: [AppComponent],
})
export class AppModule { }
