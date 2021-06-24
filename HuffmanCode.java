import java.io.*;
import java.util.*;

public class HuffmanCode{
	private HuffmanNode root;
	private LinkedList<HuffmanNode> list,tracker,returnedValues;
	public HuffmanCode(String string) throws IllegalArgumentException{
		HuffmanList list2=new HuffmanList(string);
		ByteCounter count = list2.getByteCounter();
		byte[] bs=count.getElements();
		int[] is = count.getCount(bs);

		//doing same thing as other constructors
		list=new LinkedList<HuffmanNode>();
		tracker=new LinkedList<HuffmanNode>();
		returnedValues=new LinkedList<HuffmanNode>();//holds values least to greatest

		for(int i=0;i<bs.length;i++){
			list.add(new HuffmanNode(bs[i],is[i]));//creates the list
			tracker.add(new HuffmanNode(bs[i],is[i]));//creates an extra list for use
		}

		HuffmanNode first;
		HuffmanNode second;
		HuffmanNode combo = null;
		while(list.size()>1){

			first = list.remove();
			second = list.remove();
			combo = new HuffmanNode((byte)'0',first.count+second.count);

			combo.left=first;
			combo.right=second;
			if(first.b!=(byte)'0')
				returnedValues.addFirst(first);
			if(second.b!=(byte)'0')
				returnedValues.addFirst(second);//adds largest to the the first position, slowly creating descending order

			//insert right before <= count
			for(int i=0;i<list.size();i++){
				if(combo.count<=list.get(i).count){
					//adds back in at location i
					//ensures that can't be larger than whats on the right, and larger than whats on the left
					list.add(i,combo);
					break;//leave loop
				}//ends if
				if(i==list.size()-1){

					list.add(combo);
					break;
				}
			}//ends for
		}//ends while
		root=combo;
		//root=list.remove();
		assignCode(root,new boolean[0],false);//passing new root with the size of zero
		//give leaf nodes code values

	}//creates HuffmanTree from string name of file

	public HuffmanCode(byte[] bs){
		HuffmanList list2 = new HuffmanList(bs);
		ByteCounter count= list2.getByteCounter();
		byte[] test= count.getElements();
		int[] is = count.getCount(test);

		bs=test;
		list=new LinkedList<HuffmanNode>();
		tracker=new LinkedList<HuffmanNode>();
		returnedValues=new LinkedList<HuffmanNode>();//holds values least to greatest

		for(int i=0;i<bs.length;i++){
			list.add(new HuffmanNode(bs[i],is[i]));//creates the list
			tracker.add(new HuffmanNode(bs[i],is[i]));//creates an extra list for use
		}
		HuffmanNode first;
		HuffmanNode second;
		HuffmanNode combo = null;
		while(list.size()>1){

			first = list.remove();
			second = list.remove();
			combo = new HuffmanNode((byte)'0',first.count+second.count);
			combo.left=first;
			combo.right=second;
			if(first.b!=(byte)'0')
				returnedValues.addFirst(first);
			if(second.b!=(byte)'0')
				returnedValues.addFirst(second);//adds largest to the the first position, slowly creating descending order

			//insert right before <= count
			for(int i=0;i<list.size();i++){

				if(combo.count<=list.get(i).count){
					//adds back in at location i
					//ensures that can't be larger than whats on the right, and larger than whats on the left
					list.add(i,combo);
					break;//leave loop
				}//ends if
				if(i==list.size()-1){
					list.add(combo);
					break;
				}
			}//ends for
		}//ends while
		root=combo;

		//root=list.remove();
		assignCode(root,new boolean[0],true);//passing new root with the size of zero
		//give leaf nodes code values
	}//created from stack of bytes

