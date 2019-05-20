import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpHeaders
} from '@angular/common/http';
import { AuthService } from './auth/auth.service';

@Injectable()
export class CacheInterceptorService implements HttpInterceptor {
  constructor(private authService: AuthService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let httpRequest;
    if (!req.url.match('oauth')) {
      httpRequest = req.clone({
        headers: new HttpHeaders({
          'Cache-Control': 'no-cache',
          Pragma: 'no-cache',
          Expires: 'Sat, 01 Jan 2000 00:00:00 GMT',
          Authorization: `Bearer ${this.authService.getCurrenToken()}`
        })
      });
    } else {
      httpRequest = req;
    }
    console.log(httpRequest);

    return next.handle(httpRequest);
  }
}
