import { Pet } from './pet';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class PetService {
  private petStoreUrl = 'http://localhost:8080';  // URL to web api
  constructor(
    private http: HttpClient) { }

    /** GET hero by id. Will 404 if id not found */
    getPet(id: number): Observable<Pet> {
      const url = `${this.petStoreUrl}/pet/${id}`;
      return this.http.get<Pet>(url).pipe(
          tap( pet => console.log(`fetched pet id=${id} url= ${url} ${pet}`)),
        catchError(this.handleError<Pet>(`getPet id=${id}`))
      );
    }
      /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      // this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
