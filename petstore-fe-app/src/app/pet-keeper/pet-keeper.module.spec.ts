import { PetKeeperModule } from './pet-keeper.module';

describe('PetKeeperModule', () => {
  let petKeeperModule: PetKeeperModule;

  beforeEach(() => {
    petKeeperModule = new PetKeeperModule();
  });

  it('should create an instance', () => {
    expect(petKeeperModule).toBeTruthy();
  });
});
