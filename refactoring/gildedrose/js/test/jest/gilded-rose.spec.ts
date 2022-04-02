import {GildedRose, Item} from '@/gilded-rose';
import 'jest-extended-snapshot';

describe('Gilded Rose', () => {
  function updateGildedRoseQuality(name: string, quality: number) {
    const gildedRose = new GildedRose([new Item(name, 0, quality)]);
    const items = gildedRose.updateQuality();
    return JSON.stringify(items[0]);
  }

  it('should foo', () => {
    let names = ['foo'
      ,"+5 Dexterity Vest"
      ,"Aged Brie"
      ,"Elixir of the Mongoose"
      ,"Sulfuras, Hand of Ragnaros"
      ,"Backstage passes to a TAFKAL80ETC concert"];
    let qualities = [-1, 0, 1, 49, 50, 51];
    expect(updateGildedRoseQuality).toVerifyAllCombinations(names, qualities);
  });
});
