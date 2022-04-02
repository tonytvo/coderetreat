import {GildedRose, Item} from '@/gilded-rose';

describe('Gilded Rose', () => {
  function updateGildedRoseQuality(name: string) {
    const gildedRose = new GildedRose([new Item(name, 0, 0)]);
    const items = gildedRose.updateQuality();
    let result = items[0].name;
    return result;
  }

  it('should foo', () => {
    expect(updateGildedRoseQuality('foo')).toMatchSnapshot('foo');
  });
});
