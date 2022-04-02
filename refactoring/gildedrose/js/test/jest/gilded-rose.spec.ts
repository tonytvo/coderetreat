import {GildedRose, Item} from '@/gilded-rose';
import 'jest-extended-snapshot';

describe('Gilded Rose', () => {
  function updateGildedRoseQuality(name: string) {
    const gildedRose = new GildedRose([new Item(name, 0, 0)]);
    const items = gildedRose.updateQuality();
    return JSON.stringify(items[0]);
  }

  it('should foo', () => {
    let names = ['foo'];
    expect(updateGildedRoseQuality).toVerifyAllCombinations(names);
  });
});
