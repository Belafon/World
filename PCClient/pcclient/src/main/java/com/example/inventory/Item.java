package com.example.inventory;

public class Item {
    public static class Food extends Item{

        private int id;
        private String name;
        private int weight;
        private int visiblity;
        private int toss;
        private int freshness;
        private int filling;
        private int warm;

        public Food(int id, String name, int weight, int visiblity, int toss,
                int freshness, int filling, int warm) {
            this.id = id;
            this.name = name;
            this.weight = weight;
            this.visiblity = visiblity;
            this.toss = toss;
            this.freshness = freshness;
            this.filling = filling;
            this.warm = warm;
        }

    }
}
