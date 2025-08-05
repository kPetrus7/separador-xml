package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import controller.Core;
import controller.EntradaSaidaController;

public class TelaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTextField textFieldDiretorio;
	public static JCheckBox chckbxSaida;
	public static JCheckBox chckbxEntrada;
	public static JCheckBox chckbxRenomear;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaInicial() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 184);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 192));
		contentPane.setForeground(new Color(0, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 10, 579, 127);
		setLocationRelativeTo(null);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textFieldDiretorio = new JTextField();
		textFieldDiretorio.setFont(new Font("Arial", Font.PLAIN, 15));
		textFieldDiretorio.setBounds(6, 39, 414, 31);
		panel.add(textFieldDiretorio);
		textFieldDiretorio.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Selecione o caminho até notas:");
		lblNewLabel.setFont(new Font("Calisto MT", Font.BOLD, 23));
		lblNewLabel.setBounds(6, 10, 319, 31);
		panel.add(lblNewLabel);
		
		chckbxRenomear = new JCheckBox("Renomear arquivos");
		chckbxRenomear.setBackground(new Color(181, 181, 219));
		chckbxRenomear.setFont(new Font("Dialog", Font.PLAIN, 17));
		chckbxRenomear.setBounds(237, 82, 183, 31);
		panel.add(chckbxRenomear);
		
		chckbxEntrada = new JCheckBox("Entrada");
		chckbxEntrada.setBackground(new Color(181, 181, 219));
		chckbxEntrada.setFont(new Font("Dialog", Font.PLAIN, 17));
		chckbxEntrada.setBounds(6, 79, 88, 31);
		panel.add(chckbxEntrada);
		chckbxEntrada.setSelected(true);
		chckbxEntrada.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EntradaSaidaController.chkEntradaSelected();
			}
			
		});
		
		chckbxSaida = new JCheckBox("Saída");
		chckbxSaida.setBackground(new Color(181, 181, 219));
		chckbxSaida.setFont(new Font("Dialog", Font.PLAIN, 17));
		chckbxSaida.setBounds(100, 79, 88, 31);
		panel.add(chckbxSaida);
		chckbxSaida.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EntradaSaidaController.chkSaidaSelected();
			}
			
		});
		
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Dialog", Font.PLAIN, 17));
		btnStart.setBounds(430, 82, 135, 31);
		panel.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] relatorio = Core.core();
				JOptionPane.showMessageDialog(
					    null,                          
					    "Sucessos: " + relatorio[0] + " / Erros: " + relatorio[1] + " / Total: " + relatorio[2], 
					    "Fim de processo!",                       
					    JOptionPane.INFORMATION_MESSAGE    
					);
			}
		});
		
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setFont(new Font("Dialog", Font.PLAIN, 17));
		btnProcurar.setBounds(430, 39, 135, 31);
		panel.add(btnProcurar);
		btnProcurar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser seletor = new JFileChooser();
				seletor.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				seletor.setDialogTitle("Selecione o caminho até as notas");
				int resultado = seletor.showOpenDialog(null);
				if (resultado == JFileChooser.APPROVE_OPTION) {
                    File diretorio = seletor.getSelectedFile();
                    textFieldDiretorio.setText(diretorio.getAbsolutePath());
				}
			}
		});
	}
}
