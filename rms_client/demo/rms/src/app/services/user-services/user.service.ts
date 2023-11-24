import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserAuthService } from './user-auth.service';
import { Observable } from 'rxjs';
// import { UserAuthService } from './user-auth.service';
import { JwtHelperService } from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root',
})
export class UserService {
  PATH_OF_API = 'http://localhost:8082';

  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });
  constructor(
    private httpclient: HttpClient,
    private userAuthService: UserAuthService
  ) {}

  public login(loginData) {
    return this.httpclient.post(this.PATH_OF_API + '/authenticate', loginData, {
      headers: this.requestHeader,
    });
  }
  public register(registerData){
    console.log(registerData);
    
    return this.httpclient.post(this.PATH_OF_API+'/user',registerData,{
      headers:this.requestHeader,
    });
  }

  public forUser() {
    return this.httpclient.get(this.PATH_OF_API + '/forUser', {
      responseType: 'text',
    });
  }


  public forAdmin():Observable<string> {
    return this.httpclient.get(this.PATH_OF_API + '/forAdmin', {
      responseType: 'text',
    });

  }
  public getUser(userId:number):Observable<any>{
    return this.httpclient.get<any>(this.PATH_OF_API+"/user/"+userId);
  }

  public getCurrentUser():Observable<any>{
    return this.httpclient.get<any>(this.PATH_OF_API+"/user/current");
  }

  

  public roleMatch(allowedRoles : Array<string>): boolean {
    let isMatch = false;
    const helper = new JwtHelperService();
    const token=this.userAuthService.getToken();
    if(token!=null){
    const decodedToken = helper.decodeToken(this.userAuthService.getToken());
    const roles=decodedToken.Roles;
    
    // const userRoles: any [] = this.userAuthService.getRoles();
  const userRoles: any [] = roles;

    // console.log(userRoles);
    
    if (userRoles != null && userRoles) {
      for (let i = 0; i < userRoles.length; i++) {
        for (let j = 0; j < allowedRoles.length; j++) {
          if (userRoles[i]== allowedRoles[j]) {
            isMatch = true;
            
            return isMatch;
          } else {
            
            return isMatch;
          }
        }
      }
    }}
    return isMatch;
  }
}