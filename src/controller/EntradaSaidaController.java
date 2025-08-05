package controller;

import view.TelaInicial;

public class EntradaSaidaController {
	
	public static void chkEntradaSelected() {
		TelaInicial.chckbxSaida.setSelected(false);
		TelaInicial.chckbxEntrada.setSelected(true);
	}
	
	public static void chkSaidaSelected() {
		TelaInicial.chckbxEntrada.setSelected(false);
		TelaInicial.chckbxSaida.setSelected(true);
	}
	
}
