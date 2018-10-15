import { Pet, Category, Tag } from './pet';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import {
  HttpClient,
  HttpHeaders,
  HttpResponse,
  HttpParams
} from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { AuthService } from './auth/auth.service';

const httpOptionsJson = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

// const httpOptionsImage = {
//   headers: new HttpHeaders({
//     'Content-Type': ['']
//   })
// };

@Injectable({
  providedIn: 'root'
})
export class PetService {
  private petStoreUrl = 'http://localhost:8080'; // URL to web api
  constructor(private http: HttpClient, private authService: AuthService) {}


  getPet(id: number): Observable<Pet> {
    const url = `${this.petStoreUrl}/pet/${id}`;
    return this.http.get<Pet>(url).pipe(
      tap(pet => {
        if (pet && pet.photoUrls[0]) {
          const photoUrl = pet.photoUrls[0].url;
          pet.photoUrls[0].url = this.petStoreUrl + '/' + photoUrl;
        }
      }),
      catchError(this.handleError<Pet>(`getPet id=${id}`))
    );
  }

  deletePet(id: number): Observable<string> {
    const url = `${this.petStoreUrl}/pet/${id}`;
    return this.http.delete(url, { responseType: 'text' }).pipe(
      catchError(this.handleError<string>(`getPet id=${id}`))
    );
  }

  getAllPets(): Observable<Pet[]> {

    const url = `${this.petStoreUrl}/fetchAllPets`;
    return this.http.get<Pet[]>(url).pipe(
      tap(pets => {
        if (pets) {
          pets.forEach(pet => {
            if (pet && pet.photoUrls[0]) {
              const photoUrl = pet.photoUrls[0].url;
              pet.photoUrls[0].url = this.petStoreUrl + '/' + photoUrl;
            }
          });
        }
      }),
      catchError(this.handleError<Pet[]>(`getPets`))
    );
  }

  addPet(pet: Pet): Observable<Pet> {
    const url = `${this.petStoreUrl}/pet`;
    return this.http.post<Pet>(url, pet, httpOptionsJson).pipe(
      catchError(undefined)
    );
  }

  updatePetByNameStatus(
    id: number,
    pname: string,
    pstatus: string
  ): Observable<Pet> {
    const url = `${this.petStoreUrl}/pet/${id}?name=${pname}&status=${pstatus}`;
    return this.http.post<Pet>(url, httpOptionsJson).pipe(
      catchError(undefined)
    );
  }

  uploadPhoto(petId: number, image: FormData): Observable<Pet> {
    const url = `${this.petStoreUrl}/pet/${petId}/uploadImage`;
    return this.http.post<Pet>(url, image).pipe(
      // tap( () => {
      //   }
      // ),
      catchError(this.handleError<Pet>(` pet=${petId}`))
    );
  }

  getCategories(): Observable<Category[]> {
    const url = `${this.petStoreUrl}/fetchAllCategories`;
    return this.http.get<Category[]>(url).pipe(
      catchError(this.handleError<Category[]>(`getCategories`))
    );
  }
  getTags(): Observable<Tag[]> {
    const url = `${this.petStoreUrl}/fetchAllTags`;
    return this.http.get<Tag[]>(url).pipe(
      catchError(this.handleError<Tag[]>(`getTags`))
    );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
  private log(message: string) {
    console.log(message);
  }
}
