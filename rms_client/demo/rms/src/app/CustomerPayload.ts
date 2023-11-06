export class CustomerPayload{
    email:string;
    customerName:string;
    phone:string;
    birtdate:Date;

    constructor(email:string,customerName:string,phone:string){
        this.email=email;
        this.customerName=customerName;
        this.phone=phone;
    }
    
}