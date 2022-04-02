import {Item} from "@/gilded-rose";

export interface QualityUpdater {
    update(item: Item): void;
}