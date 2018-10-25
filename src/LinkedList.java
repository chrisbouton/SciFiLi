/**
 * Andrew Hall
 * 9/26/18
 *
 * Linked list Class - has a head node and can ...
 */

public class LinkedList<Type> {
    public static final int MAX_SIZE = 50;

    private Node<Type> headNode;
    private Node<Type> tailNode;
    public Node<Type> currentNode;
    private int position;
    private int num_items;


    public LinkedList(){
        this.headNode = this.tailNode = this.currentNode = null;
        this.num_items = 0;
        this.position = -1;
    }

    public LinkedList(LinkedList<Type> l){

        Node<Type> n = l.headNode;
        this.headNode = this.tailNode = this.currentNode = null;
        this.num_items = 0;
        this.position = 0;
        while (n != null)
        {
            this.InsertAfter(n.getData());
            n = n.getLink();
        }
    }
    // navigates to the beginning of the list
    public void First()
    {
        this.currentNode = headNode;
        this.position = 0;
    }

    // navigates to the end of the list
    // the end of the list is at the last valid item in the list
    public void Last()
    {
        while(currentNode.getLink() != null) {
            currentNode = currentNode.getLink();
        }
    }

    // navigates to the specified element (0-index)
    // this should not be possible for an empty list
    // this should not be possible for invalid positions
    public void SetPos(int pos)
    {
        currentNode = headNode;
        for(int i = 0; i < pos; i++) {
            Next();
        }
    }

    // navigates to the previous element
    // this should not be possible for an empty list
    // there should be no wrap-around
    public void Prev()
    {
        if(headNode.getLink() != null) {
            int prevPos = GetPos() - 1;
            SetPos(prevPos);
        }
    }


    // navigates to the next element
    // this should not be possible for an empty list
    // there should be no wrap-around
    public void Next(){
        if(num_items != 0) {
            currentNode = currentNode.getLink();
        }
    }

    // returns the location of the current element (or -1)
    public int GetPos(){
        int count = 0;
        Node<Type> look = headNode;
        while(look != currentNode) {
            look = look.getLink();
            count++;
        }
        return count;
    }

    // returns the value of the current element (or null)
    public Type GetValue()
    {
        if(num_items == 0){
            System.out.println("empty list");
            return null;
        }
        else{
            //System.out.println("ret Data");
            Type data = currentNode.getData();
            //System.out.println(data+"*");
            return data;
            /*if(currentNode != null){
                System.out.println("ret Data");
                return currentNode.getData();
            }
            else {
                System.out.println("curr empty");
                return null;
            }*/
        }
    }

    // returns the size of the list
    // size does not imply capacity
    public int GetSize()
    {
        return num_items;
    }

    // inserts an item before the current element
    // the new element becomes the current
    // this should not be possible for a full list
    public void InsertBefore(Type data)
    {
        if(num_items > 1){
            Prev();
        }
        InsertAfter(data);
    }

    // inserts an item after the current element
    // the new element becomes the current
    // this should not be possible for a full list
    // help from Kevin Doyon and
    public void InsertAfter(Type data)
    {
        //list not full
        if(num_items < MAX_SIZE) {
            //data not empty
            if (data != null) {
                //first node
                if (num_items == 0) {
                    headNode = new Node<>();
                    tailNode = new Node<>();
                    headNode.setData(data);
                    headNode.setLink(tailNode);
                    num_items++;
                    currentNode = headNode;
                }
                //not first node
                else {
                    if(currentNode == tailNode){
                        Prev();
                        Node<Type> newNode = new Node<>();
                        newNode.setData(data);
                        newNode.setLink(currentNode.getLink());
                        currentNode.setLink(newNode);
                        num_items++;
                        //First();
                        currentNode = newNode;
                    }
                    else {
                        Node<Type> newNode = new Node<>();
                        newNode.setData(data);
                        newNode.setLink(currentNode.getLink());
                        currentNode.setLink(newNode);
                        num_items++;
                        //First();
                        currentNode = newNode;
                    }
                }
            }
            else {
                System.out.println("Data is null");
            }
        }
        else{
            System.out.println("list full");
        }
    }

    // removes the current element
    // this should not be possible for an empty list
    public void Remove()
    {
        if(num_items != 0) {
            if(currentNode != headNode) {
                Node<Type> hold = currentNode.getLink();
                Prev();
                currentNode.setLink(hold);
                num_items--;
            }
            else{
                First();
                headNode = currentNode.getLink();
                First();
            }
        }
    }

    // replaces the value of the current element with the specified value
    // this should not be possible for an empty list
    public void Replace(Type data)
    {
        currentNode.setData(data);
    }

    // returns if the list is empty
    public boolean IsEmpty()
    {
        return this.num_items == 0;
    }

    // returns if the list is full
    public boolean IsFull()
    {
        return GetSize() == MAX_SIZE;
    }

    public boolean hasNext(){
        return currentNode.getLink() != null;
    }

    // returns if two lists are equal (by value)
    public boolean Equals(LinkedList<Type> l)
    {
        First();
        l.First();
        boolean same = true;
        Type myData = currentNode.getData();
        Type lData = l.currentNode.getData();
        while(currentNode.getLink() != null && same) {
            if(myData != lData) {
                same = false;
            }
        }
        return same;
    }

    // returns the concatenation of two lists
    // l should not be modified
    // l should be concatenated to the end of *this
    // the returned list should not exceed MAX_SIZE elements
    // the last element of the new list is the current
    public LinkedList<Type> Add(LinkedList<Type> l)
    {
        LinkedList<Type> newList = new LinkedList<>();
        First();
        //copy this list to new list
        while(currentNode.getLink() != null){
            newList.InsertAfter(currentNode.getData());
            Next();
        }
        l.First();
        newList.Last();
        while(l.currentNode.getLink() != null){
            newList.InsertAfter(l.currentNode.getData());
            l.Next();
        }
        return newList;
    }

    // returns a string representation of the entire list (e.g., 1 2 3 4 5)
    // the string "NULL" should be returned for an empty list
    // help from Kevin Doyon
    public String toString()
    {
        if(num_items == 0)return null;

        StringBuilder listReturn = new StringBuilder();
        Node curr = headNode;
        while(curr != null){
            if(curr != tailNode){
                listReturn.append(curr.getData()).append(" ");
            }
            curr = curr.getLink();
        }
        return listReturn.toString();
    }

}

