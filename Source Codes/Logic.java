import java.util.Random;

//To do all the calculation and logic for the program
public class Logic
{
	Tank aiTank = new Tank(0);
	Tank humanTank = new Tank(99);
	int[] moveInt = new int[18];
	String[] move = new String[18];
	int position = 0;
	int tries = 1;

	//Randomize move sequence for AI (Hisyam)
	void randomMoveAI()
	{
		Random moves = new Random();
		for (int i=0; i<18; i++)
		{
			moveInt[i] = moves.nextInt(8);
			move[i] = translateMove(moveInt[i]);
			aiTank.addMoveSequence(move[i]);
		}
	}

	//To translate the move from int type to String type (Nicholas Tan)
	public String translateMove(int moveInt)
	{
		if(moveInt == 0)
			return "Move Up";
		else if(moveInt == 1)
			return "Move Down";
		else if(moveInt == 2)
			return "Move Left";
		else if(moveInt == 3)
			return "Move Right";
		else if(moveInt == 4)
			return "Shoot Up";
		else if(moveInt == 5)
			return "Shoot Down";
		else if(moveInt == 6)
			return "Shoot Left";
		else if(moveInt == 7)
			return "Shoot Right";
		else
			return "___________";
	}

	//To check whether the action is valid to move or shoot (Hisyam)
	public int[] checkMove(int player)
	{
		if(player == 1)
		{
			move = aiTank.getMoveSequence();
			position = aiTank.getTankPosition();
		}
		else
		{
			move = humanTank.getMoveSequence();
			position = humanTank.getTankPosition();
		}

		for(int i=0; i<18; i++)
		{
			if(move[i].equals("Move Up") && position>9)
				moveInt[i] = 0;
			else if(move[i].equals("Move Down") && position<90)
				moveInt[i] = 1;
			else if(move[i].equals("Move Left") && position%10!=0)
				moveInt[i] = 2;
			else if(move[i].equals("Move Right") && position%10!=9)
				moveInt[i] = 3;
			else if(move[i].equals("Shoot Up") && position>9)
				moveInt[i] = 4;
			else if(move[i].equals("Shoot Down") && position<90)
				moveInt[i] = 5;
			else if(move[i].equals("Shoot Left") && position%10!=0)
				moveInt[i] = 6;
			else if(move[i].equals("Shoot Right") && position%10!=9)
				moveInt[i] = 7;
			else
				moveInt[i] = 8;
		}
		return moveInt;
	}

	//player is the indicator for which is the current player, tankA is the current player, tankB is the opposite player and to check victory for them (Nicholas Tan)
	public int checkVictory(int player)
	{
		int positionA, positionB, pos;
		if(player == 1)
		{
			pos = aiTank.getBangPosition();
			positionA = aiTank.getTankPosition();
			positionB = humanTank.getTankPosition();
		}
		else
		{
			pos = humanTank.getBangPosition();
			positionA = humanTank.getTankPosition();
			positionB = aiTank.getTankPosition();
		}

		if(pos == -1) //If current player did not shoot
		{
			if(positionA == positionB) //If current player bang over opponent
			{
				if(player == 1)
					return 1;
				else
					return 0;
			}
			else
				return -1;
		}
		else
		{
			if(pos == positionB) //If the current player got shoot opponent
				return 1;
			else
				return -1;
		}
	}

	//To return the humanTank moveSequence (Nicholas Tan)
	public String[] getMove()
	{
		return humanTank.getMoveSequence();
	}

	//To return the aiTank moveSequence (Nicholas Tan)
	public String[] getMoveAI()
	{
		return aiTank.getMoveSequence();
	}

	//To return the humanTank prevMoveSequence (Nicholas Tan)
	public String[] getPrevMove()
	{
		return humanTank.getPrevMoveSequence();
	}

	//To add action to humanTank moveSequence (Nicholas Tan)
	public void addMove(String action)
	{
		humanTank.addMoveSequence(action);
	}

