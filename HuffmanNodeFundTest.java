import static org.junit.Assert.*;

import org.junit.Test;


public class HuffmanNodeFundTest {
	
	/* JUnit tests of fundamental functionality
     *   
     * Use these JUnit tests to ensure that your code correctly 
     * implements the fundamental functionality.
     */
	
	@Test
    public void testArrayArgumentConstructorAndToString() {
    	HuffmanNode hn = new HuffmanNode((byte)'a', 5);

    	assertEquals("Your constructors should initialize the byte and count members " + 
            "based on the passed arguments", 
            (byte)'a', hn.b);
    	assertEquals("Your constructors should initialize the byte and count members " + 
                "based on the passed arguments", 
                5, hn.count);
    	assertEquals("Your constructors should initialize the byte and count members " + 
                "based on the passed arguments. The left branch should be Null", 
                null, hn.left);
                assertEquals("Your constructors should initialize the byte and count members " + 
                "based on the passed arguments. The right branch should be Null", 
                null, hn.right);
    }
	
	/* Other JUnit tests.
     * 
     * Write your own JUnit tests below to check the correctness of your implementation.
     * 
     * When you turn in your draft (and final) we will run our own test suite on your code 
     * and provide you with the feedback.
     * 
     * Your draft code should contain a complete set of methods as specified in the assignment.
     * Any methods not yet implemented should be written as skeleton methods with an empty body. 
     * 
	 * The JUnit tests below help to ensure that your methods compile with our test suite and have 
	 * correctly typed arguments. You can replace them with more meaningful tests to test their
	 * functionalities.
     */
	
	// This test ensure you make each member in HuffmanNode public
	@Test
	public void testPublicMember() {
		HuffmanNode hn = new HuffmanNode((byte)'a', 5);
		System.out.println(hn.b + hn.count + "" + ((hn.code == null) ? (hn.left==hn.right) : 1));
	}

}