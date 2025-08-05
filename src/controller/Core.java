package controller;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import model.NotaXml;

import view.TelaInicial;

public class Core {

	public static int[] core() {

		Set<String> competencias = new HashSet<>();

		String diretorio = TelaInicial.textFieldDiretorio.getText();

		boolean notaEntradaSaida = true;

		int[] retorno = new int[3];

		int sucessos = 0;
		int falhas = 0;

		if (!TelaInicial.chckbxEntrada.isSelected() && TelaInicial.chckbxSaida.isSelected()) {
			notaEntradaSaida = false;
		}

		for (NotaXml nota : ArquivosXml.getArquivos(diretorio)) {
			competencias.add(nota.getData());
			if (TelaInicial.chckbxRenomear.isSelected()) {
				ArquivosXml.renomeadorXml(notaEntradaSaida, nota);
			}
			File pasta = new File(diretorio + File.separator + nota.getData());
			if (!pasta.exists()) {
				pasta.mkdir();
			}
			boolean sucesso = TransferirArquivos.moverArquivos(nota.getPath(),
					Paths.get(diretorio, nota.getData()).resolve(nota.getPath().getFileName()));
			if (sucesso) {
				sucessos++;
			} else {
				falhas++;
			}
		}
		
		retorno[0] = sucessos;
		retorno[1] = falhas;
		retorno[2] = sucessos + falhas;
		return retorno;
	}
}