	public HuffmanCode(byte[] bs, int[] is) {
		HuffmanList organize = new HuffmanList(bs,is);
		ByteCounter count = organize.getByteCounter();
		list=new LinkedList<HuffmanNode>();
		tracker=new LinkedList<HuffmanNode>();
		returnedValues=new LinkedList<HuffmanNode>();//holds values least to greatest
		list=organize.getList();
		tracker=(LinkedList<HuffmanNode>) list.clone();
		HuffmanNode first;
		HuffmanNode second;
		HuffmanNode combo = null;
		while(list.size()>1){

			first = list.remove();
			second = list.remove();
			combo = new HuffmanNode((byte)'0',first.count+second.count);
			combo.left=first;
			combo.right=second;
			if(first.b!=(byte)'0')
				returnedValues.addFirst(first);
			if(second.b!=(byte)'0')
				returnedValues.addFirst(second);//adds largest to the the first position, slowly creating descending order

			//insert right before <= count
			for(int i=0;i<list.size();i++){

				if(combo.count<=list.get(i).count){
					//adds back in at location i
					//ensures that can't be larger than whats on the right, and larger than whats on the left
					list.add(i,combo);
					break;//leave loop
				}//ends if
				if(i==list.size()-1){

					list.add(combo);
					break;
				}
			}//ends for
		}//ends while
		root=combo;
		//root=list.remove();
		assignCode(root,new boolean[0],true);//passing new root with the size of zero
		//give leaf nodes code values
	}//ends constructing from given arrays

	public boolean[] code(byte b){
		boolean checker = false;
		int x=0;//count for particular byte
		for(int i=0;i<tracker.size();i++){
			if(tracker.get(i).b==b){
				x=tracker.get(i).count;
				checker=true;//found specific byte in nodes
				break;//found the count of specific byte, and don't need the loop anymore	
			}//ends if
		}//ends for
		if(checker==false)
			throw new IllegalArgumentException();
		HuffmanNode walker = new HuffmanNode((byte)'0',root.count);//declaring is necessary to avoid null pointer error

		walker.left=root.left;
		walker.right=root.right;
		walker=Search(walker,b);

		boolean[] g=walker.code;
		return g;
	}//ends code

	public String codeString(byte b){
		boolean[] tmp=code(b);//calls for code which will throw the exception if it doesn't exist
		String s = "";
		for(int i=0;i<tmp.length;i++){
			if(tmp[i]==false)
				s+="0";
			else if(tmp[i]==true)
				s+="1";
		}//ends for
		return s;
	}
	public String toString(){//not recursive. consider using extra linkedlist and access the code for each
		String s=" ";
		for(int i=0;i<returnedValues.size();i++)
			for(int j=i+1;j<returnedValues.size();j++){
				if(codeString(returnedValues.get(i).b).length()==codeString(returnedValues.get(j).b).length())
					if(returnedValues.get(i).b>returnedValues.get(j).b){//earlier byte is larger than other byte
						HuffmanNode tmp = returnedValues.get(i);
						returnedValues.set(i,returnedValues.get(j));
						returnedValues.set(j,tmp);	
					}
			}//ends for
		for(int i=0;i<returnedValues.size();i++){
			String tmp = codeString(returnedValues.get(i).b);
			s=s+returnedValues.get(i).b+": "+tmp+"\n";

		}
		s=s.trim();//cuts off last extra line
		return s;

	}
	private void assignCode(HuffmanNode root, boolean[] tmp,boolean give){

		if(root.b!=(byte)'0'){
			tmp[tmp.length-1]=give;//should have one empty spot
			root.code=tmp;
			return;}//base case
		if(tmp.length!=0){
			tmp[tmp.length-1]=give;
		}
		assignCode(root.left,Arrays.copyOf(tmp, tmp.length+1),false);//recursive goes left
		assignCode(root.right,Arrays.copyOf(tmp, tmp.length+1),true);//recursive goes right

	}//gives boolean code 
	public LinkedList<HuffmanNode> getLinkedList(){
		return tracker;
	}
	private HuffmanNode Search(HuffmanNode root,byte b){
		if(root!=null&&root.b==b)
			return root;//base case
		HuffmanNode left=null;
		if(root!=null &&root.left!=null)
			left = Search(root.left,b);//recursive goes left
		if (left == null&&root!=null&&root.right!=null)
			return Search(root.right,b);//recursive goes right
		else
			return left;
	}
}//ends class
