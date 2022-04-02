import {QualityUpdater} from "@/quality-updater";
import {Item} from "@/gilded-rose";

export class DefaultItemQualityUpdater implements QualityUpdater {
    update(item: Item) {
        this.updateQualityForDefaultItem(item);
    }

    private updateQualityForDefaultItem(item: Item) {
        this.decreaseQualityIfGreatherThan0(item);
        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            this.decreaseQualityIfGreatherThan0(item);
        }
    }

    private decreaseQualityIfGreatherThan0(item: Item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1
        }
    }
}