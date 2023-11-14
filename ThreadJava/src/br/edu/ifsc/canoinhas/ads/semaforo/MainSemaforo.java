package br.edu.ifsc.canoinhas.ads.semaforo;



public class MainSemaforo {
	
	public static void main(String[] args) {
		System.out.println("Produtor/Comsumidor com Semaforo");
		RecursoS recurso = new RecursoS();
		
		ProdutorS produtor1 = new ProdutorS(1, recurso, 10);
		ProdutorS produtor2 = new ProdutorS(2, recurso, 10);
		
		ConsumidorS consumidor1 = new ConsumidorS(1, recurso, 10);
		ConsumidorS consumidor2 = new ConsumidorS(2, recurso, 10);

		produtor1.start();
		consumidor1.start();
		produtor2.start();
		consumidor2.start();
	}

}
