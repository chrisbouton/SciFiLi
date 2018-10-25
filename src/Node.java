/**
 * Andrew Hall
 * 9/26/18
 *
 * Node Class - handles all my issues
 */

public class Node<Type>
{

    private Node<Type> link;
    private Type data;

    public Node(){
        link = null;
    }
    // accessor mutator for link
    public void setLink(Node node){
        link = node;
    }
    public Node<Type> getLink(){
        return this.link;
    }
    //accessor mutator for data
    public void setData(Type data){
        this.data = data;
    }
    public  Type getData(){
        return this.data;
    }
}
