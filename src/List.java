/* ***************************************************
 * Christopher Bouton
 * 10-06-18
 *
 * This class implements a linked list abstract data type into Java with Node and List objects.
 * Using generics, we allow for the List and Nodes to use different types (allowing for lists of char, int, and float.)
 *************************************************** */

// the List class
public class List<Type>{
    // fields 
	public static final int MAX_SIZE = 50;
    
    // three references
	private Node<Type> head;
	private Node<Type> tail;
	private Node<Type> curr;
    // length of list
	private int num_items;

	// constructor
	// empty list should have a "size" of -1 and its "position" is at -1
	public List(){
		this.head = this.tail = this.curr = null;
		this.num_items = 0;
	}

	// copy constructor
	// clones the list l and sets the last element as the current
	public List(List l){
		Node<Type> n = l.head;

		this.head = this.tail = this.curr = null;
		this.num_items = 0;

		while (n != null){
			this.InsertAfter(n.getData());
			n = n.getLink();
		}
	}

	// navigates to the beginning of the list
	public void First(){
		this.curr = this.head;
	}

	// navigates to the end of the list
	// the end of the list is at the last valid item in the list
	public void Last(){
		this.curr = this.tail;
	}

	// navigates to the specified element (0-index)
	// this should not be possible for an empty list
	// this should not be possible for invalid positions
	public void SetPos(int pos){
		if (!this.IsEmpty() && pos >= 0 && pos < this.num_items){
			this.curr = this.head;

			// move curr to the specified position
			for (int i=0; i<pos; i++)
				this.curr = this.curr.getLink();
		}
	}

	// navigates to the previous element
	// this should not be possible for an empty list
	// there should be no wrap-around
	public void Prev(){
		if (!this.IsEmpty() && this.curr != this.head){
            // start at the head
			Node<Type> n = this.head;

			// traverse the list, checking for when the node you were at is the "next" one
            // when you've reached that, you're currently at the "previous" node 
			while (n.getLink() != this.curr)
				n = n.getLink();
            
            // set that previous as the current
			this.curr = n;
		}
	}

	// navigates to the next element
	// this should not be possible for an empty list
	// there should be no wrap-around
	public void Next(){
		if (!this.IsEmpty() && this.curr != this.tail)
			this.curr = this.curr.getLink();
	}

	// returns the location of the current element (or -1)
	public int GetPos(){
        // if the list is empty, no current pos, return -1
		if (this.IsEmpty())
			return -1;
        
        // temporary node to traverse the list and find our current
		Node<Type> n = this.head;
		int i = 0;

		// traverse the list to get the current position
		while (n != this.curr){
			n = n.getLink();
			i++;
		}

		return i;
	}

	// returns the value of the current element (or -1)
	public Type GetValue(){
		if (this.IsEmpty())
			return null;
		else
			return this.curr.getData();
	}

	// returns the length of the list (i.e. how many are in the list currently) 
	public int GetSize(){
		return this.num_items;
	}

	// inserts an item before the current element
	// the new element becomes the current
	// this should not be possible for a full list
	public void InsertBefore(Type data){
		if (!this.IsFull()){
			// if the list is empty, just insert after
			if (this.IsEmpty())
				this.InsertAfter(data);
			else{
				// if we're at the beginning of the list, we need to create a new head node
                // that points to the current head node
				if (this.curr == this.head){
					this.head = new Node(); // new head node
					this.head.setData(data);
					this.head.setLink(curr); // make its link be where we currently are
					this.curr = this.head; // now change our current to the new head
					this.num_items++; // and of course increment length by one
				}
				// otherwise, navigate to the previous node and insert after
				else{
					this.Prev();
					this.InsertAfter(data);
				}
			}
		}
	}

