import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.border.BevelBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Label;

public class Frame1 {

	private JFrame frame;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JButton solveButton;
	private JCheckBox chckbxNewCheckBox;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel panel_11;
	private JPanel panel_12;
	private JPanel panel_13;
	private JPanel panel_14;
	private JPanel panel_15;
	private JPanel panel_16;
	private JPanel panel_17;
	private JPanel panel_18;
	private JPanel panel_19;
	private JPanel panel_20;
	private JPanel panel_21;
	private JPanel panel_22;
	private JPanel panel_23;
	private JPanel panel_24;
	private JPanel panel_25;
	private JPanel panel_26;
	private JPanel panel_27;
	private JPanel panel_28;
	private JTextPane textBoxMat;
	private JTextPane textBoxMat_1;
	private JTextPane textBoxMat_2;
	private JTextPane textBoxMat_3;
	private JTextPane textBoxMat_4;
	private JTextPane textBoxMat_5;
	private JTextPane textBoxMat_6;
	private JTextPane textBoxMat_7;
	private JTextPane textBoxMat_8;
	private JTextPane textBoxMat_9;
	private JTextPane textBoxMat_10;
	private JTextPane textBoxMat_11;
	private JTextPane textBoxMat_12;
	private JTextPane textBoxMat_13;
	private JTextPane textBoxMat_14;
	private JTextPane textBoxMat_15;
	
	private puzzle15 puzzleResult;
	private Label label;
	private JTextField timeTextField;
	private JLabel lblarisedNode;
	private JTextField nodeTextField;
	private JLabel lblSec;
	private JTextField kurangITextField;
	private JTextField kurangXTextField;
	private boolean fileError;
	
