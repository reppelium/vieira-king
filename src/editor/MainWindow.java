package editor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import errors.EditorException;
import lexicon.LexiconValidator;
import model.Token;
import semantic.SemanticValidator;
import syntactic.SyntaticValidator;

public class MainWindow {

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
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
		frame.setTitle("Vieira King Compilador 1.0");
		frame.setBounds(100, 100, 842, 807);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		final LineNr nr = new LineNr();


		frame.getContentPane().add(nr.scrollPane, BorderLayout.CENTER);

		frame.getContentPane().add(nr, BorderLayout.WEST);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(new BorderLayout());
		
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		
		JTextPane textPane = new JTextPane();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		int width = screenSize.width * 18 / 20;
		panel_2.setLayout(new BorderLayout(0, 0));

		// set the jframe height and width
		textPane.setPreferredSize(new Dimension(width, 200));
		
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setMinimumSize(new Dimension(400, 400));
		tabbedPane.addTab("Console", textPane);
		panel_2.add(tabbedPane, BorderLayout.CENTER);
		
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
		panel.setLayout(new BorderLayout(0, 0));
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"Index", "Symbol"
			}
		) {
			boolean[] columnEditables = new boolean[] {
					false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_2.getColumnModel().getColumn(0).setResizable(false);
		table_2.getColumnModel().getColumn(1).setResizable(false);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane(table_1);
		panel.add(scrollPane, BorderLayout.SOUTH);
		
		JScrollPane scrollPane2 = new JScrollPane(table_2);
		panel.add(scrollPane2, BorderLayout.NORTH);
		
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
				textPane.setText("");
				
			}
		});
		toolBar.add(btnNovo);
	
		JButton btnOpen = new JButton("Abrir");
		btnOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Path filePath = selectFile(); 
				
				try {
					String fileText = getTextByFile(filePath);
					nr.pane.setText(fileText);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		toolBar.add(btnOpen);
		
		JButton btnSave = new JButton("Salvar");
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				nr.pane.getText();
				saveFile(nr.pane.getText());
				
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
				
				table_2.setModel(new DefaultTableModel(
						new Object[][] {
							
						},
						new String[] {
							"Index", "Symbol"
						}
					) {
						boolean[] columnEditables = new boolean[] {
								false, false
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
					Stack<Token> saved_stack2 = new Stack<Token>();
					
					while(!genereted_tokens.empty()) {
						Token t = genereted_tokens.pop();
						aux_stack.add(t);
						saved_stack.add(t);
						saved_stack2.add(0, t);
					}
					
					
					textPane.setText("Processo Lexico realizado com sucesso\nTotal de " + aux_stack.size() + " tokens.");
					textPane.setForeground(Color.blue);
					Font font = new Font("Serif", Font.BOLD, 20);
					textPane.setFont(font);
					DefaultTableModel model = (DefaultTableModel) table_1.getModel();
					while(!aux_stack.empty()) {
						Token aux = aux_stack.pop();
						if(aux != null) {
							System.out.println("st.add(new Token("+aux.getIndex()+", \""+aux.getSymbol()+"\", "+aux.getLine()+"));");
							model.addRow(new Object[]{aux.getLine(), aux.getIndex(), aux.getSymbol()});
						}
						else {
							System.out.println("ERRO");
						}
					}
					SyntaticValidator sv = new SyntaticValidator(saved_stack);

					new Thread() {
						
						public void run() {
							
							while(!sv.getEnd()) {
								try {
									sv.doStep();
									drawToken(sv.getTokens());
									drawTerminal(sv.getTerminals());
									Thread.sleep(20);
								} catch (EditorException e) {
									textPane.setText(e.getMessage());
									textPane.setForeground(Color.red);
									Font font = new Font("Serif", Font.BOLD, 20);
									textPane.setFont(font);
									break;
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							
							SemanticValidator seman = new SemanticValidator(saved_stack2);
							try {
								seman.validate();
							} catch (EditorException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						
					}.start();
					
				} catch (EditorException e) {
					e.getLineNumber();
					textPane.setText(e.getMessage());
					textPane.setForeground(Color.red);
					Font font = new Font("Serif", Font.BOLD, 20);
					textPane.setFont(font);
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
	
	public void saveFile(String fileText) {
		JFileChooser chooser = new JFileChooser();
		 if (chooser.showSaveDialog(this.frame) == JFileChooser.APPROVE_OPTION) {
          File file = chooser.getSelectedFile();
          String path = file.getPath();
    
          try (PrintWriter out = new PrintWriter(path)) {
  		    out.println(fileText);
  		  } catch (FileNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		  }
        }
	}
	
	public Path selectFile() {
        JFileChooser chooser = new JFileChooser();


        if (chooser.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            file.toPath();
            
            return file.toPath();
        }else {
        	return null;
        }
		
        
        
    }

	private void drawTerminal(Stack<Token> terminals) {
		
		
		table_2.setModel(new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
					"Index", "Symbol"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		DefaultTableModel model = (DefaultTableModel) table_2.getModel();
		while(!terminals.empty()) {
			Token aux = terminals.pop();
			if(aux != null) {
				model.addRow(new Object[]{aux.getIndex(), aux.getSymbol()});
			}
			else {
				System.out.println("ERRO");
			}
		}
	}

	private void drawToken(Stack<Token> tokens) {
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
		DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		while(!tokens.empty()) {
			Token aux = tokens.pop();
			if(aux != null) {
				model.addRow(new Object[]{aux.getLine(), aux.getIndex(), aux.getSymbol()});
			}
			else {
				System.out.println("ERRO");
			}
		}
		
	}
	public String getTextByFile(Path path) throws IOException {
		
		String fileText = new String(Files.readAllBytes(path)); 
		
		return fileText;
	}
}
