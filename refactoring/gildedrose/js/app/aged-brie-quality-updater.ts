import {QualityUpdater} from "@/quality-updater";
import {Item} from "@/gilded-rose";

export class AgedBrieQualityUpdater implements QualityUpdater {
    update(item: Item): Item {
        return this.updateQualityForAgeBrie(item);
    }

    private updateQualityForAgeBrie(item: Item) {
        item.increaseQuality();
        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            item.increaseQuality();
        }
        return new Item(item.name, item.sellIn, item.quality);
    }
}