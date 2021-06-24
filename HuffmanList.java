import java.io.*;
import java.util.*;
public class HuffmanList {
	private LinkedList<HuffmanNode> list=new LinkedList<HuffmanNode>();
	private ByteCounter count;
	public HuffmanList(byte[] b){
		count = new ByteCounter(b);
		count.setOrder("countInc");
		byte[] sorted = count.getElements();
		int[] x = count.getCount(sorted);

		for(int i=0;i<sorted.length;i++){
			list.add(new HuffmanNode(sorted[i],x[i]));
		}//ends for
	}//creating a linked list from an array

	public HuffmanList(String file){
		try {
			count = new ByteCounter(file);//file is turned into bytes
			count.setOrder("countInc");
			byte[] sorted = count.getElements();
			int[] x = count.getCount(sorted);
			for(int i=0;i<sorted.length;i++){
				list.add(new HuffmanNode(sorted[i],x[i]));}
			//	reader=list.iterator();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//creating linked list through a word file

	public HuffmanList(byte[] bs, int[] is) throws IllegalArgumentException {
		
		for(int i=0;i<is.length;i++)
			if(is[i]<0)
				throw new IllegalArgumentException("A number is negative");
		for(int i=0;i<bs.length;i++)
			for(int j=i+1;j<bs.length;j++){
				if(bs[i]==bs[j])
					throw new IllegalArgumentException();
			}//checks both bs and is
		//all bytes should be unique and no count should be equal to 0 or negative
		//count = new ByteCounter(bs);
		//count.setCount(is);
		
		for(int i=0;i<is.length-1;i++){
			if(is[i]>is[i+1]){
				int tmp=is[i+1];
				is[i+1]=is[i];
				is[i]=tmp;//swapping places
				byte replace = bs[i+1];
				bs[i+1]=bs[i];
				bs[i]=replace;
				i=0;//restart to check everything
			}//ends if
		}//sorts the numbers in order
		
		for(int i=0;i<bs.length;i++){
			for(int j=i+1;j<bs.length;j++){
				if(bs[i]>bs[j]&&is[i]==is[j]){//counts are the same and byte is larger than something after it
					byte swap=bs[i];
					bs[i]=bs[j];
					bs[j]=swap;
					
				}//switch places
			}
		}//sorting the bytes
		
		count = new ByteCounter(bs);
		count.setCount(is);
		for(int i=0;i<bs.length;i++)
			list.add(new HuffmanNode(bs[i],is[i]));
	}//creates list based off of given values

	public Iterator<HuffmanNode> iterator(){
		return list.iterator();
	}
	public LinkedList<HuffmanNode> getList(){
		return list;
	}
	public ByteCounter getByteCounter(){
		return count;
	}


}//ends class
