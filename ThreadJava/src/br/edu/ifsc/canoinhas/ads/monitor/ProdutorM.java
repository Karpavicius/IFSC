package br.edu.ifsc.canoinhas.ads.monitor;

public class ProdutorM extends Thread {
	
	private RecursoM recurso;
	private int idProdutor;
	private int qteProduzir;

	public ProdutorM(int idProdutor, RecursoM recurso, int qteProduzir) {
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