//To run the game
public class MVCGame
{
	//To create object of GUI, Logic, SaveLoad and Controller to run the game (Nicholas Tan)
	public static void main(String[] args)
	{
		GUI theGUI = new GUI();
		Logic theLogic = new Logic();
		SaveLoad theSaveLoad = new SaveLoad();
		Controller theController = new Controller(theGUI, theLogic, theSaveLoad);
	}
}