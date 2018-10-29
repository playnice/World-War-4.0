import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

//To control the input and output of GUI, Logic and SaveLoad
public class Controller
{
	private GUI theGUI;
	private Logic theLogic;
	private SaveLoad theSaveLoad;
	int count = 0;
	ImageIcon bang = new ImageIcon("resources/Bang.png");
	ImageIcon empty = new ImageIcon("resources/Empty.png");
	ImageIcon tankUp = new ImageIcon("resources/Tank_UP.png");
	ImageIcon tankDown = new ImageIcon("resources/Tank_DOWN.png");
	ImageIcon tankLeft = new ImageIcon("resources/Tank_LEFT.png");
	ImageIcon tankRight = new ImageIcon("resources/Tank_RIGHT.png");
	ImageIcon tankAIUp = new ImageIcon("resources/TankAI_UP.png");
	ImageIcon tankAIDown = new ImageIcon("resources/TankAI_DOWN.png");
	ImageIcon tankAILeft = new ImageIcon("resources/TankAI_LEFT.png");
	ImageIcon tankAIRight = new ImageIcon("resources/TankAI_RIGHT.png");

	//To initialize the initial game by randomizing AI move sequence, initialize GUI, add move listener to each button and display option pane (Nicholas Tan)
	public Controller(GUI theGUI, Logic theLogic, SaveLoad theSaveLoad)
	{
		this.theGUI = theGUI;
		this.theLogic = theLogic;
		this.theSaveLoad = theSaveLoad;
		this.theLogic.randomMoveAI();
		this.theGUI.initializeGame(empty, tankUp, tankAIDown);
		this.theGUI.addMoveListener(new MoveListener());
		this.theGUI.displayOptionPane();
	}

