package com.company;

public class Worker implements Runnable {
    private String message;

    Worker(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        Block block = Miner.mine(message);
        synchronized (Worker.class) {
            SharedData.getBlockList().add(block);
            boolean isValid = validate();
            if (!isValid) {
                try {
                    SharedData.getBlockList().remove(SharedData.getBlockList().size() - 1);
                    throw new Exception("Invalid Block Chain");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean validate() {
        boolean valid = true;
        for (int i = 0; i < SharedData.getBlockList().size() - 1; i++) {
            Block block1 = SharedData.getBlockList().get(i);
            Block block2 = SharedData.getBlockList().get(i + 1);
            if (!block2.getPreviousHash().equals(block1.getBlockHash())) {
                valid = false;
                break;
            }
        }
        return valid;
    }


}
