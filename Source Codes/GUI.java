import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.event.ActionListener;

//To show the Graphical User Interface
public class GUI extends JFrame{

	int gameDimension = 10; // 10x10 Playable area for moving tank
	String[] btnNames={"Move Up","Shoot Up","Move Left","Shoot Left","Move Down","Shoot Down","Move Right","Shoot Right"};
	JButton[] btn = new JButton[8]; // Interaction buttons for movement and shooting
	JButton saveBtn = new JButton(); // Save button
	JButton undoBtn = new JButton(); // Undo button
	JButton[] options = new JButton[3]; // Menu buttons
	JLabel[] lbl = new JLabel[gameDimension*gameDimension]; // 10x10 label for showing tank
	JLabel[] prevMoveList = new JLabel[20]; // Previous move number list
	JLabel[] prevMove = new JLabel[20]; // Previous move
	JLabel[] moveList = new JLabel[20]; // Current move number list
	JLabel[] move = new JLabel[20]; // Current move
	JLabel tries = new JLabel("1"); // Number of tries
	JOptionPane optionPane = new JOptionPane(); // Menu Option Pane

	//To initialize the whole GUI (Jia Sheng & Nicolas Raj)
	public GUI()
	{
		super("Robot War");

		// Main Panel
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints(); // Constraint for Main Panel

		// Number of Tries Panel
		JPanel triesPanel = new JPanel(new FlowLayout());
		JLabel triesDesc = new JLabel("Number of tries : ");
		tries.setFont(new Font("Arial",Font.PLAIN,16));
		triesDesc.setFont(new Font("Arial",Font.PLAIN,16));
		triesPanel.add(triesDesc);
		triesPanel.add(tries);
		constraints.gridx = 0;
		constraints.gridy = 0;
		mainPanel.add(triesPanel,constraints);
		// End Code for Creating Number of Tries Panel

		// Save Button Panel
		JPanel saveBox = new JPanel(new FlowLayout());
		undoBtn=new JButton("Undo");
		saveBox.add(undoBtn);
		saveBtn=new JButton("Save Game");
		saveBox.add(saveBtn);
		constraints.gridx = 1;
		constraints.gridy = 0; // Main menu panel top right
		mainPanel.add(saveBox,constraints);
		// End Code for Creating Save Button Pane

		// Game Board Panel
		JPanel gameBoard = new JPanel(new GridBagLayout());
		GridBagConstraints constraints2 = new GridBagConstraints();
		int j=0; // value for vertical spacing of 10x10 grid label
		for (int i=0; i<gameDimension*gameDimension; i++) // add label
		{
			if(i>1 && i%10==0)
				j++;
			constraints2.gridx = i%10; // value for horizontal spacing of 10x10 grid label
			constraints2.gridy = j;
			constraints2.ipadx = 3; // horizontal distance between label
			constraints2.ipady = 3; // vertical distance between label
			lbl[i] = new JLabel();
			gameBoard.add(lbl[i],constraints2);
			lbl[i].setFocusable(false);
		}
		gameBoard.setBackground(Color.black);
		constraints.gridx = 0;
		constraints.gridy = 1; // Main menu panel bottom left
		constraints.ipadx = 10; // Outer horizontal distance between main panel item
		constraints.ipady = 10; // Outer vertical distance between main panel item
		mainPanel.add(gameBoard,constraints);
		// End Code for Creating Game Board Panel

		// Bottom panel
		JPanel btmPanel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints3 = new GridBagConstraints();  // Constraint for bottom panel

		// Menu Panel
		JPanel menuBoard = new JPanel(new GridLayout(4,2));
		for (int i=0; i<8; i++)
		{
			btn[i] = new JButton(btnNames[i]);
			btn[i].setFont(new Font("Arial",Font.PLAIN,14));
			menuBoard.add(btn[i]); // add 8 interaction button to menu panel
		}
		constraints3.gridx = 0;
		constraints3.gridy = 0;
		btmPanel.add(menuBoard,constraints3);
		// End Code for Creating Menu Panel

		// Sequence List
		JPanel seqList = new JPanel(new GridBagLayout());
		GridBagConstraints constraints4 = new GridBagConstraints(); // Constraint for sequence list

		JLabel prevDesc = new JLabel("Previous Move :"); // Show Title of Previous Move
		constraints4.ipady = 10;
		constraints4.gridx = 0;
		constraints4.gridy = 0;
		seqList.add(prevDesc,constraints3);

		JLabel curDesc = new JLabel("Current Move :"); // Show Title of Current Move
		constraints4.ipady = 10;
		constraints4.gridx = 1;
		constraints4.gridy = 0;
		seqList.add(curDesc,constraints4);

		// Previous Move List Panel
		JPanel prevList = new JPanel(new GridBagLayout());
		GridBagConstraints constraints5 = new GridBagConstraints(); // Constraint for previous and current list
			
		for(int i=0; i<18; i++) // create 18 label for the list
		{
			prevMoveList[i] = new JLabel();
			prevMoveList[i].setText((i+1)+". ");
			prevMoveList[i].setFont(new Font("Arial",Font.PLAIN,14));
			constraints5.gridx = 0;
			constraints5.gridy = i+1;
			prevList.add(prevMoveList[i],constraints5);
		}
		for(int i=0; i<18; i++)
		{
			prevMove[i] = new JLabel();
			prevMove[i].setText("___________");
			prevMove[i].setFont(new Font("Arial",Font.PLAIN,14));
			constraints5.gridx = 1;
			constraints5.gridy = i+1;
			prevList.add(prevMove[i],constraints5);
		}

		constraints4.ipadx = 20;
		constraints4.gridx = 0;
		constraints4.gridy = 1;
		seqList.add(prevList,constraints4);
		// End Code for Creating Previous Move List Panel

		// Current Move List Panel
		JPanel curList = new JPanel(new GridBagLayout());
		
		for(int i=0; i<18; i++)
		{
			moveList[i] = new JLabel();
			moveList[i].setText((i+1)+". ");
			moveList[i].setFont(new Font("Arial",Font.PLAIN,14));
			constraints5.gridx = 0;
			constraints5.gridy = i+1;
			curList.add(moveList[i],constraints5);
		}
		for(int i=0; i<18; i++)
		{
			move[i] = new JLabel();
			move[i].setText("___________");
			move[i].setFont(new Font("Arial",Font.PLAIN,14));
			constraints5.gridx = 1;
			constraints5.gridy = i+1;
			curList.add(move[i],constraints5);
		}

		constraints4.ipadx = 20;
		constraints4.gridx = 1;
		constraints4.gridy = 1;
		seqList.add(curList,constraints4);
		// End Code for Creating Current Move List Panel

		constraints3.gridx = 0;
		constraints3.gridy = 1;
		constraints3.ipady = 20;
		constraints3.ipadx = 40;
		btmPanel.add(seqList,constraints3);
		// End Code for Creating Bottom Panel

		constraints.gridx = 1; // 4x4 Main menu panel bottom right
		constraints.gridy = 1;
		mainPanel.add(btmPanel,constraints);
		// End Code for Creating Main Panel

		add(mainPanel); // Add main panel to the constructor
		setSize(900,650); // Set size for window panel
		setMinimumSize(new Dimension(900, 650)); // Set minimum size for window panel
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Set close program when exit is trigger
		setFocusable(true); // Set window focusable
		requestFocusInWindow(); // Focus on this window
		setVisible(true); // Set this window visible

		// Option for main menu
		options[0] = new JButton("New Game");
		options[1] = new JButton("Load Game");
		options[2] = new JButton("Exit Game");
	}

