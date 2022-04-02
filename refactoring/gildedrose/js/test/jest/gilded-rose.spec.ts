import { Item, GildedRose } from '@/gilded-rose';

describe('Gilded Rose', () => {
  function updateGildedRoseQuality() {
    const gildedRose = new GildedRose([new Item('foo', 0, 0)]);
    const items = gildedRose.updateQuality();
    let result = items[0].name;
    return result;
  }

  it('should foo', () => {
    let result = updateGildedRoseQuality();
    expect(result).toMatchSnapshot('foo');
  });
});
