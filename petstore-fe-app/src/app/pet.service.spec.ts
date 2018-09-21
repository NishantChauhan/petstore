import { TestBed, inject } from '@angular/core/testing';
import {
  HttpClientTestingModule,
  HttpTestingController
} from '@angular/common/http/testing';
import { PetService } from './pet.service';
import { Pet } from './pet';


const dummyPet = {
  id: 1,
  name: 'Tom',
  category: { id: 1, name: 'pussy-cat' },
  status: 'AVAILABLE',
  tags: [{ id: 1, name: 'drinks-milk' }],
  photoUrls: [{ url: '/Tom.jpg' }]
};

describe('PetService', () => {
  let httpTestingController: HttpTestingController;
  const petStoreUrl = 'http://localhost:8080'; // URL to web api

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    httpTestingController = TestBed.get(HttpTestingController);

  });

  it('getPet() should return a pet', inject(
    [PetService],
    (service: PetService) => {
      const petId = 1;
      const petPhotoUrl = petStoreUrl + '//Tom.jpg';

      service.getPet(petId).subscribe(pet => {
        expect(petId).toEqual(pet.id, 'should return pet id as 1 ');
        expect(petPhotoUrl).toEqual(
          pet.photoUrls[0].url,
          'should return pet id as 1 '
        );
      });
      const getPetReq = httpTestingController.expectOne(
        petStoreUrl + `/pet/${petId}`
      );
      getPetReq.flush(dummyPet);
    }
  ));
});
