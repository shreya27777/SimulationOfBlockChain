package com.company;

import java.util.ArrayList;
import java.util.List;

class SharedData {
    private static List<Block> blockList = new ArrayList<>();
    private static long id = 1L;
    private static int n = 0;

    static List<Block> getBlockList() {
        return blockList;
    }

    static void incrementId() {
        SharedData.id += 1;
    }

    static void incrementN() {
        SharedData.n += 1;
    }

    static void decrementN() {
        SharedData.n -= 1;
    }

    static long getId() {
        return id;
    }

    static int getN() {
        return n;
    }
}
