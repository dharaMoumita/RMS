export class RegisterPayload{
    userName:string;
    email:string;
    password:string;
    role=[] as string[];
    constructor( userName:string,email:string, password:string,role=[] as any[]){
        this.userName=userName;
        this.email=email;
        this.role=role;
        this.password=password;
    }
}