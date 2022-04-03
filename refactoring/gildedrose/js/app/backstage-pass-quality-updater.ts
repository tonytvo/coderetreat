import {QualityUpdater} from "@/quality-updater";
import {Item} from "@/gilded-rose";

export class BackstagePassQualityUpdater implements QualityUpdater {
    update(item: Item): Item {
        return this.updateQualityBackstagePasses(item);
    }

    private updateQualityBackstagePasses(item: Item) {
        let updateQuality = item.increaseQuality();
        if (item.sellIn < 11) {
            updateQuality = item.increaseQuality();
        }
        if (item.sellIn < 6) {
            updateQuality = item.increaseQuality();
        }
        let updatedSellIn = item.sellIn - 1;
        if (updatedSellIn < 0) {
            updateQuality = item.resetQuality();
        }
        return new Item(item.name, updatedSellIn, updateQuality.quality);
    }
}