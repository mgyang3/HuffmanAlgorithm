import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
public class HuffmanCoder {
	private BinaryWriter writer;
	private HuffmanCode x;
	private LinkedList<HuffmanNode> tmp;
	public HuffmanCoder(String string, String string2) throws FileNotFoundException {
		writer= new BinaryWriter(string2);
		x = new HuffmanCode(string);
		tmp = x.getLinkedList();

	}

	public void compress() throws IOException {
		for(int i=0;i<tmp.size();i++){
			System.out.print((char)tmp.get(i).b+":");
			System.out.println(x.codeString(tmp.get(i).b));
			writer.writeBinaryArray(x.code(tmp.get(i).b));
		}
		writer.close();

	}//ends compress

}//ends class
