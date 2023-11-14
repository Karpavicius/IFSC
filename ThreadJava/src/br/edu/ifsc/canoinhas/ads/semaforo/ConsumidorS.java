package br.edu.ifsc.canoinhas.ads.semaforo;

public class ConsumidorS extends Thread {
	
	private RecursoS recurso;
	private int idConsumidor;
	private int qtdeConsumir;

	public ConsumidorS(int idConsumidor, RecursoS recurso, int qtdeConsumir) {
		this.recurso = recurso;
		this.idConsumidor = idConsumidor;
		this.qtdeConsumir = qtdeConsumir;
	}

	public void run() {
		for (int i = 0; i < qtdeConsumir; i++) {
			recurso.get(idConsumidor);
		}
		System.out.println("Consumidor #" + idConsumidor + " concluido!");
	}
}