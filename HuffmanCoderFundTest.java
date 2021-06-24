import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;


public class HuffmanCoderFundTest {

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
	
	@Test
	public void testConstructor() throws FileNotFoundException{
    	HuffmanCoder hc = new HuffmanCoder("file.txt", "output.txt");
    	assertTrue("The constructor make a HuffmanCode using byte array",
			true);
	}
	
	@Test
	public void testCompressMethod() throws IOException{
    	HuffmanCoder hc = new HuffmanCoder("file.txt", "output.txt");
    	hc.compress();
    	assertTrue("The constructor make a HuffmanCode using byte array",
			true);
	}

}