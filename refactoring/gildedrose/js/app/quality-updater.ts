import {Item} from "@/item";

export interface QualityUpdater {
    update(item: Item): Item;
}