public class Stack<Type> extends LinkedList
{
	public Stack()
	{
		super();
	}

	public void push(Type data)
	{
		super.InsertAfter(data);
	}

	public void pop()
	{
		super.Remove();
	}

	public Type peek()
	{
		return (Type)super.GetValue();
	}
}
