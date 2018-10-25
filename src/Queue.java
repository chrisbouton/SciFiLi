public class Queue<Type> extends LinkedList
{
	public Queue()
	{
		super();
	}

	public void enqueue(Type data)
	{
		super.Last();
		super.InsertAfter(data);
	}

	public void dequeue()
	{
		super.First();
		super.Remove();
	}

	public Type peek()
	{
		super.First();
		return (Type)super.GetValue();
	}
}
