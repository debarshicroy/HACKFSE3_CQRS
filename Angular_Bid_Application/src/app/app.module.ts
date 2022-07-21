import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductAlertsComponent } from './product-alerts/product-alerts.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { CartComponent } from './cart/cart.component';
import { ShippingComponent } from './shipping/shipping.component';

import { LoginComponent } from './login/login.component';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MaterialModule } from './materialmodule';
import { CommonModule } from '@angular/common';
import { AlertDialogComponent } from './alert-dialog/alert-dialog.component';
import { LandingpageComponent } from './landingpage/landingpage.component';
import { AddproductComponent } from './addproduct/addproduct.component';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { AppService } from './app.service';
import { ModifyproductComponent } from './modifyproduct/modifyproduct.component';
import { BuyerlandingpageComponent } from './buyerlandingpage/buyerlandingpage.component';
import { PlacebidComponent } from './placebid/placebid.component';

@NgModule({
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    CommonModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MaterialModule,
    BsDatepickerModule.forRoot(), 
    RouterModule.forRoot([
      { path: '', component: LoginComponent },
      { path: 'home', component: LandingpageComponent },
      { path: 'seller', component: ProductListComponent },
      { path: 'buyer',component:BuyerlandingpageComponent},
      { path: 'addproduct', component: AddproductComponent },
      { path: 'products/:productId', component: ProductDetailsComponent },
      { path: 'modifyproduct', component: ModifyproductComponent },
      { path: 'shipping', component: ShippingComponent },
    ]),
  ],
  providers: [AppService],
  entryComponents: [ AlertDialogComponent],
  declarations: [
    AppComponent,
    TopBarComponent,
    ProductListComponent,
    ProductAlertsComponent,
    ProductDetailsComponent,
    CartComponent,
    ShippingComponent,
    LoginComponent,
    AlertDialogComponent,
    LandingpageComponent,
    AddproductComponent,
    ModifyproductComponent,
    BuyerlandingpageComponent,
    PlacebidComponent
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}

/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at https://angular.io/license
*/
