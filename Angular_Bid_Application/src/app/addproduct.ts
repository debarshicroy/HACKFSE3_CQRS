export class AddProduct {

    constructor(
      public productName: string,
      public shortproductdesc: string,
      public detailedproductdesc: string,
      public productcategory: string,
      public price: number,
      public bidenddate:Date
    ) {  }
}