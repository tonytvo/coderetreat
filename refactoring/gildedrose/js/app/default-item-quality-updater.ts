import {QualityUpdater} from "@/quality-updater";
import {Item} from "@/gilded-rose";

export class DefaultItemQualityUpdater implements QualityUpdater {
    update(item: Item) {
        this.updateQualityForDefaultItem(item);
    }

    private updateQualityForDefaultItem(item: Item) {
        item.decreaseQuality();
        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            item.decreaseQuality();
        }
    }
}