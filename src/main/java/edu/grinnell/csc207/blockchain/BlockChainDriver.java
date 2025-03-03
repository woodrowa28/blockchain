package edu.grinnell.csc207.blockchain;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * The main driver for the block chain program.
 */
public class BlockChainDriver {
   
    /**
     * The main entry point for the program.
     * @param args the command-line arguments
     * @throws NoSuchAlgorithmException upon hashing failure
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        if (args.length != 1) {
            System.err.println("Usage: java BlockChainDriver <startingBalance>");
            System.exit(1);
        } else if (Integer.parseInt(args[0]) < 0) {
            System.err.println("Starting balance must be non-negative");
            System.exit(1);
        }
        BlockChain chain = new BlockChain(Integer.parseInt(args[0]));
        runLoop(chain);
    }
    
    /**
     * Repeatedly prompts user for input while the program hasn't been terminated
     * @param chain block chain data
     * @throws NoSuchAlgorithmException upon hash failure
     */
    public static void runLoop(BlockChain chain) throws NoSuchAlgorithmException {
        Scanner input = new Scanner(System.in);
        boolean stillRunning = true;
        String command;
        while (stillRunning) {
            System.out.print(chain.toString());
            System.out.print("Command? ");
            command = input.nextLine();
            stillRunning = performAction(chain, command, input);
        }
    }
    
    /**
     * Executes the command given by the user
     * @param chain block chain data
     * @param command string command to be matched with action
     * @param input scanner to receive user input (from System.in)
     * @return whether or not to keep looping for user feedback (false if received "quit")
     * @throws NoSuchAlgorithmException upon hash failure
     */
    public static boolean performAction(BlockChain chain, String command, Scanner input) 
            throws NoSuchAlgorithmException{
        int amount;
        long nonce;
        Block block;
        switch (command) {
            case "mine":
                System.out.print("Amount transferred? ");
                amount = getIntInput(input);
                block = new Block(chain.getSize(), amount, chain.getHash());
                System.out.println("amount = " + block.getAmount() + ", nonce = " 
                                    + block.getNonce());
                break;
            case "append":
                System.out.print("Amount transferred? ");
                amount = getIntInput(input);
                System.out.print("Nonce? ");
                nonce = getLongInput(input);
                chain.append(new Block(chain.getSize(), amount, chain.getHash(), nonce));
                break;
            case "remove":
                chain.removeLast();
                break;
            case "check":
                System.out.println("Chain is " + (chain.isValidBlockChain() ? 
                        "valid!" : "invalid!"));
                break;
            case "report":
                chain.printBalances();
                break;
            case "help":
                printHelp();
                break;
            case "quit":
                return false;
            default:
                System.out.println("Invalid command. Please type \"help\" for valid commands.");
                break;
        }
        System.out.println("");
        return true;
    }
    
    /**
     * Prompts user for integer input until a valid number is received
     * @param input scanner to receive user input (from System.in)
     * @return user-inputted integer
     */
    public static int getIntInput(Scanner input) {
        boolean validInput = false;
        int num = 0;
        do {
            if (input.hasNextInt()) {
                num = input.nextInt();
                validInput = true;
            } else {
                System.out.println("Invalid input type. Must be an integer.");
            }
            input.nextLine();
        } while (!validInput);
        return num;
    }
    
    /**
     * Prompts user for long input until a valid number is received
     * @param input scanner to receive user input (from System.in)
     * @return user-inputted long
     */
    public static long getLongInput(Scanner input) {
        boolean validInput = false;
        long num = 0;
        do {
            if (input.hasNextInt()) {
                num = input.nextLong();
                validInput = true;
            } else {
                System.out.println("Invalid input type. Must be a long integer.");
            }
            input.nextLine();
        } while (!validInput);
        return num;
    }
    
    /**
     * Prints all possible commands used for program functionality.
     */
    public static void printHelp() {
        System.out.println("Valid commands:");
        System.out.println("\tmine: discovers the nonce for a given transaction");
        System.out.println("\tappend: appends a new block onto the end of the chain");
        System.out.println("\tremove: removes the last block from the end of the chain");
        System.out.println("\tcheck: checks that the block chain is valid");
        System.out.println("\treport: reports the balances of Alice and Bob");
        System.out.println("\thelp: prints this list of commands");
        System.out.println("\tquit: quits the program\n");
    }
}
