import { Component, OnInit, ViewChild } from '@angular/core';

import { MatTableDataSource } from '@angular/material/table';
import { FormBuilder, Validators } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogRef } from '@angular/material/dialog';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AppService } from '../app.service';
import { HttpClient } from '@angular/common/http';


export interface BiddersInfo {
  bidAmount: number;
  id: number;
  bidderName: string;
}


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
})
export class ProductListComponent implements OnInit {
  productName: string = '';
  productDescription: string = '';
  detailproductDescription: string = '';
  category: string = '';
  bidEndDate: any;
  pipe = new DatePipe('en-US');
  todayWithPipe: any = null;
  groupedform = this.fb.group({
    Name: ['', Validators.required],
    Email: [],
    knownlang: this.fb.array([this.fb.control('')]),
  });
  displayedColumns: string[] = ['id', 'productName','bidderName','bidAmount'];//, 'mobile', 'email'];
  showBid: boolean = false;
  isSubmitted = false;
  showProductInfo: boolean = false;
  price: number = 0;
  //dataSource = new MatTableDataSource([...ELEMENT_DATA]);
  //Products: any = ['Painting', 'Sculptor', 'Ornament'];
  Products = ['Painting', 'Sculptor','Ornament', 'Miscellanous Product'];
  
  constructor(public fb: FormBuilder,
    public dialog: MatDialog,private router:Router,
    private appservice:AppService, private http:HttpClient) {}
    data:any =[];
    BiddersInfo:any=[];
    dataSource = new MatTableDataSource<any>([]);
   ngOnInit() {
    //this.dataSource.sort = this.sort;
    let url ='http://localhost:8082/showAllbids';
    this.http.get(url,{responseType:'json'}).subscribe(x =>{
    this.BiddersInfo.push(x);
    this.data = x
    this.dataSource.data = this.data;
    
    });
    
  }
  public counter() {
    this.showBid = true;
    
  }

  /*########### Form ###########*/
  registrationForm = this.fb.group({
    bidForm: ['', [Validators.required]],
  });

  ProductForm = this.fb.group({
    productForm: ['', [Validators.required]],
  });

  // Getter method to access formcontrols
  get cityName() {
    return this.registrationForm.get('bidForm');
  }

  /*########### Template Driven Form ###########*/
  onSubmit() {
    this.isSubmitted = true;
    this.showProductInfo = false;
    this.router.navigateByUrl("/modifyproduct");
    this.productName = 'iPhone 12.0';
    this.productDescription = 'This is a test Product';
    this.detailproductDescription = 'This is a detailed Product';
    this.category = 'Bid Products';
    this.price = 100;
    this.bidEndDate = new Date();
    this.todayWithPipe = this.pipe.transform(this.bidEndDate, 'dd/MM/yyyy');
    if (!this.registrationForm.valid) {
      return false;
    } else {
      return true;
    }
  }
  addproduct(){
    this.router.navigateByUrl("/addproduct");
  }
}

/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at https://angular.io/license
*/
