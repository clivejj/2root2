import java.util.ArrayList;
import java.lang.RuntimeException;

public class ArrayPriorityQueue<T extends Comparable <T>> {
    
    private ArrayList<T> _data;
    
    public ArrayPriorityQueue(){
	_data = new ArrayList<T>();
    }
    
    public void add(T x){
	_data.add(x);
    }//O(1)
    
    public boolean isEmpty(){
	return _data.size() == 0;
    }//O(1)
    
    public int size(){
        return _data.size();
    }
    
    public T peekMin(){
	if (isEmpty()){
	    throw new RuntimeException();
	}
	T min = _data.get(0);
	for (int i = 1; i < _data.size(); i ++){
	    T current = _data.get(i);
	    if (current.compareTo(min) < 0){
		min = current;
	    }
	}
	return min;
    }//O(n)
    
    public T removeMin(){
	if (isEmpty()){
	    throw new RuntimeException();
	}
	T min = _data.get(0);
	//index which min is located at
	int index = 0;
	for (int i = 1; i < _data.size(); i ++){
	    if (_data.get(i).compareTo(min) < 0){
		min = _data.get(i);
		index = i;
	    }
	}
	_data.remove(index);
	return min;
    }//O(n)
    
    public String toString(){
	return _data.toString();
    }

    public boolean contains(T x) {
	for (T i : _data) {
	    if (i == x) {
		return true;
	    }
	}
	return false;
    }
	

    /*
    public static void main(String[] args){
	ArrayPriorityQueue<Ticket> bob = new ArrayPriorityQueue<Ticket>();
	bob.add(new Ticket(0, 2, "little issue", "Jon"));
	//System.out.println(bob);
	bob.add(new Ticket(1, 1, "bigger issue", "Bon"));
	//System.out.println(bob);
	bob.add(new Ticket(2, 0, "huge issue", "Jovi"));
	System.out.println(bob);
	System.out.println("test removal...");
	System.out.println( bob.removeMin() );
	
	System.out.println("test removal...");
	System.out.println( bob.removeMin() );
	
	System.out.println("test removal...");
	System.out.println( bob.removeMin() );
	System.out.println(bob);
	ArrayPriorityQueue<Integer> bob2 = new ArrayPriorityQueue<Integer>();
	bob2.add(4);
	bob2.add(8);
	bob2.add(2);
	System.out.println(bob2.removeMin());
	System.out.println(bob2.removeMin());
	System.out.println(bob2.removeMin());
    }
    */
    
}
