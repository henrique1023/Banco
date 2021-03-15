package view;

import java.util.concurrent.Semaphore;

import controller.ThreadBanco;

public class Banco {
	public static void main(String[] args) {
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		for(int idThread = 1; idThread <=21; idThread++) {
		Thread tBanco = new ThreadBanco(idThread, semaforo);
		tBanco.start();
		}
	}
}
