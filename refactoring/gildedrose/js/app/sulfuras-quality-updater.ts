import {QualityUpdater} from "@/quality-updater";
import {Item} from "@/gilded-rose";

export class SulfurasQualityUpdater implements QualityUpdater {
    update(item: Item): Item {
        return item;
    }
}