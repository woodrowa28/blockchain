package edu.grinnell.csc207.blockchain;

import java.security.NoSuchAlgorithmException;

/**
 * A linked list of hash-consistent blocks representing a ledger of
 * monetary transactions.
 */
public class BlockChain {
    
    private static class Node {
        private Block block;
        
        private Node next;
        
        public Node(Block b) {
            block = b;
            next = null;
        }
    }
    
    private int numBlocks;
    
    private Node first;
    
    private Node last;
    
    /**
     * Creates a new block chain with one initial block
     * @param initial beginning account value for block chain
     * @throws NoSuchAlgorithmException upon hashing failure
     */
    public BlockChain(int initial) throws NoSuchAlgorithmException {
        first = new Node(new Block(0, initial, null));
        last = first;
        numBlocks = 1;
    }
    
    /**
     * Creates a new Block with given transfer amount, mining for appropriate nonce
     * @param amount transfer amount in block
     * @return valid block 
     * @throws NoSuchAlgorithmException upon hashing failure
     */
    public Block mine(int amount) throws NoSuchAlgorithmException {
        return new Block(numBlocks, amount, getHash());
    }
    
    /**
     * Gets size of the block chain
     * @return numBlocks
     */
    public int getSize() {
        return numBlocks;
    }
    
    /**
     * Adds the given block to the end of the list
     * @param blk desired block to be added
     * @throws IllegalArgumentException if block is incompatible with rest of chain
     */
    public void append(Block blk) throws IllegalArgumentException {
        if (!blk.getHash().isValid()) {
            throw new IllegalArgumentException("Block is invalid and cannot be added.");
        }
        last.next = new Node(blk);
        last = last.next;
    }
    
    /**
     * Attempts to remove the last block in the chain
     * @return whether or not successfully removed
     */
    public boolean removeLast() {
        if (numBlocks == 1) {
            return false;
        } else {
            Node curr = first;
            while (curr.next.next != null) {
                curr = curr.next;
            }
            last = curr;
            return true;
        }
    }
    
    /**
     * Gets the hash of the last block in the chain
     * @return hash (object) of last block
     */
    public Hash getHash() {
        return last.block.getHash();
    }
    
    /**
     * Checks whether the block chain is valid by checking the transaction validity
     * @return validity of chain
     */
    public boolean isValidBlockChain() {
        int annaBalance = first.block.getNum();
        int bobBalance = 0;
        Node curr = first.next;
        while (curr != null) {
            annaBalance += curr.block.getAmount();
            bobBalance -= curr.block.getAmount();
            if (annaBalance < 0 || bobBalance < 0) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Prints the balances of Alice and Bob based on the transactions recorded in the blocks.
     */
    public void printBalances() {
        int annaBalance = first.block.getNum();
        int bobBalance = 0;
        Node curr = first.next;
        while (curr != null) {
            annaBalance += curr.block.getAmount();
            bobBalance -= curr.block.getAmount();
        }
        System.out.println("Anna: " + annaBalance + ", Bob: " + bobBalance);
    }
    
    /**
     * Returns the block chain as a string containing all blocks
     * @return string with all blocks in the chain on individual lines
     */
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        Node curr = first;
        while (curr != null) {
            ret.append(curr.block.toString());
        }
        return ret.toString();
    }
}