	// inserts an item after the current element
	// the new element becomes the current
	// this should not be possible for a full list
	public void InsertAfter(Type data){
		if (!this.IsFull()){
            // create the new node
			Node<Type> n = new Node<Type>();
			n.setData(data);

			// if the list is empty, this new node will be the only
            // one in the list, making it the head, tail, and current
			if (this.IsEmpty())
				this.head = this.tail = this.curr = n;
			else{
				// if we're at the end of the list, just append it
                // no shuffling of links around needed
				if (this.curr == this.tail){
					this.curr.setLink(n);
					this.curr = this.tail = n;
				}
				// otherwise, change the links to insert the node
                // ensure that the new node gets the next entry's link
                // then set the current link to point to the new node
                // then, set your current pointer to the new node
				else{
					n.setLink(this.curr.getLink());
					this.curr.setLink(n);
					this.curr = n;
				}
			}
            // no matter how or where we insert this new node, the list grows by one
			this.num_items++;
		}
	}

	// removes the current element (collapsing the list)
	// this should not be possible for an empty list
	public void Remove(){
		if (!this.IsEmpty()){
			// if we're at the beginning of the list, we essentially just 'forget' the old
            // head / current and set our head / current to the second entry in the list (curr.getLink();)
			if (this.curr == this.head){
				this.head = this.curr = this.curr.getLink();

				// if the only node in the list was the head, and we just removed it 
				if (this.head == null)
					this.tail = null;
			}
			// otherwise, go back a node and reroute around the node to be removed
			else{
				this.Prev();
				// essentially set the current link to the next node's link, essentially 'forgetting' the node in-between
				this.curr.setLink(this.curr.getLink().getLink());
				// if the new current no longer has a next node, it must be the tail (i.e. we removed the tail)
				if (this.curr.getLink() == null)
					this.tail = this.curr;
				this.Next(); // we can use this even if we are at the tail (just won't do anything, so it's okay) 
			}
            // and in all cases, SOMETHING was removed, therefore we must decrease our length by one 
			this.num_items--;
		}
	}

	// replaces the value of the current element with the specified value
	// this should not be possible for an empty list, as nothing exists to be replaced
	public void Replace(Type data){
		if (!this.IsEmpty())
			this.curr.setData(data);
	}

	// returns if the list is empty
    // essentially just a check to see if the head contains a link or not 
	public boolean IsEmpty(){
		return (this.head == null);
	}

	// returns if the list is full, i.e. our length has reached the max allowed size
	public boolean IsFull(){
		return (this.num_items == MAX_SIZE);
	}

	// returns if two lists are equal (by value)
	public boolean Equals(List<Type> l){
		// if they aren't the same size, they def4-initely are not equal
		if (this.num_items != l.num_items)
			return false;
        
        // otherwise, we will iterate through each list, comparing entires
		Node<Type> p = this.head;
		Node<Type> q = l.head;

		while (p != null){
			// if any pair of elements differ, the lists are not equal
			if (p.getData() != q.getData())
				return false;
            // move to the next node
			p = p.getLink();
			q = q.getLink();
		}
        // otherwise, if we get all the way to the end of both lists (i.e. null)
        // that means that each of their nodes are equivalent
		return true;
	}

	// returns the concatenation of two lists
	// l should not be modified
	// l should be concatenated to the end of *this
	// the returned list should not exceed MAX_SIZE elements
	// the last element of the new list is the current
	public <Type> List<Type> Add(List<Type> l){
		// creates a new list with the exact same data as the current list this was called upon
		List<Type> t = new List<Type>(this);
        // make a copy of the head node of the list we are appending for iterative use
		Node<Type> n = l.head;

		// iterate through the second list and copy each element to the new list,
        // with n being the iterable variable for each node within the list being appended
        // and t being the new combined list
        // Note: may not append all items if two would exceed MAX_SIZE
        //       in that case, it adds up until it reaches MAX_SIZE then terminates
		while (n != null && !t.IsFull()){
			t.InsertAfter(n.getData());
			n = n.getLink();
		}
		return t;
	}

	// returns a string representation of the entire list (e.g., 1 2 3 4 5)
	// the string "NULL" should be returned for an empty list
	public String toString(){
		// "NULL" if the list is empty
		if (this.head == null)
			return "NULL";
		else{
			Node<Type> n = this.head;
			String s = "";

			// otherwise iterate through the list and display each element separated by a space
			while (n != null){
				s += n.getData() + " ";
				n = n.getLink();
			}

			return s;
		}
	}
}
