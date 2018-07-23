export class Pet {


  constructor(
    public id: number,
    public name: string,
    public category: string,
    public photoUrl: string[],
    public tags: string[],
    public status: string
  ) {

  }
}
