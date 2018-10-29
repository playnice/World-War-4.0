import java.io.*;
import java.util.ArrayList;

@SuppressWarnings("unchecked")

//To save and load both the player move sequence and tries
public class SaveLoad{

	//Save file method. (Zi Xiang)
	public void save(String[] prevMove, String[] move, String[] moveAI, int tries)
	{
		try
		{
			//Open a file to write to .sav.
			FileOutputStream savePrevFile = new FileOutputStream("PrevMoveLists.sav");//Last round's moves.
			FileOutputStream saveFile = new FileOutputStream("MoveLists.sav");//Save current moves.
			FileOutputStream saveAIFile = new FileOutputStream("AIMoveLists.sav");//Save AI's moves.
			FileOutputStream triesFile = new FileOutputStream("Tries.sav");//Tries count.
			//Create an ObjectOutputStream to put objects into save file.
			ObjectOutputStream savePrev = new ObjectOutputStream(savePrevFile);
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			ObjectOutputStream saveAI = new ObjectOutputStream(saveAIFile);
			ObjectOutputStream saveTries = new ObjectOutputStream(triesFile);

			//Writing data into respective save files.
			saveTries.writeObject(tries);
			save.writeObject(move);
			saveAI.writeObject(moveAI);
			savePrev.writeObject(prevMove);

			//Close the files after finished saving.
			save.close();
			saveTries.close();
			saveAI.close();
			savePrev.close();
		}
		catch(Exception exc)
		{
			exc.printStackTrace(); //If there was an error, print the info.
		}
	}

	//Load last round's move from save file. (Zi Xiang)
	public String[] loadPrevMove()
	{
		String[] tankMoves = new String[18];
		try
		{
			//Open file to read from, named PrevMoveLists.sav.
			FileInputStream loadFile = new FileInputStream("PrevMoveLists.sav");

			//Create an ObjectInputStream to get objects from PrevMoveLists.sav.
			ObjectInputStream load = new ObjectInputStream(loadFile);

			//Cast the object to String type
			tankMoves = (String[])load.readObject();

			//Close the file.
			load.close(); //This also closes save file.
		}
		catch(Exception exc)
		{
			exc.printStackTrace(); //If there was an error, print the info.
		}
		return tankMoves;
	}

	//Load AI's move from save file. (Zi Xiang)
	public String[] loadMoveAI()
	{
		String[] tankMoves = new String[18];
		try
		{
			//Open file to read from, named AIMoveLists.sav.
			FileInputStream loadFile = new FileInputStream("AIMoveLists.sav");

			//Create an ObjectInputStream to get objects from AIMoveLists.sav.
			ObjectInputStream load = new ObjectInputStream(loadFile);

			//Cast the object to String type
			tankMoves = (String[])load.readObject();

			//Close the file.
			load.close();
		}
		catch(Exception exc)
		{
			exc.printStackTrace(); //If there was an error, print the info.
		}

		return tankMoves;
	}

	//Load player's move from save file. (Zi Xiang)
	public String[] loadMove()
	{
		String[] tankMoves = new String[18];
		try
		{
			//Open file to read from, named MoveLists.sav.
			FileInputStream loadFile = new FileInputStream("MoveLists.sav");

			//Create an ObjectInputStream to get objects from MoveLists.sav.
			ObjectInputStream load = new ObjectInputStream(loadFile);

			//Cast the object to String type
			tankMoves = (String[])load.readObject();

			//Close the file.
			load.close();
		}
		catch(Exception exc)
		{
			exc.printStackTrace(); //If there was an error, print the info.
		}

		return tankMoves;
	}

	//Load the tries count from save file. (Zi Xiang)
	public int loadTries()
	{
		int moveCounter= 0;
		try
		{
			//Open file to read from, named Tries.sav.
			FileInputStream loadFile = new FileInputStream("Tries.sav");

			//Create an ObjectInputStream to get objects from Tries.sav.
			ObjectInputStream load = new ObjectInputStream(loadFile);

			//Cast the object to int type
			moveCounter = (int) load.readObject();

			//Close the file.
			load.close();
		}
		catch(Exception exc)
		{
			exc.printStackTrace(); //If there was an error, print the info.
		}
		return moveCounter;
	}
}
