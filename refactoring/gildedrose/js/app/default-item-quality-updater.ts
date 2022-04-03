import {QualityUpdater} from "@/quality-updater";
import {Item} from "@/gilded-rose";

export class DefaultItemQualityUpdater implements QualityUpdater {
    update(item: Item): Item {
        return this.updateQualityForDefaultItem(item);
    }

    private updateQualityForDefaultItem(item: Item) {
        let updatedQuality = item.decreaseQuality();
        let updatedSellIn = item.sellIn - 1;
        if (updatedSellIn < 0) {
            updatedQuality = item.decreaseQuality();
        }
        return new Item(item.name, updatedSellIn, updatedQuality.quality);
    }
}