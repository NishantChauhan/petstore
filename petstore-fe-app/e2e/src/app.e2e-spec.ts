'use strict'; // necessary for node!

import {
  browser,
  element,
  by,
  protractor,
  ElementFinder,
  ElementArrayFinder
} from 'protractor';


describe('Pet Form Validation Tests', function() {

  it('should load the home page', function() {
    browser.get('/searchPets/petById/1');
    const title = element(by.tagName('title')).getText();
    expect(title).toEqual('');
  });

  it('should display Pet Tom', function() {
    browser.get('/searchPets/petById');
    element(by.id('petId')).sendKeys(1);
    element(by.tagName('button')).click();
    // browser.wait( function() {
    //   sleep(50);
    // });
    const petName = element(by.id('petName')).getText();
    expect(petName).toEqual('Name Tom');
  });

});
async function sleep(ms: number) {
 await new Promise(resolve => setTimeout(resolve, ms));
}
