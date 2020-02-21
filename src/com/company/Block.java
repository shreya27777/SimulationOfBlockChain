package com.company;

import java.io.Serializable;

public class Block implements Serializable {
    private long id;
    private long time;
    private String previousHash;
    private String blockHash;
    private long magicNumber;
    private long completionTime;
    private int blockN;
    private int previousN;
    private int minerId;

    Block(long id, long time, String previousHash, String blockHash, long magicNumber, long completionTime,
          int blockN, int previousN, int minerId) {
        this.id = id;
        this.time = time;
        this.previousHash = previousHash;
        this.blockHash = blockHash;
        this.magicNumber = magicNumber;
        this.completionTime = completionTime;
        this.blockN = blockN;
        this.previousN = previousN;
        this.minerId = minerId;
    }

    String getPreviousHash() {
        return previousHash;
    }

    String getBlockHash() {
        return blockHash;
    }

    public long getId() {
        return id;
    }

    long getTime() {
        return time;
    }

    public long getMagicNumber() {
        return magicNumber;
    }

    long getCompletionTime() {
        return completionTime;
    }

    public int getBlockN() {
        return blockN;
    }

    public int getMinerId() {
        return minerId;
    }

    public int getPreviousN() {
        return previousN;
    }

    @Override
    public String toString() {
        String changeInN;
        int n = blockN - previousN;
        if (n > 0) {
            changeInN = "N was increased to " + blockN;
        } else if (n < 0) {
            changeInN = "N was decreased by " + Math.abs(n);
        } else {
            changeInN = "N stays the same";
        }

        return "Block:\n" +
                "Created by miner # " + minerId + "\n" +
                "Id: " + id + "\n" +
                "Timestamp: " + time + "\n" +
                "Magic number: " + magicNumber + "\n" +
                "Hash of the previous block:\n" + previousHash + "\n" +
                "Hash of the block:\n" + blockHash + "\n" +
                "Block was generating for " + (completionTime - time) + " seconds" + "\n" +
                changeInN;
    }
}
