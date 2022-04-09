import {QualityUpdater} from "@/quality-updater";
import {Item} from "@/item";

export class DefaultItemQualityUpdater implements QualityUpdater {
    update(item: Item): Item {
        return this.updateQualityForDefaultItem(item);
    }

    private updateQualityForDefaultItem(item: Item) {
        let updatedQuality = item.decreaseQuality();
        let updatedSellIn = item.sellIn - 1;
        if (updatedSellIn < 0) {
            updatedQuality = updatedQuality.decreaseQuality();
        }
        return new Item(item.name, updatedSellIn, updatedQuality.quality);
    }
}