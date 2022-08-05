import { formatDate } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { biddercloudALBurl_8081, sellercloudALBurl_8082 } from 'src/environments/environment.prod';
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
            'Ornaments', 'Miscellanous Product'];
  constructor(private router:Router,private formBuilder: FormBuilder,private http:HttpClient,private dialog: MatDialog,private appService:AppService) { 
    this.addBidForm = this.formBuilder.group({
      productCategory:['', [Validators.required,Validators.maxLength(30)]],
      productName:['', [Validators.required,Validators.minLength(5),Validators.maxLength(30)]],
      firstName: ['', [Validators.required,Validators.minLength(5) ,Validators.maxLength(30)]],
      lastName: ['', [Validators.required,Validators.minLength(3) ,Validators.maxLength(25)]],
      mobileNo:['',[Validators.required,Validators.pattern("^[0-9]*$"),Validators.maxLength(10)]],
      pincode: ['', [Validators.required,Validators.pattern("^[0-9]*$"),Validators.maxLength(6)]],
      price: ['', [Validators.required,Validators.pattern("^[0-9.]*$")]],
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
    let url =sellercloudALBurl_8082+'showProductbycat/'+productCategory;
    this.http.get<any>(url,{responseType:'json'}).subscribe(data =>{
      if(data !== undefined && data !== null && data !== ''){
      console.log('data ----->'+JSON.stringify(data));
      this.productsObj = data;
      console.log('productsObj ----->'+this.productsObj[0]['name']);
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
  selectedProductObj:any={"id":"",
  "productId":0,
  "name":"",
  "short_desc":"",
  "detailed_desc":"",
  "category":"",
  "price":0,
  "bidEndDate":""};
  getProductInfo(){
    let id = this.addBidForm.controls['productName'].value;
    this.productsObj.forEach(data =>{
      //console.log("data"+JSON.stringify(data));
      this.selectedProductObj = JSON.stringify(data);
      if(JSON.parse(this.selectedProductObj)['name']===id){
        let obj = JSON.parse(this.selectedProductObj);
        this.dateString = obj['bidEndDate'];
        this.prdtName = obj['name'];
        this.minDate = formatDate(this.dateString.substring(0,10),'yyyy-MM-dd','en');
        this.addBidForm.controls['bidEndDate'].setValue(this.minDate);
        this.addBidForm.controls['price'].setValue(obj['price']);
        if(this.minDate < formatDate(new Date(),'yyyy-MM-dd','en')){
          this.alertDialog('Bidding is closed for the product you have opted');
        }else{
          this.placeBidCondition = true;
        }
      }
    })
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
     if(this.placeBidCondition){ 
     let url: any =biddercloudALBurl_8081+'place-bid';
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
}
