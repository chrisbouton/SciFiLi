/****************************************
 * Chris Bouton
 * October 1, 2018
 * Node Class - handles any form of data
 */

// the Node class
class Node<Type> {
    // fields
	private Type data;
	private Node<Type> link;

	// constructor
	public Node(){
        this.data = null;
		this.link = null;
	}

	// accessors and mutators for the data component
	public Type getData(){
		return this.data;
	}

	public void setData(Type data){
		this.data = data;
	}

	// accessors and mutators for the link component
	public Node<Type> getLink(){
		return this.link;
	}

	public void setLink(Node<Type> link){
		this.link = link;
	}
}