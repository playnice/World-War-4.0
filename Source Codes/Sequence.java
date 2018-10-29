//To store the tank sequence
public class Sequence implements Container
{
	int counter = 0;
	Action[] actionList = new Action[18];

	//To initialize the actionList as new Action type (Nicholas Tan)
	public Sequence()
	{
		for(int i=0; i<18; i++)
			actionList[i] = new Action();
	}

	@Override     //To override the getIterator from Iterator class (Nicholas Tan)
	public Iterator getIterator()
	{
		return new SequenceIterator();
	}

	//To implement hasNext() and next() function (Nicholas Tan)
	public class SequenceIterator implements Iterator
	{
		int index;

		//To know whether the actionList have next item or not (Nicholas Tan)
		public Boolean hasNext()
		{
			if(index < actionList.length)
				return true;
			else
				return false;
		}

		//To return the next item (Nicholas Tan)
		public Object next()
		{
			if(this.hasNext())
				return actionList[index++];
			else
				return null;
		}
	}

	//To return the actionList (Nicholas Tan)
	public String[] getSequence()
	{
		String[] tempSequence = new String[18];
		for(int i=0; i<18; i++)
			tempSequence[i] = actionList[i].returnAction();
		return tempSequence;
	}

	//To add new action to actionList (Nicholas Tan)
	public void addAction(String action)
	{
		actionList[counter].setAction(action);
		counter++;
	}

	//To return an action from actionList (Nicholas Tan)
	public String getAction(int pos)
	{
		return actionList[pos].returnAction();
	}

	//To set actionList counter (Nicholas Tan)
	public void setCounter(int counter)
	{
		this.counter = counter;
	}

	//To return the counter of actionList (Nicholas Tan)
	public int getCounter()
	{
		return counter;
	}

	//To delete latest action from actionList (Jia Sheng & Zi Xiang)
	public void deleteAction()
	{
		actionList[counter].setAction("___________");
		counter--;
	}
}