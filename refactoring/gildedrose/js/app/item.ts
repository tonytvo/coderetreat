import {NonNegativeWithCeilingQuality} from "@/non-negative-with-ceiling-quality";

export class Item {
    name: string;
    sellIn: number;
    quality: NonNegativeWithCeilingQuality;

    constructor(name, sellIn, quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = new NonNegativeWithCeilingQuality(quality);
    }

    public increaseQuality() {
        return this.quality.increaseQuality();
    }

    public toString() {
        return `{"name":"${this.name}","sellIn":${this.sellIn},"quality":${this.quality.quality}}`;
    }

    public decreaseQuality() {
        return this.quality.decreaseQuality();
    }

    public qualityAmount() {
        return this.quality.quality;
    }
}