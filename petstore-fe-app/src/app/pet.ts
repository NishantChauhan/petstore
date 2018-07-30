export class Pet {
  public id: number;
  public name: string;
  public category: Category;
  public photoUrls: PhotoURL[];
  public tags: Tag[];
  public status: string;
  constructor(
  ) {}
}
    // new Pet( 1, 'Tom',
    //  'pussy-cat',['http://localhost:8080/photoURL/pussy-cat/Tom-id-1/Tom.png'],
    // ['cartoon','loves-milk','eats-mice'],'AVAILABLE');

export class PhotoURL {
  constructor(public url: string) {}
}

export class Category {
  constructor(public id: number, public name: string) {}
}
export class Tag {
  constructor(public id: number, public name: string) {}
}
