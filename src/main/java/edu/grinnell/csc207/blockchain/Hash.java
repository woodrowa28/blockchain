package edu.grinnell.csc207.blockchain;

import java.util.Arrays;

/**
 * A wrapper class over a hash value (a byte array).
 */
public class Hash {
    private byte[] hashData;
    
    /**
     * Creates a new Hash object with the given byte data
     * @param data hash data to be stored
     */
    public Hash(byte[] data) {
        hashData = data;
    }
    
    /**
     * Gets the hash data
     * @return byte array with hash
     */
    public byte[] getData() {
        return hashData;
    }
    
    /**
     * Returns whether or not the given hash value starts with 3 zeroes
     * @return boolean representing validity
     */
    public boolean isValid() {
        if (hashData.length >= 3) {
            return hashData[0] == 0 && hashData[1] == 0 && hashData[2] == 0;
        } else {
            return false;
        }
    }
    
    /**
     * Returns the hash data as a string full of integers
     * @return string version of hash
     */
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder(hashData.length * 2);
        for (int i = 0; i < hashData.length; i++) {
            ret.append(String.format("%1$h", Byte.toUnsignedInt(hashData[i])));
        }
        return ret.toString();
    }
    
    /**
     * Determines whether or not the two objects can be considered equal
     * @param other an object. If not a Hash object, will always return false
     * @return boolean representing equality. If other is a Hash, this is whether the first
     * three indices hold zeroes.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Hash otherHash) {
            return Arrays.equals(hashData, otherHash.getData());
        } else {
            return false;
        }
    }
    
}
