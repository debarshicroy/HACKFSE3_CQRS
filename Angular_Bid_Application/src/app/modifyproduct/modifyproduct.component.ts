import { formatDate } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { AlertDialogComponent } from '../alert-dialog/alert-dialog.component';
import { AppService } from '../app.service';


export interface BiddingElement {
  id: number,
  category: string;
  name: string;
  short_desc:string
  detailed_desc:string
  price: number;
  bidEndDate: any;
} 

const BIDDING_ELEMENT_DATA: BiddingElement[] = [
{"id": 1,"name": "Ravi Verma Painting","short_desc": "White ball ABC","detailed_desc": "white ball","category": "Sports","price": 50.5,"bidEndDate": "2022-07-09T14:23:16.009+00:00"},
{"id": 10,"name": "Queen Chair","short_desc": "Imperial Chair","detailed_desc": "Imperial Chair 500 Years Old","category": "Miscellanous Product","price": 50.5,"bidEndDate": "2022-07-16T06:03:32.730+00:00"},
{"id": 11,"name": "Captial Jack Sparrow Key","short_desc": "Imperial Chair","detailed_desc": "Imperial Chair 500 Years Old","category": "Miscellanous Product","price": 50.5,"bidEndDate": "2022-07-29T00:00:00.000+00:00"},
{"id": 13,"name": "Monalisa Painting","short_desc": "Falls under the best Paints ","detailed_desc": "Falls under the best Paints in the world","category": "Painting","price": 5000,"bidEndDate": "2022-07-27T00:00:00.000+00:00"}];

@Component({
  selector: 'app-modifyproduct',
  templateUrl: './modifyproduct.component.html',
  styleUrls: ['./modifyproduct.component.css']
})
export class ModifyproductComponent implements OnInit {

  constructor(public dialog: MatDialog,private http:HttpClient,private router: Router,private appService: AppService) { }
  BiddingElement:any =[];
  data:any =[];
  dataSource = new MatTableDataSource<any>([]);
  ngOnInit(): void {
    console.log('inside the modify product');
    let url ="http://localhost:8082/showProducts";
    this.http.get(url,{responseType:'json'}).subscribe(x =>{
      this.BiddingElement.push(x);
      this.data = x;
      this.dataSource.data = this.data;
    });
  }
  displayedColumns: string[] = ['category', 'name', 'price', 'bidEndDate','actions'];
   
  deleteProduct(row: any):void{
    let bidDate = formatDate(row['bidEndDate'],'yyyy-MM-dd','en_US');
    let currentDate = formatDate(new Date(),'yyyy-MM-dd','en_US');
    console.log("deleting the product "+row['bidEndDate']);
    if(bidDate < currentDate ){
    let message = 'You Cant Delete a product after bid end date';
    this.alertDialog(message);
    }else{
      let url ='http://localhost:8081/delete/productName='+row['name'];
      this.http.get(url,{responseType:'text'}).subscribe(x =>{
        this.alertDialog(x);
      });
    }
  }
  alertDialog(info: any) {
    this.dialog.open(AlertDialogComponent, {
      data: {
        message: info
      }
    });
  }
  editProduct(row: any){
    this.router.navigateByUrl("/addproduct");
    console.log(row['name']);
    this.appService.updateApprovalMessage(row);
  }
}
