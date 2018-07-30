import { PetService } from './../../pet.service';
import { Category, Tag, PhotoURL } from './../../pet';
import { Component, OnInit } from '@angular/core';
import { Pet } from '../../pet';

@Component({
  selector: 'app-add-pet',
  templateUrl: './add-pet.component.html',
  styleUrls: ['./add-pet.component.css']
})
export class AddPetComponent implements OnInit {
  pet = new Pet();
  categories: Category[];
  tags: Tag[];
  tagsChecked: boolean[];
  statuses = ['AVAILABLE', 'PENDING', 'SOLD'];

  constructor(private petService: PetService) {
    this.petService.getTags().subscribe(tags => {
      this.tags = tags;
      this.tagsChecked = new Array() ;
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

  public addPet() {
    if (this.tagsChecked) {
      this.pet.tags = new Array<Tag>();
      for (let i = 0; i < this.tagsChecked.length; i++) {
        if (this.tags && this.tagsChecked[i]) {
          this.pet.tags.push(this.tags[i]);
        }
      }
    }
    this.pet.photoUrls = new Array<PhotoURL>();
    this.pet.photoUrls.push( new PhotoURL('photoURL/bulldog/Spike-id-3/Spike.png'));

    this.petService.addPet(this.pet).subscribe();
  }
}
