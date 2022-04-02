import {QualityUpdater} from "@/quality-updater";
import {Item} from "@/gilded-rose";

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