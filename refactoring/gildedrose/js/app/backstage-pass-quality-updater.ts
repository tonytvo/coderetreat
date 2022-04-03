import {QualityUpdater} from "@/quality-updater";
import {Item} from "@/gilded-rose";

export class BackstagePassQualityUpdater implements QualityUpdater {
    update(item: Item): void {
        this.updateQualityBackstagePasses(item);
    }

    private updateQualityBackstagePasses(item: Item) {
        item.increaseQuality();
        if (item.sellIn < 11) {
            item.increaseQuality();
        }
        if (item.sellIn < 6) {
            item.increaseQuality();
        }
        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            item.resetQuality();
        }
    }
}