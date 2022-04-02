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

}

export class Item {
    name: string;
    sellIn: number;
    quality: number;
    qualityTemp: NonNegativeWithCeilingQuality;

    constructor(name, sellIn, quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.qualityTemp = new NonNegativeWithCeilingQuality(quality);
    }

    public toString() {
        return `{"name":"${this.name}","sellIn":${this.sellIn},"quality":${this.quality}}`;
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

        return new DefaultItemQualityUpdater();
    }
}
