package edu.grinnell.csc207.blockchain;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A single block of a block chain.
 */
public class Block {
    private int blockNum;
    
    private int transferAmount;
    
    private Hash prevHash;
    
    private long nonce;
    
    private Hash currHash;
    
    // Holds the size of the data to be hashed, other than prevHash's bytes
    // Comes from two ints and one double (4 + 4 + 8)
    private final int DATA_SIZE = 16;
    
    /**
     * Creates a new Block object with given values, mining for a nonce and building a hash.
     * @param num number of block in chain
     * @param amount amount of money transferred between accounts
     * @param previousHash hash of previous block in chain
     * @throws NoSuchAlgorithmException upon hashing error
     */
    public Block(int num, int amount, Hash previousHash) throws NoSuchAlgorithmException {
        blockNum = num;
        transferAmount = amount;
        prevHash = previousHash;
        nonce = -1;
        currHash = mine();
    }
    
    /**
     * Creates a new Block object with given values (including nonce).
     * @param num number of block in chain
     * @param amount amount of money transferred between accounts
     * @param previousHash hash of previous block in chain
     * @param nonceNum nonce value to hash with
     * @throws NoSuchAlgorithmException upon hashing error
     */
    public Block(int num, int amount, Hash previousHash, long nonceNum) throws NoSuchAlgorithmException {
        blockNum = num;
        transferAmount = amount;
        prevHash = previousHash;
        nonce = nonceNum;
        currHash = createHash();
    }
    
    
    /**
     * Gets a valid Hash and nonce value by repeatedly trying increasing nonce values 
     * until the hash is valid.
     * @return new hash object with valid nonce and hash
     * @throws NoSuchAlgorithmException upon hashing error
     */
    private Hash mine() throws NoSuchAlgorithmException {
        Hash newHash;
        do {
            nonce++;
            newHash = createHash();
        } while (!newHash.isValid());
        return newHash;
    }
    
    /**
     * Creates a new Hash object by hashing all data in the block
     * @return Hash object containing encrypted block data
     * @throws NoSuchAlgorithmException upon hashing error
     */
    private Hash createHash() throws NoSuchAlgorithmException {
        int prevHashSize = prevHash == null ? 0 : prevHash.getData().length;
        ByteBuffer buf = ByteBuffer.allocate(DATA_SIZE + prevHashSize);
        buf.putInt(blockNum);
        buf.putInt(transferAmount);
        if (prevHashSize != 0) {
            buf.put(prevHash.getData());
        }
        buf.putLong(nonce);
        
        MessageDigest md = MessageDigest.getInstance("sha-256");
        md.update(buf.array());
        return new Hash(md.digest());
    }
    
    /**
     * Gets where the block is in the block chain.
     * @return blockNum
     */
    public int getNum() {
        return blockNum;
    }
    
    /**
     * Gets the amount of money transferred held by this block
     * @return transferAmount
     */
    public int getAmount() {
        return transferAmount;
    }
    
    /**
     * Gets the nonce value associated with this block.
     * @return nonce
     */
    public long getNonce() {
        return nonce;
    }
    
    /**
     * Gets the hash value/object of the previous block in the chain
     * @return prevHash
     */
    public Hash getPrevHash() {
        return prevHash;
    }
    
    /**
     * Gets the hash value/object of this block
     * @return currHash
     */
    public Hash getHash() {
        return currHash;
    }
    
    /**
     * Returns a representation of the Block as a string
     * @return string containing a formatted version of all Block fields
     */
    @Override
    public String toString() {
        return "Block " + blockNum + " (Amount: " + transferAmount + ", Nonce: " + nonce + 
               ", prevHash: " + prevHash + ", hash: " + currHash + ")";
    }
}
