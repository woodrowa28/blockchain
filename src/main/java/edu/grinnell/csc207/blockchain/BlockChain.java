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
        
        /**
         * Creates a new Node with information to make a block (unknown nonce).
         * @param numBlocks number of blocks in list (location of block to create)
         * @param data transfer amount held by block
         * @param prevHash hash of previous block in list
         * @throws NoSuchAlgorithmException upon hashing failure
         */
        public Node(int numBlocks, int data, Hash prevHash) throws NoSuchAlgorithmException {
            block = new Block(numBlocks, data, prevHash);
            next = null;
        }
        
        /**
         * Creates a new Node with information to make a block (known nonce).
         * @param numBlocks number of blocks in list (location of block to create)
         * @param data transfer amount held by block
         * @param prevHash hash of previous block in list
         * @param nonce unique number for hashing
         * @throws NoSuchAlgorithmException upon hashing failure
         */
        public Node(int numBlocks, int data, Hash prevHash, long nonce) 
               throws NoSuchAlgorithmException {
            block = new Block(numBlocks, data, prevHash, nonce);
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
        first = new Node(0, initial, null);
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
        
    }
    
    /**
     * Gets size of the block chain
     * @return numBlocks
     */
    public int getSize() {
        
    }
    
    /**
     * Adds the given block to the end of the list
     * @param blk desired block to be added
     * @throws IllegalArgumentException if block is incompatible with rest of chain
     */
    public void append(Block blk) throws IllegalArgumentException {
        
    }
    
    /**
     * Attempts to remove the last block in the chain
     * @return whether or not successfully removed
     */
    public boolean removeLast() {
        
    }
    
    /**
     * Gets the hash of the last block in the chain
     * @return hash (object) of last block
     */
    public Hash getHash() {
        
    }
    
    /**
     * Checks whether the block chain is valid by checking the transaction validity
     * @return validity of chain
     */
    public boolean isValidBlockChain() {
        
    }
    
    /**
     * Prints the balances of Alice and Bob based on the transactions recorded in the blocks.
     */
    public void printBalances() {
        
    }
    
    /**
     * Returns the block chain as a string containing all blocks
     * @return string with all blocks in the chain on individual lines
     */
    @Override
    public String toString() {
        
    }
}
