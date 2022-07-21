import { formatDate } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AlertDialogComponent } from '../alert-dialog/alert-dialog.component';
import { AppService } from '../app.service';

@Component({
  selector: 'app-buyerlandingpage',
  templateUrl: './buyerlandingpage.component.html',
  styleUrls: ['./buyerlandingpage.component.css']
})
export class BuyerlandingpageComponent implements OnInit {

  displayBid: boolean= false;
  addBidForm: FormGroup;
  powers = ['Painting', 'Sculptor',
            'Ornament', 'Miscellanous Product'];
  constructor(private router:Router,private formBuilder: FormBuilder,private http:HttpClient,private dialog: MatDialog,private appService:AppService) { 
    this.addBidForm = this.formBuilder.group({
      productCategory:['', [Validators.required,Validators.maxLength(30)]],
      productName:['', [Validators.required,Validators.maxLength(30)]],
      firstName: ['', [Validators.required,Validators.minLength(5) ,Validators.maxLength(30)]],
      lastName: ['', [Validators.required,Validators.minLength(3) ,Validators.maxLength(25)]],
      mobileNo:['',[Validators.required,Validators.pattern("^[0-9]*$"),Validators.maxLength(10)]],
      pincode: ['', [Validators.required,Validators.pattern("^[0-9]*$"),Validators.maxLength(6)]],
      price: ['', [Validators.required,Validators.pattern("^[0-9]*$")]],
      bidEndDate: ['', [Validators.required]],
      stateName:['', [Validators.required]],
      address:['',[Validators.required,Validators.minLength(5),Validators.maxLength(10)]],
      email: ['', [Validators.required, Validators.email,Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]]
    });
  }
  
  ngOnInit(): void {
    this.getStates();
  }
  newBid(){
    this.displayBid = true;
    
  }
  productsObj: any[]=[];
  minDate: any;
  alertDialog(info: any) {
    this.dialog.open(AlertDialogComponent, {
      data: {
        message: info
      }
    });
  }
  pcategory(){
    console.log("event here "+this.addBidForm.controls['productCategory'].value);
    let productCategory = this.addBidForm.controls['productCategory'].value;
    this.http.post<any>('http://localhost:8081/showProductbycat',productCategory,{responseType:'json'}).subscribe(data =>{
      if(data !== undefined && data !== null && data !== ''){
      console.log('data ----->'+JSON.stringify(data));
      this.productsObj = data;
      console.log('productsObj ----->'+this.productsObj.length);
      if(this.productsObj.length ===0){
        this.alertDialog('No Products available under this category');
      }
      }else{
        this.alertDialog('Service Unavailable');
      }
    });
  }
  productIdObj: any={};
  dateString: string='';
  placeBidCondition: boolean = false;
  getProductInfo(){
    //console.log("event kartheek "+event);
    console.log("event here getProductInfo id"+this.addBidForm.controls['productName'].value);
    let id = this.addBidForm.controls['productName'].value;
    this.http.post<any>('http://localhost:8081/showProductbyid',id, {responseType:'json'}).subscribe(data =>{
      if(data !== undefined && data !== null && data !== ''){
      console.log('data ----->'+JSON.stringify(data));
      this.productIdObj = data;
      this.dateString= this.productIdObj['bidEndDate'];
      this.prdtName = this.productIdObj['name'];
      this.minDate = formatDate(this.dateString.substring(0,10),'yyyy-MM-dd','en');
      console.log("this.minDate"+(this.minDate));
      this.addBidForm.controls['bidEndDate'].setValue(this.minDate);
      console.log("comparsion"+(this.minDate < formatDate(new Date(),'yyyy-MM-dd','en')));
      if(this.minDate < formatDate(new Date(),'yyyy-MM-dd','en')){
        this.alertDialog('Bidding is closed for the product you have opted');
      }else{
        this.placeBidCondition = true;
      }
      }else{
        this.alertDialog('Service Unavailable');
      }
    });
  }
  keyPress(event: any) {
    const pattern = /[0-9\+\-\ ]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (event.keyCode != 8 && !pattern.test(inputChar)) {
      event.preventDefault();
    } 
  } 
  geoNameObj:any;
  statesObj: any[]=[];
    async getStates(){
    this.appService.getStatesAndCities().subscribe(data =>{
      this.geoNameObj =data;
      this.statesObj = this.geoNameObj['geonames'];
      //var self = this;
    });
    console.log("this states "+this.statesObj);
  }
  bidReqObj:any ={'productName':'','bidderName':'','bidAmount':0.0};
  prdtName:string ='';
  onSubmit() { 
    //this.showPopUp = true;
    let url: any ='http://localhost:8081/place-bid';
    this.bidReqObj['productName'] = this.prdtName;
    this.bidReqObj['bidderName'] = this.addBidForm.controls['firstName'].value + " "+this.addBidForm.controls['lastName'].value;
    this.bidReqObj['bidAmount'] = this.addBidForm.controls['price'].value; 
    this.http.post(url,this.bidReqObj,{ responseType:'text'}).subscribe(data =>{
      console.log("data"+data);
      this.alertDialog("Placed Bid SuccessFully");
    },(error)=>{
      console.log("error occured here");
      this.alertDialog("Failed to add the Bid. Please try after some time");
    });
  }
}
