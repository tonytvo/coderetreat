import {QualityUpdater} from "@/quality-updater";
import {Item} from "@/gilded-rose";

export class DefaultItemQualityUpdater implements QualityUpdater {
    update(item: Item): Item {
        return this.updateQualityForDefaultItem(item);
    }

    private updateQualityForDefaultItem(item: Item) {
        item.decreaseQuality();
        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            item.decreaseQuality();
        }
        return new Item(item.name, item.sellIn, item.quality);
    }
}