import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.Test;

public class HuffmanListFundTest {

    /* JUnit tests of fundamental functionality
     *   
     * Use these JUnit tests to ensure that your code correctly 
     * implements the fundamental functionality.
    */
	
	@Test
	public void testByteArrayArgumentConstructor() {
		HuffmanList hList = new HuffmanList(new byte[]{(byte)'a',(byte)'b',(byte)'a'});
		Iterator<HuffmanNode> iter = hList.iterator();
		assertTrue("HuffmanList constructed from byte array ['a','b','a'] should not be empty",iter.hasNext());
		HuffmanNode hNode = iter.next();
		assertEquals("The first element of HuffmanList should contain byte 'b'",(byte)'b',hNode.b);
		assertEquals("The first element of HuffmanList should be with count 1",1,hNode.count);

		assertTrue("HuffmanList constructed from byte array ['a','b','a'] should have 2 elements",iter.hasNext());
		hNode = iter.next();
		assertEquals("The second element of HuffmanList should contain byte 'a'",(byte)'a',hNode.b);
		assertEquals("The second element of HuffmanList should be with count 2",2,hNode.count);

		assertFalse("HuffmanList constructed from byte array ['a','b','a'] should only have 2 elements",iter.hasNext());
	}
	
	@Test
	public void testStringArgumentConstructor()throws IllegalArgumentException {
		char[] charList = new char[]{' ', 'd', 'e', 'h', 'r', 'w', 'o', 'l'};
		int[] countList = new int[]{1, 1, 1, 1, 1, 1, 2, 3};
		
		HuffmanList list = new HuffmanList("file.txt");

		HuffmanNode hNode;
		Iterator<HuffmanNode> iter = list.iterator();
		for (int i = 0; i < 8; i++) {
			assertTrue("HuffmanList constructed from file 'file.txt' should create a list length 8. " +
					"However, your list only has " + i + " elements", iter.hasNext());
	
			hNode = iter.next();
			assertEquals("The #" + (i+1) + " element in the HuffmanList, constructed from file 'file.txt', " 
					+ "should be character '" + charList[i] + "'.", (byte)charList[i], hNode.b);
			assertEquals("The count of #" + (i+1) + " element, '" + (byte)charList[i] + "'," 
					+ " in the HuffmanList, which constructed from file 'file.txt', should be " 
					+ countList[i] + ".", countList[i], hNode.count);
		}
		assertFalse("HuffmanList constructed from file 'file.txt' should create a list length 8. " +
				"However, your list has the 9-th element.", iter.hasNext());
	}
	
	@Test
	public void testIterator() throws IOException {
		HuffmanList list = new HuffmanList("file.txt"); 
		Iterator<HuffmanNode> it = list.iterator();
		assertEquals("this method provides an iterator of the list. "+
				"check that the iterator has values", true,it.hasNext());
		HuffmanNode hn = it.next();
		assertEquals("this method provides an iterator of the list. "+
				"check the byte value of the first node in the list ", (byte)' ',hn.b);
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		hn = it.next();
		assertEquals("this method provides an iterator of the list. "+
				"check the count value of the last node in the list ", 3,hn.count);
		assertEquals("this method provides an iterator of the list. "+
				"check that the hasNext is false as we finished all the elements in iterator ", 
				false,it.hasNext());
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
	
	@Test
	public void testByteAndIntegerArrayConstructor() throws IllegalArgumentException {
		HuffmanList list2 = new HuffmanList(new byte[]{(byte)'d',(byte)'c',(byte)'b',(byte)'a'},new int[]{1,1,2,4});
		
		assertEquals("First value should be the same",
				(byte)'c',list2.getList().get(0).b);
		assertEquals("Second value should be the same",
				(byte)'d',list2.getList().get(1).b);
		assertEquals("Third value should be the same",
				(byte)'b',list2.getList().get(2).b);
		
	}
}