import {QualityUpdater} from "@/quality-updater";
import {BackstagePassQualityUpdater} from "@/backstage-pass-quality-updater";
import {AgedBrieQualityUpdater} from "@/aged-brie-quality-updater";
import {SulfurasQualityUpdater} from "@/sulfuras-quality-updater";
import {DefaultItemQualityUpdater} from "@/default-item-quality-updater";

export class NonNegativeWithCeilingQuality {
    quality: number;
    constructor(quality) {
        this.quality = quality;
    }

    public increaseQuality() {
        if (this.quality < 50) {
            return new NonNegativeWithCeilingQuality(this.quality + 1);
        }
        return new NonNegativeWithCeilingQuality(this.quality);
    }

    static zero() {
        return new NonNegativeWithCeilingQuality(0);
    }

    public decreaseQuality() {
        if (this.quality > 0) {
            return new NonNegativeWithCeilingQuality(this.quality - 1);
        }
        return new NonNegativeWithCeilingQuality(this.quality);
    }
}

export class Item {
    name: string;
    sellIn: number;
    quality: NonNegativeWithCeilingQuality;

    constructor(name, sellIn, quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = new NonNegativeWithCeilingQuality(quality);
    }

    public increaseQuality() {
        return this.quality.increaseQuality();
    }

    public toString() {
        return `{"name":"${this.name}","sellIn":${this.sellIn},"quality":${this.quality.quality}}`;
    }
    public decreaseQuality() {
        return this.quality.decreaseQuality();
    }
}

export class GildedRose {
    items: Array<Item>;

    constructor(items = [] as Array<Item>) {
        this.items = items;
    }

    updateQuality() {
        this.items = this.items.map(item => this.createQualityUpdater(item.name).update(item));
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

        return new DefaultItemQualityUpdater();
    }
}
