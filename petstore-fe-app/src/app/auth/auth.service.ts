import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { ExecutionStatus } from './execution-status';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  serverUrl = 'http://localhost:8080';
  headers: HttpHeaders;
  authUser: User;
  sessionValid: boolean;

  constructor(private http: HttpClient) {}

  getUserStatus(): boolean {
    return this.sessionValid;
  }

  login(user: User): Observable<ExecutionStatus> {
    this.authUser = user;
    // const params = new URLSearchParams();
    // params.append('username', user.username);
    // params.append('password', user.password);
    // params.append('grant_type', 'password');
    // params.append('client_id', 'petapp');
    this.headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      Authorization: 'Basic ' + btoa('petstoreapp:pEt@!23')
    });

    // this.headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const url = `${this.serverUrl}/login?username=${user.username}&password=${user.password}`;

    // const url = `${this.serverUrl}/login`;
    const options = { headers: this.headers, withCredentials: true };

    return this.http
      .post<ExecutionStatus>(url, options)
      .pipe(
        tap(data => {
          console.log(data);
        }),
        catchError(this.handleError<ExecutionStatus>())
      );
  }

  logout(): Observable<ExecutionStatus> {
    // const params = new URLSearchParams();
    // params.append('username', this.authUser.username);
    // params.append('password', this.authUser.password);
    // params.append('grant_type', 'password');
    // params.append('client_id', 'petapp');
    this.headers = new HttpHeaders({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
      Authorization: 'Basic ' + btoa('petstoreapp:pEt@!23')
    });

    // this.headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const url = `${this.serverUrl}/logout?username=${this.authUser.username}&password=${this.authUser.password}`;

    // const url = `${this.serverUrl}/login`;

    const options = { headers: this.headers, withCredentials: true };

    return this.http.post<ExecutionStatus>(url, options).pipe(
      tap(data => {
        console.log(data);
      }),
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
        result = { status: 'Invalid Login' };
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
