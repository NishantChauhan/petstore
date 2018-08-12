import { Mode } from '../../mode';
import { PetService } from '../../pet.service';
import { Category, Tag } from '../../pet';
import { Component, OnInit } from '@angular/core';
import { Pet } from '../../pet';


@Component({
  selector: 'app-add-pet',
  templateUrl: './add-pet.component.html',
  styleUrls: ['./add-pet.component.css'],
  providers: [PetService]
})
export class AddPetComponent implements OnInit {
  pet = new Pet();
  categories: Category[];
  mode = Mode.READ_ONLY;
  petAdded = false;
  tags: Tag[];
  photos: FileList;
  imageUrl: string;
  tagsChecked: boolean[];
  statuses = ['AVAILABLE', 'PENDING', 'SOLD'];
  submitted = false;

  constructor(private petService: PetService) {
    this.petService.getTags().subscribe(tags => {
      this.tags = tags;
      this.tagsChecked = new Array();
      if (this.tags) {
        for (let i = 0; i < this.tags.length; i++) {
          if (this.tags) {
            this.tagsChecked.push(false);
            // console.log(this.tags[i] + ' = ' + this.tagsChecked[i]);
          }
        }
      }
    });
  }

  ngOnInit() {
    this.petService
      .getCategories()
      .subscribe(categories => (this.categories = categories));
  }
/*
  public diagnostic(): string {
    return JSON.stringify(this.pet);
  }
*/
  public reset() {
    location.reload();
  }

  public tagsChanged() {
    if (this.tagsChecked) {
      this.pet.tags = new Array<Tag>();
      for (let i = 0; i < this.tagsChecked.length; i++) {
        if (this.tags && this.tagsChecked[i]) {
          this.pet.tags.push(this.tags[i]);
        }
      }
    }
  }


  public uploadPhoto(files: FileList) {
    this.photos = files;
    // console.log(files);
    if (this.photos && this.photos.length > 0) {
      for (let i = 0; i < this.photos.length; i++) {
        const formData = new FormData();
        formData.append('image', this.photos[i]);
        this.petService
          .uploadPhoto(this.pet.id, formData)
          .subscribe(uploadedPet => {
            this.pet = uploadedPet;
            // console.log(uploadedPet);
            this.submitted = true;
          });
      }
    }
  }
  public addPet() {
    this.petService.addPet(this.pet).subscribe(addedPet => {
      this.pet = addedPet;
      this.petAdded = true;
    });
  }
}
