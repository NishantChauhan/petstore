import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { ExecutionStatus } from './execution-status';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  serverUrl = 'http://localhost:8080';
  // headers: HttpHeaders;
  authUser: User;
  sessionValid: boolean;
  accessToken: string;
  grant_type = 'password';
  client_id = 'petapp';
  client_secret = 'pEt@!23';


  constructor(private http: HttpClient) {}

  getUserStatus(): boolean {
    return this.sessionValid;
  }

  getUser(): Observable<User> {
    const url = `${this.serverUrl}/user/${this.authUser.username}`;
    return this.http.get<User>(url).pipe(
      catchError(undefined)
    );
  }

  getCurrenToken(): string {
    return this.accessToken;
  }

  obtainToken(user: User): Observable<ExecutionStatus> {
    this.authUser = user;
    const  httpHeader = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8'
      , 'Authorization': 'Basic ' + btoa(this.client_id + ':' + this.client_secret)
    });
    const url = `${this.serverUrl}/oauth/token?username=${this.authUser.username}&password=${this.authUser.password}`
    + `&grant_type=${this.grant_type}&client_id=${this.client_id}`
    + `&client_secret=${this.client_secret}`
    ;
    // const options = { headers: httpHeader };
    const options = { headers: httpHeader, withCredentials: true };
    return this.http
      .post<any>(url,  options)
      .pipe(
        map ( data => {
                const execStatus = new ExecutionStatus();
                execStatus.status = 'Login Successful';
                execStatus.data = data;
                this.accessToken = data.access_token;
                return execStatus;
            }),
        catchError(this.handleError())
      );
  }


  logout(): Observable<ExecutionStatus> {
    const httpHeader = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8'
    });
/*     const url = `${this.serverUrl}/logout?username=${this.authUser.username}&password=${this.authUser.password}` +
    `&grant_type=${this.grant_type}&client_id=${this.client_id}&client_secret=${this.client_secret}`;
 */
    const url = `${this.serverUrl}/logout`;
    const options = { headers: httpHeader, withCredentials: true };

    return this.http.post<ExecutionStatus>(url, options).pipe(
      catchError(this.handleError<ExecutionStatus>())
    );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: ExecutionStatus) {
    return (error: HttpErrorResponse): Observable<ExecutionStatus> => {
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
      if (error.url === 'http://localhost:8080/logon?error') {
        result = { status: 'Invalid Login', data: error };
      } else {
        result = {status: error.message,  data: error};
      }

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as ExecutionStatus);
    };
  }
  private log(message: string) {
    console.log(message);
  }
}