	// Special function for visual center align
	public void setToCenter(JTextPane tbm) {
		StyledDocument th1 = tbm.getStyledDocument();
		SimpleAttributeSet centerN1 = new SimpleAttributeSet();
		StyleConstants.setAlignment(centerN1, StyleConstants.ALIGN_CENTER);
		th1.setParagraphAttributes(0, th1.getLength(), centerN1, false);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		frame = new JFrame("15-Puzzle Solver");
		frame.setBounds(100, 100, 750, 455);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		Image icon = Toolkit.getDefaultToolkit().getImage("puzzleicon.png");
		frame.setIconImage(icon);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 315, 413);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("File Name");
		lblNewLabel.setBounds(41, 119, 62, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(113, 116, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblSolveTime = new JLabel("Solve Duration");
		lblSolveTime.setBounds(41, 351, 107, 14);
		panel.add(lblSolveTime);
		
		timeTextField = new JTextField();
		timeTextField.setEditable(false);
		timeTextField.setColumns(10);
		timeTextField.setBounds(30, 365, 107, 20);
		panel.add(timeTextField);
		
		lblarisedNode = new JLabel("Arised Node");
		lblarisedNode.setBounds(212, 351, 80, 14);
		panel.add(lblarisedNode);
		
		nodeTextField = new JTextField();
		nodeTextField.setEditable(false);
		nodeTextField.setColumns(10);
		nodeTextField.setBounds(212, 365, 68, 20);
		panel.add(nodeTextField);
		
		JButton nextButton = new JButton("Next");
		JButton prevButton = new JButton("Prev");
		nextButton.setEnabled(false);
		prevButton.setEnabled(false);
		
		nextButton.setBounds(191, 186, 89, 23);
		panel.add(nextButton);
		prevButton.setBounds(30, 186, 89, 23);
		panel.add(prevButton);
		
		JLabel kurangILabel = new JLabel("KURANG(i)");
		kurangILabel.setBounds(106, 235, 63, 14);
		panel.add(kurangILabel);
		
		kurangITextField = new JTextField();
		kurangITextField.setEditable(false);
		kurangITextField.setBounds(174, 232, 86, 20);
		panel.add(kurangITextField);
		kurangITextField.setColumns(10);
		
		JLabel sumKurangX = new JLabel("Sum(KURANG(i)) + X");
		sumKurangX.setBounds(51, 259, 118, 14);
		panel.add(sumKurangX);
		
		kurangXTextField = new JTextField();
		kurangXTextField.setEditable(false);
		kurangXTextField.setBounds(174, 256, 86, 20);
		panel.add(kurangXTextField);
		kurangXTextField.setColumns(10);
		
		/*--- Main Feature of the GUI ---*/
		JButton insertButton = new JButton("Insert");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// File process & panelMatrix initialized
				String fileName = textField.getText();
				boolean usingFile = chckbxNewCheckBox.isSelected();
				fileError = false;
				try {
					puzzleResult = new puzzle15(usingFile, fileName);
				}
				catch(Exception e2){
					JOptionPane.showMessageDialog(null, "File not found / error");
					fileError = true;
				}
				JTextPane[] panelMatrix =  {textBoxMat,textBoxMat_1,textBoxMat_2,textBoxMat_3,
						textBoxMat_4,textBoxMat_5,textBoxMat_6,textBoxMat_7,
						textBoxMat_8,textBoxMat_9,textBoxMat_10,textBoxMat_11,
						textBoxMat_12,textBoxMat_13,textBoxMat_14,textBoxMat_15};
				
				// Visual Matrix Cleaner
				int f = 0;
        		for (int i = 0; i < 4; i++) {
	    			for (int j = 0; j < 4; j++) {
	    				panelMatrix[f].setText(" ");
	    				f++;
	    			}
	    		}
				
				int g = 0;
	    		for (int i = 0; i < 4; i++) {
	    			for (int j = 0; j < 4; j++) {
	    				panelMatrix[g].setText(Integer.toString(puzzleResult.getPuzzle()[i][j]));
	    				g++;
	    			}
	    		}
	    		solveButton.setEnabled(true);
			}
		});
		insertButton.setBounds(209, 115, 71, 23);
		panel.add(insertButton);
		
		
		solveButton = new JButton("Solve");
		solveButton.setBounds(209, 143, 71, 23);
		solveButton.setEnabled(false);
		solveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Prev Next button initialized
				nextButton.setEnabled(true);
				prevButton.setEnabled(true);
				
				JTextPane[] panelMatrix =  {textBoxMat,textBoxMat_1,textBoxMat_2,textBoxMat_3,
						textBoxMat_4,textBoxMat_5,textBoxMat_6,textBoxMat_7,
						textBoxMat_8,textBoxMat_9,textBoxMat_10,textBoxMat_11,
						textBoxMat_12,textBoxMat_13,textBoxMat_14,textBoxMat_15};
				
				int[] solution;
				int solutionlen;
	    		
	    		// isReachable check
	    		boolean reachable = puzzleResult.isReachable(puzzleResult.getPuzzle());
	    		kurangITextField.setText(Integer.toString(puzzleResult.kurangI));
	    		kurangXTextField.setText(Integer.toString(puzzleResult.X + puzzleResult.kurangI));
	    		
		        if (reachable){
		        	// Disable execute button
					solveButton.setEnabled(false);
					
		        	// Solve the puzzle
		        	puzzleResult = puzzleResult.solve();
		        	
		        	// Put arised node and time to screen
		        	int arisedNodeCount = puzzleResult.possiblePath.size() - 1;
		        	double time = puzzleResult.time;
		        	nodeTextField.setText(Integer.toString(arisedNodeCount));
		        	timeTextField.setText(Double.toString(time));
		    		
		    		// Transfer solutionpath value to solution
		    		// Functionalizing prev & next
		        	solutionlen= puzzleResult.solutionpath.size();
		    		solution = new int[solutionlen];
		    		System.out.println("Solution Length : " + solutionlen);
		    		for (int q = 0; q < solutionlen; q++) {
		    			solution[q] = puzzleResult.solutionpath.removeFirst();
		    			
		    		}
//		    		for (int q = 0; q < puzzleResult.possiblePath.size(); q++) {
//		    			System.out.println("==================");
//		    			puzzleResult.displayPuzzle(puzzleResult.possiblePath.get(q).instancepuzzle);
//		    		}
//		    		System.out.print("[");
//		    		for (int q = 0; q < solutionlen-1; q++) {
//		    			System.out.print(solution[q]);
//		    			System.out.print(",");
//		    		}
//		    		System.out.println(solution[solutionlen-1]);
//		    		System.out.print("]\n");
//		    		System.out.println(solutionlen);
		    		
		    		// Next button pressed
	        		nextButton.addActionListener(new ActionListener() {
		    			public void actionPerformed(ActionEvent e) {
		    				// visualidx is in the last idx
		    				if (puzzleResult.visualidx != solutionlen - 1) {
		    					puzzleResult.visualidx++;
		    					int[][] visualizerMat = puzzleResult.possiblePath.get(solution[puzzleResult.visualidx]).instancepuzzle;
		    		    		int n = 0;
		    		    		for (int i = 0; i < 4; i++) {
		    		    			for (int j = 0; j < 4; j++) {
		    		    				panelMatrix[n].setText(Integer.toString(visualizerMat[i][j]));
		    		    				n++;
		    		    			}
		    		    		}
		    				}
		    			}
		    		});
		    		
		    		// Prev button pressed
		    		prevButton.addActionListener(new ActionListener() {
		    			public void actionPerformed(ActionEvent e) {
		    				if (puzzleResult.visualidx != 0) {
		    					puzzleResult.visualidx--;
		    					int[][] visualizerMat = puzzleResult.possiblePath.get(solution[puzzleResult.visualidx]).instancepuzzle;
		    		    		int n = 0;
		    		    		for (int i = 0; i < 4; i++) {
		    		    			for (int j = 0; j < 4; j++) {
		    		    				panelMatrix[n].setText(Integer.toString(visualizerMat[i][j]));
		    		    				n++;
		    		    			}
		    		    		}
	    		    		}
		    			}
		    		});
		    		JOptionPane.showMessageDialog(null, "Puzzle Solved!!\nPlease close and reopen this app to try again.\nThank You :D");
		    		insertButton.setEnabled(false);
		        }
		        else{
		        	if (!fileError) {
			            JOptionPane.showMessageDialog(null, "Unreachable");
		        	}
		        }
			}
		});
		panel.add(solveButton);
		/*--- end of button fiture ---*/
		
		chckbxNewCheckBox = new JCheckBox("Using test file");
		chckbxNewCheckBox.setBounds(98, 143, 112, 23);
		panel.add(chckbxNewCheckBox);
		
		label = new Label("15 - PUZZLE SOLVER");
		label.setFont(new Font("Cambria Math", Font.BOLD, 23));
		label.setBounds(41, 33, 235, 46);
		panel.add(label);
		
		lblSec = new JLabel("sec");
		lblSec.setBounds(141, 368, 36, 14);
		panel.add(lblSec);
		
		panel_1 = new JPanel();
		panel_1.setBounds(318, 0, 95, 95);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(1, 2, 3, 4));
		
		panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		textBoxMat = new JTextPane();
		textBoxMat.setFont(new Font("Tahoma", Font.PLAIN, 56));
		textBoxMat.setEditable(false);
		textBoxMat.setBackground(SystemColor.menu);
		textBoxMat.setBounds(10, 11, 75, 75);
		StyledDocument first = textBoxMat.getStyledDocument();
		SimpleAttributeSet center1 = new SimpleAttributeSet();
		StyleConstants.setAlignment(center1, StyleConstants.ALIGN_CENTER);
		first.setParagraphAttributes(0, first.getLength(), center1, false);
		panel_2.add(textBoxMat);
		
		panel_3 = new JPanel();
		panel_3.setBounds(423, 0, 95, 95);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(new GridLayout(1, 2, 3, 4));
		
		panel_4 = new JPanel();
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3.add(panel_4);
		panel_4.setLayout(null);
		
		textBoxMat_1 = new JTextPane();
		textBoxMat_1.setFont(new Font("Tahoma", Font.PLAIN, 56));
		textBoxMat_1.setEditable(false);
		textBoxMat_1.setBackground(SystemColor.menu);
		textBoxMat_1.setBounds(10, 11, 75, 75);
		setToCenter(textBoxMat_1);
		panel_4.add(textBoxMat_1);
		
		panel_5 = new JPanel();
		panel_5.setBounds(528, 0, 95, 95);
		frame.getContentPane().add(panel_5);
		panel_5.setLayout(new GridLayout(1, 2, 3, 4));
		
		panel_6 = new JPanel();
		panel_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_5.add(panel_6);
		panel_6.setLayout(null);
		
		textBoxMat_2 = new JTextPane();
		textBoxMat_2.setFont(new Font("Tahoma", Font.PLAIN, 56));
		textBoxMat_2.setEditable(false);
		textBoxMat_2.setBackground(SystemColor.menu);
		textBoxMat_2.setBounds(10, 11, 75, 75);
		setToCenter(textBoxMat_2);
		panel_6.add(textBoxMat_2);
		
		panel_7 = new JPanel();
		panel_7.setBounds(634, 0, 95, 95);
		frame.getContentPane().add(panel_7);
		panel_7.setLayout(new GridLayout(1, 2, 3, 4));
		
		panel_8 = new JPanel();
		panel_8.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_7.add(panel_8);
		panel_8.setLayout(null);
		
		textBoxMat_3 = new JTextPane();
		textBoxMat_3.setFont(new Font("Tahoma", Font.PLAIN, 56));
		textBoxMat_3.setEditable(false);
		textBoxMat_3.setBackground(SystemColor.menu);
		textBoxMat_3.setBounds(10, 11, 75, 75);
		setToCenter(textBoxMat_3);
		panel_8.add(textBoxMat_3);
		
		panel_9 = new JPanel();
		panel_9.setBounds(318, 106, 95, 95);
		frame.getContentPane().add(panel_9);
		panel_9.setLayout(new GridLayout(1, 2, 3, 4));
		
		panel_10 = new JPanel();
		panel_10.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_9.add(panel_10);
		panel_10.setLayout(null);
		
		textBoxMat_4 = new JTextPane();
		textBoxMat_4.setFont(new Font("Tahoma", Font.PLAIN, 56));
		textBoxMat_4.setEditable(false);
		textBoxMat_4.setBackground(SystemColor.menu);
		textBoxMat_4.setBounds(10, 11, 75, 75);
		setToCenter(textBoxMat_4);
		panel_10.add(textBoxMat_4);
		
		panel_11 = new JPanel();
		panel_11.setBounds(423, 106, 95, 95);
		frame.getContentPane().add(panel_11);
		panel_11.setLayout(new GridLayout(1, 2, 3, 4));
		
		panel_12 = new JPanel();
		panel_12.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_11.add(panel_12);
		panel_12.setLayout(null);
		
		textBoxMat_5 = new JTextPane();
		textBoxMat_5.setFont(new Font("Tahoma", Font.PLAIN, 56));
		textBoxMat_5.setEditable(false);
		textBoxMat_5.setBackground(SystemColor.menu);
		textBoxMat_5.setBounds(10, 11, 75, 75);
		setToCenter(textBoxMat_5);
		panel_12.add(textBoxMat_5);
		
		panel_13 = new JPanel();
		panel_13.setBounds(528, 106, 95, 95);
		frame.getContentPane().add(panel_13);
		panel_13.setLayout(new GridLayout(1, 2, 3, 4));
		
		panel_14 = new JPanel();
		panel_14.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_13.add(panel_14);
		panel_14.setLayout(null);
		
		textBoxMat_6 = new JTextPane();
		textBoxMat_6.setFont(new Font("Tahoma", Font.PLAIN, 56));
		textBoxMat_6.setEditable(false);
		textBoxMat_6.setBackground(SystemColor.menu);
		textBoxMat_6.setBounds(10, 11, 75, 75);
		setToCenter(textBoxMat_6);
		panel_14.add(textBoxMat_6);
		
		panel_15 = new JPanel();
		panel_15.setBounds(634, 106, 95, 95);
		frame.getContentPane().add(panel_15);
		panel_15.setLayout(new GridLayout(1, 2, 3, 4));
		
		panel_16 = new JPanel();
		panel_16.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_15.add(panel_16);
		panel_16.setLayout(null);
		
		textBoxMat_7 = new JTextPane();
		textBoxMat_7.setFont(new Font("Tahoma", Font.PLAIN, 56));
		textBoxMat_7.setEditable(false);
		textBoxMat_7.setBackground(SystemColor.menu);
		textBoxMat_7.setBounds(10, 11, 75, 75);
		setToCenter(textBoxMat_7);
		panel_16.add(textBoxMat_7);
		
		panel_17 = new JPanel();
		panel_17.setBounds(318, 212, 95, 95);
		frame.getContentPane().add(panel_17);
		panel_17.setLayout(new GridLayout(1, 2, 3, 4));
		
		panel_18 = new JPanel();
		panel_18.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_17.add(panel_18);
		panel_18.setLayout(null);
		
		textBoxMat_8 = new JTextPane();
		textBoxMat_8.setFont(new Font("Tahoma", Font.PLAIN, 56));
		textBoxMat_8.setEditable(false);
		textBoxMat_8.setBackground(SystemColor.menu);
		textBoxMat_8.setBounds(10, 11, 75, 75);
		setToCenter(textBoxMat_8);
		panel_18.add(textBoxMat_8);
		
		panel_19 = new JPanel();
		panel_19.setBounds(423, 212, 95, 95);
		frame.getContentPane().add(panel_19);
		panel_19.setLayout(new GridLayout(1, 2, 3, 4));
		
		panel_20 = new JPanel();
		panel_20.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_19.add(panel_20);
		panel_20.setLayout(null);
		
		textBoxMat_9 = new JTextPane();
		textBoxMat_9.setFont(new Font("Tahoma", Font.PLAIN, 56));
		textBoxMat_9.setEditable(false);
		textBoxMat_9.setBackground(SystemColor.menu);
		textBoxMat_9.setBounds(10, 11, 75, 75);
		setToCenter(textBoxMat_9);
		panel_20.add(textBoxMat_9);
		
		panel_21 = new JPanel();
		panel_21.setBounds(528, 212, 95, 95);
		frame.getContentPane().add(panel_21);
		panel_21.setLayout(new GridLayout(1, 2, 3, 4));
		
		panel_22 = new JPanel();
		panel_22.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_21.add(panel_22);
		panel_22.setLayout(null);
		
		textBoxMat_10 = new JTextPane();
		textBoxMat_10.setFont(new Font("Tahoma", Font.PLAIN, 56));
		textBoxMat_10.setEditable(false);
		textBoxMat_10.setBackground(SystemColor.menu);
		textBoxMat_10.setBounds(10, 11, 75, 75);
		setToCenter(textBoxMat_10);
		panel_22.add(textBoxMat_10);
		
		panel_23 = new JPanel();
		panel_23.setBounds(634, 212, 95, 95);
		frame.getContentPane().add(panel_23);
		panel_23.setLayout(new GridLayout(1, 2, 3, 4));
		
		panel_24 = new JPanel();
		panel_24.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_23.add(panel_24);
		panel_24.setLayout(null);
		
		textBoxMat_11 = new JTextPane();
		textBoxMat_11.setFont(new Font("Tahoma", Font.PLAIN, 56));
		textBoxMat_11.setEditable(false);
		textBoxMat_11.setBackground(SystemColor.menu);
		textBoxMat_11.setBounds(10, 11, 75, 75);
		setToCenter(textBoxMat_11);
		panel_24.add(textBoxMat_11);
		
		panel_25 = new JPanel();
		panel_25.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_25.setBounds(318, 318, 95, 95);
		frame.getContentPane().add(panel_25);
		panel_25.setLayout(null);
		
		textBoxMat_12 = new JTextPane();
		textBoxMat_12.setFont(new Font("Tahoma", Font.PLAIN, 56));
		textBoxMat_12.setEditable(false);
		textBoxMat_12.setBackground(SystemColor.menu);
		textBoxMat_12.setBounds(10, 11, 75, 75);
		setToCenter(textBoxMat_12);
		panel_25.add(textBoxMat_12);
		
		panel_26 = new JPanel();
		panel_26.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_26.setBounds(423, 318, 95, 95);
		frame.getContentPane().add(panel_26);
		panel_26.setLayout(null);
		
		textBoxMat_13 = new JTextPane();
		textBoxMat_13.setFont(new Font("Tahoma", Font.PLAIN, 56));
		textBoxMat_13.setEditable(false);
		textBoxMat_13.setBackground(SystemColor.menu);
		textBoxMat_13.setBounds(10, 11, 75, 75);
		setToCenter(textBoxMat_13);
		panel_26.add(textBoxMat_13);
		
		panel_27 = new JPanel();
		panel_27.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_27.setBounds(528, 318, 95, 95);
		frame.getContentPane().add(panel_27);
		panel_27.setLayout(null);
		
		textBoxMat_14 = new JTextPane();
		textBoxMat_14.setFont(new Font("Tahoma", Font.PLAIN, 56));
		textBoxMat_14.setEditable(false);
		textBoxMat_14.setBackground(SystemColor.menu);
		textBoxMat_14.setBounds(10, 11, 75, 75);
		setToCenter(textBoxMat_14);
		panel_27.add(textBoxMat_14);
		
		panel_28 = new JPanel();
		panel_28.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_28.setBounds(634, 318, 95, 95);
		frame.getContentPane().add(panel_28);
		panel_28.setLayout(null);
		
		textBoxMat_15 = new JTextPane();
		textBoxMat_15.setFont(new Font("Tahoma", Font.PLAIN, 56));
		textBoxMat_15.setEditable(false);
		textBoxMat_15.setBackground(SystemColor.menu);
		textBoxMat_15.setBounds(10, 11, 75, 75);
		setToCenter(textBoxMat_15);
		panel_28.add(textBoxMat_15);
		
	}
}
