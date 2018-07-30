import { PetSearchModule } from './pet-search.module';

describe('PetSearchModule', () => {
  let petSearchModule: PetSearchModule;

  beforeEach(() => {
    petSearchModule = new PetSearchModule();
  });

  it('should create an instance', () => {
    expect(petSearchModule).toBeTruthy();
  });
});
