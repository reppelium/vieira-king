package editor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import lexicon.LexiconValidator;
import lexicon.Token;

public class MainWindow {

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private Stack<Token> genereted_tokens;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("/home/romulo/Downloads/icons8-java-eclipse-64.png"));
		frame.setTitle("Vieira King Compilador 1.0");
		frame.setBounds(100, 100, 842, 807);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		final LineNr nr = new LineNr();


		frame.getContentPane().add(nr.scrollPane, BorderLayout.CENTER);

		frame.getContentPane().add(nr, BorderLayout.WEST);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		JTextPane textPane = new JTextPane();
		textPane.setPreferredSize(new Dimension(1600, 200));
		textPane.setMinimumSize(new Dimension(700, 50));
	
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setMinimumSize(new Dimension(400, 400));
		
		tabbedPane.addTab("Console", textPane);
		panel_2.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setVisible(false);
		frame.getContentPane().add(panel, BorderLayout.EAST);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"Line","Index", "Symbol"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(0).setResizable(false);
		table_1.getColumnModel().getColumn(1).setResizable(false);
		table_1.getColumnModel().getColumn(2).setResizable(false);

		JScrollPane scrollPane = new JScrollPane(table_1);
		panel.add(scrollPane);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				panel.setVisible(false);
				nr.pane.setText("");
				
			}
		});
		toolBar.add(btnNovo);
		
		
		JButton btnSave = new JButton("Salvar");
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				nr.pane.getText();
				
				
			}
			
		});
		toolBar.add(btnSave);
		
		
		JButton btnExecute = new JButton("Executar");
		btnExecute.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				table_1.setModel(new DefaultTableModel(
						new Object[][] {
							
						},
						new String[] {
							"Line","Index", "Symbol"
						}
					) {
						boolean[] columnEditables = new boolean[] {
							false, false, false
						};
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					});
				
				// TODO Auto-generated method stub
				panel.setVisible(true);
				String[] FullText = nr.pane.getText().split("\n");
				
				List<String> lines = new ArrayList<String>();

				for (String line : FullText) {
					
					lines.add(line);
						
				}
				
				
				
				LexiconValidator lv = new LexiconValidator();
				
				
				try {
					
					genereted_tokens = lv.validate(lines);
					Stack<Token> aux_stack = new Stack<Token>();
					Stack<Token> saved_stack = new Stack<Token>();
					
					while(!genereted_tokens.empty()) {
						Token t = genereted_tokens.pop();
						aux_stack.add(t);
						saved_stack.add(t);
					}
					
					DefaultTableModel model = (DefaultTableModel) table_1.getModel();
					textPane.setText("Processo Lexico realizado com sucesso\nTotal de " + aux_stack.size() + " tokens.");
					textPane.setForeground(Color.blue);
					Font font = new Font("Serif", Font.BOLD, 20);
					textPane.setFont(font);
					while(!aux_stack.empty()) {
						Token aux = aux_stack.pop();
						if(aux != null) {
						
							model.addRow(new Object[]{aux.getLine(), aux.getIndex(), aux.getSymbol()});
						}
						else {
							System.out.println("ERRO");
						}
					}	
										
				} catch (Exception e) {
					System.out.println(e.getMessage());
					
				}
			}
		});
		toolBar.add(btnExecute);
		
		
	}
	
	public static Stack reverse(Stack<Token> stack){
		Stack<Token> newStack = new Stack();
		while(!stack.empty()) {
			newStack.push( stack.pop());
		}
		return newStack;
	}
}
