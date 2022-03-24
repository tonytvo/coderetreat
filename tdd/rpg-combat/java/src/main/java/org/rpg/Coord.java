package org.rpg;

class Coord {
    private final int x;

    public Coord(int x) {
        this.x = x;
    }

    public static Coord of(int x) {
        return new Coord(x);
    }
}
