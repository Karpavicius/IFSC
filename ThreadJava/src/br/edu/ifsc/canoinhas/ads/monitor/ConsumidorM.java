package br.edu.ifsc.canoinhas.ads.monitor;

public class ConsumidorM extends Thread {
	
	private RecursoM recurso;
	private int idConsumidor;
	private int qtdeConsumir;
	
	public ConsumidorM(int idConsumidor, RecursoM recurso, int qtdeConsumir) {
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