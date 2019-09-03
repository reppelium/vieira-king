package editor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		
		final LineNr nr = new LineNr();


		frame.getContentPane().add(nr.scrollPane, BorderLayout.CENTER);

		frame.getContentPane().add(nr, BorderLayout.WEST);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.RED);
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.EAST);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Id", "Token"
			}
		));
		
		JScrollPane scrollPane = new JScrollPane(table_1);
		panel.add(scrollPane);
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenu mnExecutar = new JMenu("Executar");
		menuBar.add(mnExecutar);
		mnExecutar.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("TA AQ");
				
				String[] FullText = nr.pane.getText().split("\n");
				
				List<String> lines = new ArrayList<String>();

				for (String line : FullText) {
					
					lines.add(line);
						
				}
				
				LexiconValidator lv = new LexiconValidator();
				
				Stack<Token> stk = lv.validate(lines);
				System.out.println(stk.size());
				while(!stk.empty()) {
					Token aux = stk.pop();
					if(aux != null) {
						System.out.println(aux.toString());
					}
					else {
						System.out.println("ERRO");
					}
					
				}
				
			}
		
		});
		
		JMenuItem mntmNovo = new JMenuItem("Novo");
		mnArquivo.add(mntmNovo);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mnArquivo.add(mntmAbrir);
		
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mnArquivo.add(mntmSalvar);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mnArquivo.add(mntmSair);
	}
}
