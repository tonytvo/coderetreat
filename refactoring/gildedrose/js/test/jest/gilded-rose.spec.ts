import {GildedRose, Item} from '@/gilded-rose';
import 'jest-extended-snapshot';
import * as R from 'ramda';

describe('Gilded Rose', () => {
  function updateGildedRoseQuality(name: string, quality: number, sellIn: number) {
    const gildedRose = new GildedRose([new Item(name, sellIn, quality)]);
    const items = gildedRose.updateQuality();
    return items[0].toString();
  }

  it('should foo', () => {
    let names = ['foo'
      ,"+5 Dexterity Vest"
      ,"Aged Brie"
      ,"Elixir of the Mongoose"
      ,"Sulfuras, Hand of Ragnaros"
      ,"Backstage passes to a TAFKAL80ETC concert"];
    let qualities = [-1, 0, 1, 49, 50, 51];
    let sellIns = R.range(-1, 15);
    expect(updateGildedRoseQuality).toVerifyAllCombinations(names, qualities, sellIns);
  });
});
