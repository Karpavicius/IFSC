package br.edu.ifsc.canoinhas.ads.semaforo;

import java.util.concurrent.Semaphore;

public class RecursoS {

	private int estoque;

	static Semaphore semConsumidor = new Semaphore(0);

	static Semaphore semProdutor = new Semaphore(1);

	public synchronized int get(int idConsumidor) {

		try {
			semConsumidor.acquire();
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}

		System.out.println("Consumidor #" + idConsumidor + " consumiu " + estoque);

		semProdutor.release();

		return estoque;
	}

	public void set(int idProdutor, int qtde) {

		try {
			semProdutor.acquire();
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}

		estoque = qtde;
		System.out.println("Produtor #" + idProdutor + " produziu " + estoque);
		semConsumidor.release();
	}

}