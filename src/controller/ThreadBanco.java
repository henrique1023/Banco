package controller;

import java.util.concurrent.Semaphore;

public class ThreadBanco extends Thread {
	private int idThread;
	private Semaphore semaforo;

	public ThreadBanco(int idThread, Semaphore semaforo) {
		this.idThread = idThread;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		calculos();
		try {
			semaforo.acquire();
			transacaoBD();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			calculos();
		}

	}

	private void transacaoBD() {
		System.out.println("A transação " + "#" + idThread + " esta rodando");
		int tempo;
		int esc = (int) (idThread % 3);
		switch (esc) {
		case 0:
			tempo = 1500;
			break;
		case 1:
			tempo = 1000;
		case 2:
			tempo = 1500;
		default:
			break;
		}
	}

	private void calculos() {
		if (idThread % 3 == 1) {
			int tempCalc = (int) ((Math.random() * 201) + 800);
			float tempReal = tempCalc / 100;

			int tempo = tempCalc;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Calculando " + "#" + idThread + " no tempo de " + tempReal);
		} else {
			if (idThread % 3 == 2) {
				int tempCalc = (int) ((Math.random() * 501) + 1000);
				float tempReal = tempCalc / 100;
				int tempo = tempCalc;
				try {
					sleep(tempo);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Calculando " + "#" + idThread + " no tempo de " + tempReal);
			} else {
				if (idThread % 3 == 0) {
					int tempCalc = (int) ((Math.random() * 1001) + 1000);
					float tempReal = tempCalc / 100;

					int tempo = tempCalc;
					try {
						sleep(tempo);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Calculando " + "#" + idThread + " no tempo de " + tempReal);
				}
			}

		}
	}
}