	//To listen to the button user clicked
	class MoveListener implements ActionListener
	{
		//To perform what kind of action after user clicked a button (Nicholas Tan)
		public void actionPerformed(ActionEvent e)
		{
			String action = e.getActionCommand();

			//If the user clicked the 8 action button, add the action to moveSequence and update GUI
			if(action == "Move Up" || action == "Move Down" || action == "Move Left" || action == "Move Right" || action == "Shoot Up" || action == "Shoot Down" || action == "Shoot Left" || action == "Shoot Right")
			{
				theLogic.addMove(action);
				theGUI.setMove(action, theLogic.getHumanSequenceCounter()-1);
			}
			else if(action == "Save Game") //If user clicked Save Game button, save prevMoveSequence, moveSequence, AI moveSequence, tries to .sav file
				theSaveLoad.save(theLogic.getPrevMove(), theLogic.getMove(), theLogic.getMoveAI(), theLogic.getTries());
			else if(action == "Undo") //If user clicked Undo button, undo latest move, if no move exist, then do nothing
			{
				if(count>0)
				{
					theLogic.undoMove();
					theGUI.setMove("___________", count-1);
				}
			}
			else if(action == "New Game") //If user clicked New Game button, close the option pane and reset humanTank moveSequence counter
			{
				theGUI.closeOptionPane();
				theLogic.setHumanSequenceCounter(0);
			}
			else if(action == "Load Game") //If user clicked Load Game button, load prevMoveSequence, moveSequence, AI moveSequence and tries from .sav file to individual array
			{
				theLogic.hardReset();
				String prevMove[] = theSaveLoad.loadPrevMove();
				String move[] = theSaveLoad.loadMove();
				String moveAI[] = theSaveLoad.loadMoveAI();
				int tries = theSaveLoad.loadTries();
				int pos = 0;
				for(int i=0; i<18; i++) //Set prev move
				{
					theLogic.addPrevMove(prevMove[i]);
					theGUI.setPrevMove(prevMove[i], i);
				}
				for(int i=0; i<18; i++) //Set current move
				{
					theLogic.addMove(move[i]);
					theGUI.setMove(move[i], i);
				}
				for(int i=0; i<18; i++) //To set the move counter
				{
					if(move[i].equals("___________"))
					{
						pos = i;
						break;
					}
				}
				theLogic.setHumanSequenceCounter(pos);
				theLogic.setAISequenceCounter(0);
				for(int i=0; i<18; i++) //To set AI move
					theLogic.addMoveAI(moveAI[i]);
				theLogic.setTries(tries); //To set tries
				theGUI.setDisplayTries(Integer.toString(tries)); //To update GUI to display tries
				theGUI.closeOptionPane();
			}
			else if(action == "Exit Game") //If user clicked Exit Game button, exit game
				System.exit(0);

			count = theLogic.getHumanSequenceCounter(); //To get the humanTank moveSequence counter
			//If count is 18, meaning the user have already input 18 actions
			if(count == 18)
			{
				theGUI.setButtonDisable(); //To set all buttons to disable to prevent user from clicking when animating the GUI
				//To separate the animating part to Thread in order to animate by using sleep
				Thread thread1 = new Thread()
				{
					//To do the following action when start a thread (Nicholas Tan)
					public void run()
					{
						try
						{
							int victory = 0; //To indicate what outcome
							int player = 1; //To indicate what player, 1 is AI, 2 is human
							int move[] = new int[18]; //To store the outcome of the moveSequence after checking condition
							for(int i=0; i<18; i++)
							{
								player = 1;
								for(int j=0; j<2; j++)
								{
									move = theLogic.checkMove(player);
									if(move[i] != 8)
									{
										//To remove bang picture if player previously shoot
										if(theLogic.getBangLocation(player) != -1)
										{
											theGUI.setPic(empty, theLogic.getBangLocation(player));
											theLogic.setBangLocation(8, player, -1);
										}

										//To set the corresponding picture
										switch(player)
										{
											case 1:	switch(move[i])
													{
														case 0:	theGUI.setPic(tankAIUp, theLogic.getTankLocation(player)-10);
																theGUI.setPic(empty, theLogic.getTankLocation(player));
																break;
														case 1:	theGUI.setPic(tankAIDown, theLogic.getTankLocation(player)+10);
																theGUI.setPic(empty, theLogic.getTankLocation(player));
																break;
														case 2:	theGUI.setPic(tankAILeft, theLogic.getTankLocation(player)-1);
																theGUI.setPic(empty, theLogic.getTankLocation(player));
																break;
														case 3:	theGUI.setPic(tankAIRight, theLogic.getTankLocation(player)+1);
																theGUI.setPic(empty, theLogic.getTankLocation(player));
																break;
														case 4:	theGUI.setPic(tankAIUp, theLogic.getTankLocation(player));
																theGUI.setPic(bang, theLogic.getTankLocation(player)-10);
																break;
														case 5:	theGUI.setPic(tankAIDown, theLogic.getTankLocation(player));
																theGUI.setPic(bang, theLogic.getTankLocation(player)+10);
																break;
														case 6:	theGUI.setPic(tankAILeft, theLogic.getTankLocation(player));
																theGUI.setPic(bang, theLogic.getTankLocation(player)-1);
																break;
														case 7:	theGUI.setPic(tankAIRight, theLogic.getTankLocation(player));
																theGUI.setPic(bang, theLogic.getTankLocation(player)+1);
													}
													break;
											case 2:	switch(move[i])
													{
														case 0:	theGUI.setPic(tankUp, theLogic.getTankLocation(player)-10);
																theGUI.setPic(empty, theLogic.getTankLocation(player));
																break;
														case 1:	theGUI.setPic(tankDown, theLogic.getTankLocation(player)+10);
																theGUI.setPic(empty, theLogic.getTankLocation(player));
																break;
														case 2:	theGUI.setPic(tankLeft, theLogic.getTankLocation(player)-1);
																theGUI.setPic(empty, theLogic.getTankLocation(player));
																break;
														case 3:	theGUI.setPic(tankRight, theLogic.getTankLocation(player)+1);
																theGUI.setPic(empty, theLogic.getTankLocation(player));
																break;
														case 4:	theGUI.setPic(tankUp, theLogic.getTankLocation(player));
																theGUI.setPic(bang, theLogic.getTankLocation(player)-10);
																break;
														case 5:	theGUI.setPic(tankDown, theLogic.getTankLocation(player));
																theGUI.setPic(bang, theLogic.getTankLocation(player)+10);
																break;
														case 6:	theGUI.setPic(tankLeft, theLogic.getTankLocation(player));
																theGUI.setPic(bang, theLogic.getTankLocation(player)-1);
																break;
														case 7:	theGUI.setPic(tankRight, theLogic.getTankLocation(player));
																theGUI.setPic(bang, theLogic.getTankLocation(player)+1);
													}
										}
										//To set tankPosition and bangPosition
										theLogic.setTankLocation(move[i], player, theLogic.getTankLocation(player));
										theLogic.setBangLocation(move[i], player, theLogic.getTankLocation(player));
										//To check what is the outcome
										victory = theLogic.checkVictory(player);

										if(victory != -1)
										{
											i = 18;
											j = 2;
											if(player == 2 && victory == 1)
											{
												theGUI.victory("Human");
												theLogic.setTries(10);
											}
											else
												theGUI.victory("AI");
											break;
										}
									}
									player++;
								}
								Thread.sleep(500);
							}
							theLogic.setTries(theLogic.getTries()+1);
							//To check is the tries 11, if is 11, end the game and ask user to new game or load game or exit game, if not just reset the GUI and moveSequence and allow user to continue play
							if(theLogic.getTries() == 11)
							{
								if(!(player == 2 && victory == 1))
									theGUI.end();
								theLogic.hardReset();
								theGUI.displayOptionPane();
								theGUI.newGame(empty, tankUp, tankAIDown);
								for(int i=0; i<18; i++)
									theGUI.setPrevMove("___________", i);
							}
							else
							{
								if(victory == -1)
									theGUI.victory("AI");
								theLogic.resetMoves();
								theGUI.newGame(empty, tankUp, tankAIDown);
								String prevMove[] = theLogic.getPrevMove();
								for(int i=0; i<18; i++)     //Setting the prev move to the GUI
									theGUI.setPrevMove(prevMove[i], i);
							}
							theGUI.setDisplayTries(Integer.toString(theLogic.getTries()));
						}
						catch(InterruptedException ex)
						{
							Thread.currentThread().interrupt();
						}
					}
				};
				thread1.start(); //To start the thread
			}
		}
	}
}