import { PetService } from './pet.service';
import { TestBed, inject } from '@angular/core/testing';
import { MockBackend, MockConnection } from '@angular/http/testing';
import {
  HttpModule,
  XHRBackend,
  ResponseOptions,
  Response,
  RequestMethod
} from '@angular/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';

const mockResponse = {
  id: 1,
  name: 'Tom',
  category: { id: 1, name: 'pussy-cat' },
  status: 'AVAILABLE',
  tags: [{ id: 1, name: 'drinks-milk' }],
  photoUrls: [{ url: '/Tom.jpg' }]
};

xdescribe('service: PetService', () => {
  const petStoreUrl = 'http://localhost:8080'; // URL to web api
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpModule, HttpClientTestingModule],
      providers: [
        {
          provide: XHRBackend,
          useClass: XHRBackend
        },
        PetService
      ]
    });
  });
  it('getPet() should return Pet as Json', inject(
    [PetService, XHRBackend],
    (service: PetService, xhrBackend: MockBackend) => {
      const petId = 1;
      const expectedUrl = petStoreUrl + `/pet/${petId}`;
      xhrBackend.connections.subscribe((connection: MockConnection) => {
        expect(connection.request.method).toBe(RequestMethod.Get);
        expect(connection.request.url).toBe(expectedUrl);
        connection.mockRespond(
          new Response(new ResponseOptions({ body: mockResponse }))
        );
      });
      service.getPet(1).subscribe(pet => {
        expect(pet).toEqual(mockResponse);
      });
    }
  ));
});
