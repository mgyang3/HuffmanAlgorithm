import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.util.*;
public class ByteCounter {
	private byte[] bytes;
	private byte[] sortedBytes;
	private String order;
	private int[] count;
	public ByteCounter(byte[] x){
		bytes=x;
		order = "byte";
		count=getCount(bytes);
	}//constructor that takes array full of bytes to be counted
	public ByteCounter(String file) throws IOException{
		Path p = Paths.get(file);
		try{
			byte[] x = Files.readAllBytes(p);
			bytes=x;
			order="byte";
			count=getCount(bytes);
		}//tries to get the file
		catch (IOException e){	
		}//catches the exception if file isn't real
	}//constructor that takes name of file to read in binary
	
	public void setCount(int[] x){
		count=x;
	}//changes the counts 
	public int getCount(byte b){
		int count=0;
		for(int i=0; i<bytes.length;i++)
			if(bytes[i]==b)
				count++;//if byte is equal to b, then it gets added
		return count;
	}
	public int[] getCount(){
		return count;
	}
	public int[] getCount(byte[] b){
		int[] tmp = new int[b.length];//max amount of bytes possible to be contained (1 count of each)
		for(int i=0;i<b.length;i++){
			tmp[i]= getCount(b[i]);
		}//should go through the given array

		return tmp;
	}//ends get count

	public String toString() {	
		StringBuilder sb = new StringBuilder();
		setOrder("byte");
		byte[] real=getElements();
		int[] x =getCount(real);			
		for(int i=0;i<real.length;i++){
			sb.append(real[i]+":"+x[i]+" ");
		}//adds to string builder
		return sb.toString().trim();

	}//ends toString

	public String toString(String format){
		
		StringBuilder sb = new StringBuilder();
	
		byte[] real=getElements();
		int[] x =getCount(real);	
		for(int i=0;i<real.length;i++){
			sb.append((char)real[i]+":"+x[i]+" ");
		}//adds to string builder
		sb.setLength(sb.length()-1);
		return sb.toString();
	}//prints out ascii values of the values

	public void setOrder(String string){
		if(order.equals("byte") || order.equals("countInc") || order.equals("countDec")){
			order=string;
		}
		else throw new IllegalArgumentException("Invalid String"); 

	}
	public byte[] getElements(){
		if(order.equals("byte")){
			Arrays.sort(bytes);
			sortedBytes = removeDuplicates(bytes);
			count=getCount(bytes);

		}//increasing byte count
		else if(order.equals("countInc")){
			ArrayList<Byte> temp= new ArrayList<Byte>();
			byte[] clone = bytes.clone();//identical to manipulate
			int[] first = getCount(clone);
			Arrays.sort(clone);//sorts the bytes from least to greatest
			Arrays.sort(first);//sorted from least to greatest
			first=removeDuplicates(first);
			for(int i=0;i<first.length;i++){
				for(int j=i;j<clone.length;j++){//this and previous for loop should have the same length
					if(first[i]==getCount(clone[j])){//when adding to the stack, it should be ordered already since the its already been fixed
						temp.add(clone[j]);	//use an arraylist here to prevent values from being overwritten
					}//what to do if the count is equal to the value
				}//second for
			}//ends first for
			byte[] x = new byte[temp.size()];
			for(int i=0;i<x.length;i++)
				x[i]=temp.get(i);
			sortedBytes =removeDuplicates(x);

		}//increasing by count order

		else if(order.equals("countDec")){
			//simple because can order the countInc version and simply flip the order
			setOrder("countInc");//acquire ordered version of the bytes
			byte[] flipped = new byte[sortedBytes.length];
			int counter=0;
			for(int i=sortedBytes.length-1;i>=0;i--){
				flipped[counter]=sortedBytes[i];
				counter++;
			}
			sortedBytes=flipped;
		}//order by decreasing count order
		return sortedBytes;
	}//ends getElements
	
	private byte[] removeDuplicates(byte[] tmp){
		ArrayList<Byte> remover = new ArrayList<Byte>();
		for(int i=0;i<tmp.length;i++){
			remover.add(tmp[i]);
		}//adds all values into array list. next: take out everything
		for(int i=0;i<remover.size();i++){
			for(int j=i+1;j<remover.size();j++){
				if(remover.get(i)==remover.get(j)){
					remover.remove(j);
					j--;
				}
			}
		}//ends for
		//need to check last one
		if(remover.get(remover.size()-2)==(remover.get(remover.size()-1)))
			remover.remove(remover.size()-1);

		byte[] real = new byte[remover.size()];
		for(int i=0;i<remover.size();i++)
			real[i]=remover.get(i);
		return real;

	}//gets rid of everything that is not unique. keeps the first location

	// Create an array with all unique elements
	public int[] removeDuplicates(int[] x) {
		if (x.length < 2)
			return x;
		int j = 0;
		int i = 1;
		while (i<x.length){
			if(x[i]==x[j])
				i++;
			else {
				j++;
				x[j] = x[i];
				i++;
			}//ends else
		}//end while
		int[] tmp = Arrays.copyOf(x,j+1);
		return tmp;
	}

}//ends class
