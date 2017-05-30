import java.util.ArrayList;
import java.lang.RuntimeException;

public class ArrayPriorityQueue<T extends Comparable <T>> {
    
    private ArrayList<T> _data;
    
    public ArrayPriorityQueue(){
	_data = new ArrayList<T>();
    }
    
    public void add(T x){
	if (isEmpty()) {
	    _data.add(x);
	    return;
	}
	else {
	    for (int i = 0; i < _data.size(); i++) {
		if (x.compareTo(_data.get(i)) < 0) {
		    _data.add(i,x);
		    return;
		}
	    }
	}
	_data.add(x);
    }//O(n)
	
    
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
	return _data.get(0);
    }//O(1)
    
    public T get(int ind){
    return _data.get(ind);
    }
    
    public T removeMin(){
	if (isEmpty()){
	    throw new RuntimeException();
	}
	return _data.remove(0);
    }//O(1)
    
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

    public ArrayList<T> getData() {
	return _data;
    }

    
    public static void main(String[] args) {
	/*
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
	ArrayPriorityQueue<Integer> b = new ArrayPriorityQueue<Integer>();
	b.add(1);
	b.add(3);
	b.add(1);
	/*b.add(9);
	b.add(12);
	b.add(2);
	b.add(4);
	b.add(8);
	b.add(17);
	System.out.println(b);
	*/
	
    }
}