	// Add Action Listener for all buttons (Jia Sheng)
	void addMoveListener(ActionListener moveListener)
	{
		for(int i=0; i<8; i++)
			btn[i].addActionListener(moveListener);
		saveBtn.addActionListener(moveListener);
		undoBtn.addActionListener(moveListener);
		for(int i=0; i<3; i++)
			options[i].addActionListener(moveListener);
	}

	// Set button unable to push when thread is running (Jia Sheng)
	public void setButtonDisable()
	{
		for(int i=0; i<8; i++)
			btn[i].setEnabled(false);
		saveBtn.setEnabled(false);
		undoBtn.setEnabled(false);
	}

	// Initialize the GUI Board (Jia Sheng & Nicolas Raj)
	public void initializeGame(ImageIcon empty, ImageIcon tankUp, ImageIcon tankAIDown)
	{
		for(int i=0; i<gameDimension*gameDimension; i++)
			setPic(empty, i);
		setPic(tankUp, 99);
		setPic(tankAIDown, 0);
	}

	// Set Icon for the label (Nicolas Raj)
	public void setPic(ImageIcon icon, int position)
	{
		lbl[position].setIcon(icon);
	}

	// Display Main Menu Panel (Nicolas Raj)
	public void displayOptionPane()
	{
		optionPane.showOptionDialog(null, "What do you want to do", "Main menu", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	}

	// Close Main Menu Panel (Nicolas Raj)
	public void closeOptionPane()
	{
		optionPane.getRootFrame().dispose();
	}

	// Set number of tries in GUI (Nicolas Raj)
	public void setDisplayTries(String count)
	{
		tries.setText(count);
	}

	// Set Current Move list (Jia Sheng)
	public void setMove(String action, int counter)
	{
		move[counter].setText(action);
	}

	// Set Previous Move list (Jia Sheng)
	public void setPrevMove(String action, int counter)
	{
		prevMove[counter].setText(action);
	}

	// Display Victory panel (Nicolas Raj)
	public void victory(String winner)
	{
		JOptionPane.showMessageDialog(null, "Victory to " + winner + " !");
	}

	// Display Game Over panel (Nicolas Raj)
	public void end()
	{
		JOptionPane.showMessageDialog(null, "Victory to AI! You have reached the limit of 10 round.");
	}
	
	// Initialize GUI Board for multiple tries (Nicolas Raj)
	public void newGame(ImageIcon empty, ImageIcon tankUp, ImageIcon tankAIDown)
	{
		for(int i=0; i<gameDimension*gameDimension; i++)
			setPic(empty, i);
		for(int i=0; i<18; i++)
			move[i].setText("___________");
		setPic(tankUp, 99);
		setPic(tankAIDown, 0);
		for(int i=0; i<8; i++)
			btn[i].setEnabled(true);
		saveBtn.setEnabled(true);
		undoBtn.setEnabled(true);
	}
}