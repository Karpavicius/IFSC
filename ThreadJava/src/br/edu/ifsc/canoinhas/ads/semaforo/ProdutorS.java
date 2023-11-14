package br.edu.ifsc.canoinhas.ads.semaforo;

public class ProdutorS extends Thread {
	
	private RecursoS recurso;
	private int idProdutor;
	private int qteProduzir;

	public ProdutorS(int idProdutor, RecursoS recurso, int qteProduzir) {
		this.recurso = recurso;
		this.idProdutor = idProdutor;
		this.qteProduzir = qteProduzir;
	}

	public void run() {
		for (int i = 0; i < qteProduzir; i++) {
			recurso.set(idProdutor, i);
		}
		System.out.println("Produtor #" + idProdutor + " concluido!");
	}
}
