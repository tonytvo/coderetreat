import {QualityUpdater} from "@/quality-updater";
import {Item} from "@/item";
import {NonNegativeWithCeilingQuality} from "@/non-negative-with-ceiling-quality";

export class BackstagePassQualityUpdater implements QualityUpdater {
    update(item: Item): Item {
        return this.updateQualityBackstagePasses(item);
    }

    private updateQualityBackstagePasses(item: Item) {
        let updateQuality = item.increaseQuality();
        if (item.sellIn < 11) {
            updateQuality = updateQuality.increaseQuality();
        }
        if (item.sellIn < 6) {
            updateQuality = updateQuality.increaseQuality();
        }
        let updatedSellIn = item.sellIn - 1;
        if (updatedSellIn < 0) {
            updateQuality = NonNegativeWithCeilingQuality.zero();
        }
        return new Item(item.name, updatedSellIn, updateQuality.quality);
    }
}