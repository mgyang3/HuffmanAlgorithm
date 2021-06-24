import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;


public class HuffmanCodeFundTest {

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
	public void testByteArrayArgumentConstructor() {
		HuffmanCode hc= new HuffmanCode(new byte[] {(byte)'a',(byte)'b',(byte)'c',(byte)'a',(byte)'a',(byte)'b',(byte)'a',(byte)'d'});
		assertTrue("The constructor make a HuffmanCode using byte array",
				true);
		String s=hc.codeString((byte)'a');
		String x=hc.codeString((byte)'b');
		String z=hc.codeString((byte)'c');
		String l=hc.codeString((byte)'d');

		boolean[] code = hc.code((byte)'a');
		boolean[] code1= hc.code((byte)'b');
		assertEquals("returns an array of length one"
				,1,code.length);
		assertEquals("returns an array of length one"
				,2,code1.length);
		assertEquals("find a codestring",
				"1",s);
		assertEquals("find a codestring",
				"01",x);
		assertEquals("find a codestring",
				"000",z);
		assertEquals("find a codestring",
				"001",l);
	}
	@Test
	public void testByteArrayConstructorandCodeString(){
		HuffmanCode hc= new HuffmanCode(new byte[] {(byte)'a',(byte)'b',(byte)'c',(byte)'a',(byte)'a',(byte)'b',(byte)'a',(byte)'d'});
		//HuffmanCode hc= new HuffmanCode(new byte[] {(byte)'a', (byte)'b',(byte)'d', (byte)'d',(byte)'c', (byte)'e',(byte)'a', (byte)'b', (byte)'d', (byte)'d',(byte)'b', (byte)'b',(byte)'e', (byte)'d',(byte)'d', (byte)'e',(byte)'d', (byte)'e',(byte)'b', (byte)'d',(byte)'b',(byte)'e'});
		assertTrue("The constructor makes a HuffmanCode using byte array",true);
		String s=hc.codeString((byte)'a');
		String x=hc.codeString((byte)'b');
		String z=hc.codeString((byte)'c');
		String l=hc.codeString((byte)'d');
		boolean[] code = hc.code((byte)'a');
		boolean[] code1= hc.code((byte)'b');
		assertEquals("returns an array of length one"
				,1,code.length);
		assertEquals("returns an array of length one"
				,2,code1.length);
		assertEquals("find a codestring",
				"1",s);
		assertEquals("find a codestring",
				"01",x);
		assertEquals("find a codestring",
				"000",z);
		assertEquals("find a codestring",
				"001",l);
	}

	@Test
	public void testStringArgumentConstructor() throws IOException {
		HuffmanCode hc = new HuffmanCode("file.txt");
		assertTrue("The constructor make a HuffmanCode from a file",
				true);
		assertEquals("Codestring",
				"10",hc.codeString((byte)'l'));
		assertEquals("Codestring",
				"011",hc.codeString((byte)'h'));
		assertEquals("Codestring",
				"001",hc.codeString((byte)'w'));
	
	}

	@Test
	public void testByteAndCountArraysConstructor() {
		HuffmanCode hc = new HuffmanCode(new byte [] {(byte)'c', (byte)'d',(byte)'b',(byte)'a'}, new int [] {1,1,2,4});
		assertTrue("The constructor make a HuffmanCode using byte and count arrays",
				true);
		String s=hc.codeString((byte)'a');
		String x=hc.codeString((byte)'b');
		String z=hc.codeString((byte)'c');
		String l=hc.codeString((byte)'d');
		
		boolean[] code = hc.code((byte)'a');
		boolean[] code1= hc.code((byte)'b');
		assertEquals("returns an array of length one"
				,1,code.length);
		assertEquals("returns an array of length one"
				,2,code1.length);
		assertEquals("find a codestring",
				"1",s);
		assertEquals("find a codestring",
				"01",x);
		assertEquals("find a codestring",
				"000",z);
		assertEquals("find a codestring",
				"001",l);
	}

	@Test
	public void testCodeMethod() {
		HuffmanCode hc= new HuffmanCode(new byte[] {(byte)'a',(byte)'b',(byte)'c',(byte)'a',(byte)'a',(byte)'b',(byte)'a',(byte)'d'});
		boolean[] code= hc.code((byte)'a');
		boolean[] coder = hc.code((byte)'b');
		assertEquals("array length of a should be 1",
				1, code.length);
		assertEquals("array length of b should be 2",
				2, coder.length);
	}

	@Test
	public void testCodeStringMethod() {
		HuffmanCode hc = new HuffmanCode(new byte [] {(byte)'a', (byte)'b'}, new int [] {2, 3});
		boolean[] code = hc.code((byte)'b');
		String s = hc.codeString((byte)'b');
		String x = hc.codeString((byte)'a');
		boolean[] coder=hc.code((byte)'a');
		assertTrue("This method returns the code of specific byte",
				true);
		assertEquals("Strings should be equal",
				"1",s);
		String check = null;
		if(code[0]==true)
			check="1";
		assertEquals("Strings should be the same as code",
				check,s);
		String check2=null;

		if(coder[0]==false)
			check2="0";
		assertEquals("Strings match",
				check2,x);

	}

	@Test
	public void testToStringMethod() {
		HuffmanCode hc = new HuffmanCode(new byte [] {(byte)'a', (byte)'b'}, new int [] {2, 3});
		String s = hc.toString();
		assertTrue("This method returns astring containing the table of the binary encodings of each byte in the Huffman tree",
				true);
		assertEquals("Should have equal things",(byte)'a'+": "+"0"+"\n"+(byte)'b'+": "+"1",s);
	}
}