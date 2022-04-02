import {QualityUpdater} from "@/quality-updater";
import {Item} from "@/gilded-rose";

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