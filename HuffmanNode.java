
public class HuffmanNode {
	public byte b;
	public int count; 
	public boolean[] code;
	public HuffmanNode left,right;
	
	public HuffmanNode(byte x, int c){
		count = c;
		b=x;
		left=null;
		right=null;
	}//constructor
	
	public void setCount(int x){
		count=x;
	}//changes the count to something new
	public void setCode(boolean[] x){
		code=x;
	}
	public boolean hasLeft(){
		if(left!=null)
			return true;
		else return false;
	}
	public boolean hasRight(){
		if(right!=null)
			return true;
		else return false;
	}
	
}//ends class
