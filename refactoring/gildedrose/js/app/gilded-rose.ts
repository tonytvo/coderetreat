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

export class BackstagePassQualityUpdater implements QualityUpdater {
    update(item: Item): void {
        this.updateQualityBackstagePasses(item);
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

    private increaseQualityIfLessThan50(item: Item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1
        }
    }
}

export class AgedBrieQualityUpdater implements QualityUpdater {
    update(item: Item): void {
        this.updateQualityForAgeBrie(item);
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

export class SulfurasQualityUpdater implements QualityUpdater {
    update(item: Item): void {
    }
}

export class EveryThingQualityUpdater implements QualityUpdater {
    update(item: Item) {
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
}

export class GildedRose {
    items: Array<Item>;

    constructor(items = [] as Array<Item>) {
        this.items = items;
    }

    updateQuality() {
        for (let i = 0; i < this.items.length; i++) {
            let item = this.items[i];
            this.createQualityUpdater(item.name).update(item);
        }

        return this.items;
    }

    private createQualityUpdater(name: string) : QualityUpdater{
        if (name == 'Aged Brie') {
            return new AgedBrieQualityUpdater();
        }

        if (name == 'Backstage passes to a TAFKAL80ETC concert') {
            return new BackstagePassQualityUpdater();
        }

        if (name == 'Sulfuras, Hand of Ragnaros') {
            return new SulfurasQualityUpdater();
        }

        return new EveryThingQualityUpdater();
    }
}
