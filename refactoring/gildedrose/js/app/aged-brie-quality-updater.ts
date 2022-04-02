import {QualityUpdater} from "@/quality-updater";
import {Item} from "@/gilded-rose";

export class AgedBrieQualityUpdater implements QualityUpdater {
    update(item: Item): void {
        this.updateQualityForAgeBrie(item);
    }

    private updateQualityForAgeBrie(item: Item) {
        item.increaseQuality();
        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            item.increaseQuality();
        }
    }
}