	//To add action to humanTank prevMoveSequence (Nicholas Tan)
	public void addPrevMove(String action)
	{
		humanTank.addPrevMoveSequence(action);
	}

	//To add action to aiTank moveSequence (Nicholas Tan)
	public void addMoveAI(String action)
	{
		aiTank.addMoveSequence(action);
	}

	//To return humanTank moveSequenceCounter (Nicholas Tan)
	public int getHumanSequenceCounter()
	{
		return humanTank.getSequenceCounter();
	}

	//To set humanTank moveSequence counter (Nicholas Tan)
	public void setHumanSequenceCounter(int counter)
	{
		humanTank.setSequenceCounter(counter);
	}

	//To set aiTank moveSequence counter (Nicholas Tan)
	public void setAISequenceCounter(int counter)
	{
		aiTank.setSequenceCounter(counter);
	}

	//To return tries (Hisyam)
	public int getTries()
	{
		return tries;
	}

	//To set tries (Hisyam)
	public void setTries(int tries)
	{
		this.tries = tries;
	}

	//To return the bangPosition (Hisyam & Zi Xiang)
	public int getBangLocation(int player)
	{
		if(player == 1)
			return aiTank.getBangPosition();
		else
			return humanTank.getBangPosition();
	}

	//To set the bangPosition (Hisyam & Zi Xiang)
	public void setBangLocation(int move, int player, int position)
	{
		if(move == 4)
		{
			if(player == 1)
				aiTank.setBangPosition(position-10);
			else
				humanTank.setBangPosition(position-10);
		}
		else if(move == 5)
		{
			if(player == 1)
				aiTank.setBangPosition(position+10);
			else
				humanTank.setBangPosition(position+10);
		}
		else if(move == 6)
		{
			if(player == 1)
				aiTank.setBangPosition(position-1);
			else
				humanTank.setBangPosition(position-1);
		}
		else if(move == 7)
		{
			if(player == 1)
				aiTank.setBangPosition(position+1);
			else
				humanTank.setBangPosition(position+1);
		}
		else if(move == 8)
		{
			if(player == 1)
				aiTank.setBangPosition(-1);
			else
				humanTank.setBangPosition(-1);
		}
	}

	//To return the tankPosition (Hisyam & Zi Xiang)
	public int getTankLocation(int player)
	{
		if(player == 1)
			return aiTank.getTankPosition();
		else
			return humanTank.getTankPosition();
	}
	
	//To set the tankPosition (Hisyam & Zi Xiang)
	public void setTankLocation(int move, int player, int position)
	{
		if(move == 0)
		{
			if(player == 1)
				aiTank.setTankPosition(position-10);
			else
				humanTank.setTankPosition(position-10);
		}
		else if(move == 1)
		{
			if(player == 1)
				aiTank.setTankPosition(position+10);
			else
				humanTank.setTankPosition(position+10);
		}
		else if(move == 2)
		{
			if(player == 1)
				aiTank.setTankPosition(position-1);
			else
				humanTank.setTankPosition(position-1);
		}
		else if(move == 3)
		{
			if(player == 1)
				aiTank.setTankPosition(position+1);
			else
				humanTank.setTankPosition(position+1);
		}
	}

	//To reset all of humanTank and aiTank sequence and position (Nicholas Tan)
	public void resetMoves()
	{
		humanTank.resetMoveSequence();
		humanTank.resetPosition(99);
		aiTank.resetPosition(0);
	}

	//To reset all of humanTank and aiTank sequence and position, and rerandomize new move sequence for AI and reset tries (Nicholas Tan)
	public void hardReset()
	{
		humanTank.resetMoveSequence();
		humanTank.resetPrevMoveSequence();
		humanTank.resetPosition(99);
		aiTank.resetPrevMoveSequence();
		aiTank.resetPosition(0);
		aiTank.setSequenceCounter(0);
		randomMoveAI();
		tries = 1;
	}

	//To undo action to humanTank moveSequence (Jia Sheng & Zi Xiang)
	public void undoMove()
	{
		humanTank.undoMoveSequence();
	}
}