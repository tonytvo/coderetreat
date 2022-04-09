import {QualityUpdater} from "@/quality-updater";
import {BackstagePassQualityUpdater} from "@/backstage-pass-quality-updater";
import {AgedBrieQualityUpdater} from "@/aged-brie-quality-updater";
import {SulfurasQualityUpdater} from "@/sulfuras-quality-updater";
import {DefaultItemQualityUpdater} from "@/default-item-quality-updater";
import {Item} from "@/item";

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
