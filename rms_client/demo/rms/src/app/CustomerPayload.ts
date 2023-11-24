export class CustomerPayload{
    email:string;
    customerName:string;
    phone:string;
    birthdate:Date;
    gender:string;

    constructor(email:string,customerName:string,phone:string,gender:string,birthdate:Date){
        this.email=email;
        this.customerName=customerName;
        this.phone=phone;
        this.gender=gender;
        this.birthdate=birthdate;
    }
    
}