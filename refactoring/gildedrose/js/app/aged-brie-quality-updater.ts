import {QualityUpdater} from "@/quality-updater";
import {Item} from "@/gilded-rose";

export class AgedBrieQualityUpdater implements QualityUpdater {
    update(item: Item): Item {
        return this.updateQualityForAgeBrie(item);
    }

    private updateQualityForAgeBrie(item: Item) {
        let updatedQuality = item.increaseQuality();
        let updatedSellIn = item.sellIn - 1;
        if (updatedSellIn < 0) {
            updatedQuality = item.increaseQuality();
        }
        return new Item(item.name, updatedSellIn, updatedQuality.quality);
    }
}