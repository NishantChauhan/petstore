import { Location } from '@angular/common';
import { PetService } from './../../pet.service';
import { Pet } from './../../pet';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-pet',
  templateUrl: './edit-pet.component.html',
  styleUrls: ['./edit-pet.component.css']
})
export class EditPetComponent implements OnInit {
  petId: number;
  editablePet: Pet;
  url: string;
  photos: FileList;
  statuses = ['AVAILABLE', 'PENDING', 'SOLD'];

  constructor(private route: ActivatedRoute,  private router: Router, private petService: PetService, private location: Location) { }

  ngOnInit() {
    this.petId = +this.route.snapshot.paramMap.get('id');
    this.petService.getPet(this.petId).subscribe(
      pet => {
        this.editablePet = pet;
        this.url = pet.photoUrls[0].url;
      }
    );
  }
  public uploadPhoto(files: FileList) {
    this.photos = files;
    if (this.photos && this.photos.length > 0) {
      for (let i = 0; i < this.photos.length; i++) {
        const formData = new FormData();
        formData.append('image', this.photos[i]);
        this.petService
          .uploadPhoto(this.editablePet.id, formData)
          .subscribe(uploadedPet => {
            this.editablePet = uploadedPet;
            this.url = 'http://localhost:8080/' + this.editablePet.photoUrls[0].url;
            alert('Image Uploaded successfully !!');
            // console.log(this.editablePet);
          });
      }
    }

  }
  updatePet() {

    this.petService.updatePetByNameStatus( this.editablePet.id, this.editablePet.name, this.editablePet.status).subscribe(
      () => {
        alert('Pet updated successfully !!');
        this.location.back();
      }
    )
    ;
  }
  cancel() {
   this.location.back();
  }
}
