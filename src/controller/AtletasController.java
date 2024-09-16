package controller;

import java.util.concurrent.Semaphore;

public class AtletasController extends Thread {
	
	Atleta[] cAtleta;
	int corrida = 3000;
	int ciclismo = 5000;
	int vCorrida;
	int vCiclismo;
	Semaphore armas;
	Semaphore fimCiclismo;
	int iD;
	static int pontosCiclismo = 250;
	static int posicao = 1;
	public static int fimdaCorrida = 0;
	
	public AtletasController (Semaphore armas, Semaphore fimCiclismo, int iD, Atleta[] cAtleta) {
		this.armas = armas;
		this.cAtleta = cAtleta;
		this.iD = iD;
		this.fimCiclismo = fimCiclismo;
	}
	
	@Override
	public void run() {
		corrida();
		disparos();
		ciclismo();
		
		if (fimdaCorrida == 25) {
			try {
				sleep(1000);
				ordenaAtleta(cAtleta);
				imprimePontos(cAtleta);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void ciclismo() {
		System.out.println("Atleta #" + (cAtleta[iD].atletaID+1) + " começou o ciclismo!");
		for (int i = 0; i < corrida; i = i + (int)((Math.random()*11)+30)) {
			try {
				sleep(40);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		try {
			fimCiclismo.acquire();
			sleep(15);
			System.out.println("Atleta# " + (cAtleta[iD].atletaID+1) + " terminou o ciclismo em: " + posicao + "o lugar!");
			cAtleta[iD].pontos += pontosCiclismo;
			posicao++;
			pontosCiclismo = pontosCiclismo - 10;
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		} finally {
			fimdaCorrida++;
			fimCiclismo.release();
		}
		
		
	}

	private void disparos() {
		System.out.println("Atleta #" + (cAtleta[iD].atletaID+1) + " começou os disparos!");
		try {
			armas.acquire();
			for (int i = 0; i < 3; i++) {
				sleep((int) (Math.random()*2501) + 500);
				cAtleta[iD].pontos += (int) (Math.random()*11);
			}
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		} finally {
			System.out.println("Atleta #" + (cAtleta[iD].atletaID+1) + " completou os disparos!");
			armas.release();
		}
		
	}

	private void corrida() {
		System.out.println("Atleta #" + (cAtleta[iD].atletaID+1) + " começou a corrida!");
		for (int i = 0; i < corrida; i = i + (int)((Math.random()*6)+20)) {
			try {
				sleep(30);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Atleta #" + (cAtleta[iD].atletaID+1) + " terminou a corrida!");
		
	}
	
	private Atleta[] ordenaAtleta(Atleta[] cAtleta) {
		int tamanho = cAtleta.length;
		
		for (int i = 0; i < tamanho; i++) {
			for (int j = i+1; j < tamanho; j++) {
				if (cAtleta[i].pontos > cAtleta[j].pontos) {
					Atleta[] aux = new Atleta[1];
					aux[0] = new Atleta();
					aux[0].atletaID = cAtleta[i].atletaID;
					aux[0].pontos = cAtleta[i].pontos;
					cAtleta[i].atletaID = cAtleta[j].atletaID;
					cAtleta[i].pontos = cAtleta[j].pontos;
					cAtleta[j].atletaID = aux[0].atletaID;
					cAtleta[j].pontos = aux[0].pontos;
				}
			}
		}
		return cAtleta;
	}
	
	private void imprimePontos(Atleta[] cAtleta) {
		int tamanho = cAtleta.length;
		for(int i = 0; i < tamanho; i++) {
			System.out.println("O Atleta #" + (cAtleta[i].atletaID+1) + " ficou com " + cAtleta[i].pontos + " pontos.");
		} 
	}
	
}
