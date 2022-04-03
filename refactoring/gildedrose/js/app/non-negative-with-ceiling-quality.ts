export class NonNegativeWithCeilingQuality {
    quality: number;

    constructor(quality) {
        this.quality = quality;
    }

    public increaseQuality() {
        if (this.quality < 50) {
            return new NonNegativeWithCeilingQuality(this.quality + 1);
        }
        return new NonNegativeWithCeilingQuality(this.quality);
    }

    static zero() {
        return new NonNegativeWithCeilingQuality(0);
    }

    public decreaseQuality() {
        if (this.quality > 0) {
            return new NonNegativeWithCeilingQuality(this.quality - 1);
        }
        return new NonNegativeWithCeilingQuality(this.quality);
    }
}