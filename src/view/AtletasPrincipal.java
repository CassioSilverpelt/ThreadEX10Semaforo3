package view;

import java.util.concurrent.Semaphore;

import controller.Atleta;
import controller.AtletasController;

public class AtletasPrincipal {
	
	public static void main (String[] args) {
		
		Semaphore armas = new Semaphore(5);
		Semaphore fimCiclismo = new Semaphore(1);
		Atleta[] cAtleta = new Atleta[25];
		
		for (int i = 0; i < 25; i++) {
			cAtleta[i] = new Atleta();
			cAtleta[i].atletaID = (i);
			AtletasController atleta = new AtletasController(armas, fimCiclismo, i, cAtleta);
			atleta.start();
		}
		
	}
	
	/*public static Atleta[] ordenaAtleta(Atleta[] cAtleta) {
		int tamanho = cAtleta.length;
		
		for (int i = 0; i < tamanho-1; i++) {
			for (int j = 1; j < tamanho; j++) {
				if (cAtleta[i].pontos < cAtleta[j].pontos) {
					Atleta[] aux = new Atleta[1];
					aux[0].atletaID = cAtleta[i].atletaID;
					aux[0].pontos = cAtleta[i].pontos;
					cAtleta[i].atletaID = cAtleta[j].atletaID;
					cAtleta[i].pontos = cAtleta[j].pontos;
					cAtleta[j].atletaID = aux[j].atletaID;
					cAtleta[j].pontos = aux[j].pontos;
				}
			}
		}
		return cAtleta;
	}
	
	public static void imprimePontos(Atleta[] cAtleta) {
		int tamanho = cAtleta.length;
		for(int i = 0; i < tamanho; i++) {
			System.out.println("O Atleta #" + (cAtleta[i].atletaID+1) + " ficou com " + cAtleta[i].pontos + " pontos.");
		} 
	}*/
	
}
