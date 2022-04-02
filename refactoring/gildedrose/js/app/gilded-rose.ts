export class Item {
    name: string;
    sellIn: number;
    quality: number;

    constructor(name, sellIn, quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }
}

export interface QualityUpdater {
    update(item: Item): void;
}

export class EveryThingQualityUpdater implements QualityUpdater {
    update(item: Item) {
        if (item.name == 'Aged Brie') {
            this.updateQualityForAgeBrie(item);
            return;
        }
        if (item.name == 'Backstage passes to a TAFKAL80ETC concert') {
            this.updateQualityBackstagePasses(item);
            return;
        }
        if (item.name == 'Sulfuras, Hand of Ragnaros') {
            return;
        }

        this.updateQualityForDefaultItem(item);
    }

    private updateQualityForDefaultItem(item: Item) {
        this.decreaseQualityIfGreatherThan0(item);
        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            this.decreaseQualityIfGreatherThan0(item);
        }
    }

    private decreaseQualityIfGreatherThan0(item: Item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1
        }
    }

    private updateQualityBackstagePasses(item: Item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1
            if (item.sellIn < 11) {
                this.increaseQualityIfLessThan50(item);
            }
            if (item.sellIn < 6) {
                this.increaseQualityIfLessThan50(item);
            }
        }
        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            this.resetQuality(item);
        }
    }

    private resetQuality(item: Item) {
        item.quality = item.quality - item.quality
    }

    private updateQualityForAgeBrie(item: Item) {
        this.increaseQualityIfLessThan50(item);
        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            this.increaseQualityIfLessThan50(item);
        }
    }

    private increaseQualityIfLessThan50(item: Item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1
        }
    }
}

export class GildedRose {
    items: Array<Item>;

    constructor(items = [] as Array<Item>) {
        this.items = items;
    }

    updateQuality() {
        for (let i = 0; i < this.items.length; i++) {
            new EveryThingQualityUpdater().update(this.items[i]);
        }

        return this.items;
    }
}
