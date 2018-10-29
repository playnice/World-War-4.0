//To store tank information
public class Tank
{
	Sequence moveSequence = new Sequence();
	Sequence prevMoveSequence = new Sequence();
	Position tankPosition = new Position();
	Position bangPosition = new Position();

	//To initialize tankPosition to the value that passed in and bangPosition to -1  (Nicholas Tan)
	public Tank(int pos)
	{
		tankPosition.setPosition(pos);
		bangPosition.setPosition(-1);
	}

	//To return moveSequence (Nicholas Tan)
	public String[] getMoveSequence()
	{
		String[] tempMove = new String[18];
		int i=0;
		Action action;
		for(Iterator iter = moveSequence.getIterator(); iter.hasNext();)
		{
			action = (Action)iter.next();
			tempMove[i] = action.returnAction();
			i++;
		}
		return tempMove;
	}

	//To return prevMoveSequence (Nicholas Tan)
	public String[] getPrevMoveSequence()
	{
		String[] tempMove = new String[18];
		int i=0;
		Action action;
		for(Iterator iter = prevMoveSequence.getIterator(); iter.hasNext();)
		{
			action = (Action)iter.next();
			tempMove[i] = action.returnAction();
			i++;
		}
		return tempMove;
	}

	//To return bangPosition (Hisyam & Zi Xiang)
	public int getBangPosition()
	{
		return bangPosition.getPosition();
	}

	//To return tankPosition (Hisyam & Zi Xiang)
	public int getTankPosition()
	{
		return tankPosition.getPosition();
	}

	//To return moveSequence counter (Nicholas Tan)
	public int getSequenceCounter()
	{
		return moveSequence.getCounter();
	}

	//To add action to moveSequence (Nicholas Tan)
	public void addMoveSequence(String action)
	{
		moveSequence.addAction(action);
	}

	//To add action to prevMoveSequence (Nicholas Tan)
	public void addPrevMoveSequence(String action)
	{
		prevMoveSequence.addAction(action);
	}

	//To set tankPosition (Hisyam & Zi Xiang)
	public void setTankPosition(int position)
	{
		tankPosition.setPosition(position);
	}

	//To set bangPosition (Hisyam & Zi Xiang)
	public void setBangPosition(int position)
	{ 
		bangPosition.setPosition(position);
	}

	//To set moveSequence counter (Nicholas Tan)
	public void setSequenceCounter(int counter)
	{
		moveSequence.setCounter(counter);
	}

	//To reset moveSequence and prevMoveSequence (Nicholas Tan)
	public void resetMoveSequence()
	{
		prevMoveSequence = moveSequence;
		moveSequence = new Sequence();
		setSequenceCounter(0);
	}

	//To reset bangPosition and tankPosition (Nicholas Tan)
	public void resetPosition(int pos)
	{
		setBangPosition(-1);
		setTankPosition(pos);
	}

	//To reset only prevMoveSequence (Nicholas Tan)
	public void resetPrevMoveSequence()
	{
		prevMoveSequence = new Sequence();
		setSequenceCounter(0);
	}

	//To undo action to moveSequence (Jia Sheng & Zi Xiang)
	public void undoMoveSequence()
	{
		moveSequence.deleteAction();
	}
}