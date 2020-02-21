package com.company;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Random;

class Miner {

    static synchronized Block mine(String message) {
        long time = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        String string;
        String previousHash;
        String hash;
        long magicNumber;
        long completionTime;
        long blockId;
        int previousN = SharedData.getN();
        int minerId = Integer.parseInt(Thread.currentThread().getName());

        synchronized (Miner.class) {
            string = "0".repeat(SharedData.getN());
        }

        do {
            magicNumber = new Random().nextLong();
            hash = applySha256(message + magicNumber);
        } while (!hash.startsWith(string));

        if (SharedData.getBlockList().isEmpty()) {
            previousHash = "0";
        } else {
            previousHash = SharedData.getBlockList().get((int) SharedData.getId() - 2).getBlockHash();
        }
        completionTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        regulate(time, completionTime);
        blockId = SharedData.getId();
        SharedData.incrementId();

        return new Block(blockId, time, previousHash, hash, magicNumber, completionTime, SharedData.getN(), previousN,
                minerId);
    }

    private static synchronized void regulate(long initTime, long completionTime) {
        if (completionTime - initTime < 5) {
            SharedData.incrementN();
        } else if (completionTime - initTime > 5) {
            SharedData.decrementN();
        }
    }

    private static synchronized String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte elem : hash) {
                String hex = Integer.toHexString(0xff & elem);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
