package br.edu.ifsc.canoinhas.ads.monitor;

public class MainMonitor {
	public static void main(String[] args) {
		System.out.println("Produtor/Comsumidor com Monitor");
		RecursoM recurso = new RecursoM();
		
		ProdutorM produtor1 = new ProdutorM(1, recurso, 10);
		ProdutorM produtor2 = new ProdutorM(2, recurso, 10);
		
		ConsumidorM consumidor1 = new ConsumidorM(1, recurso, 10);
		ConsumidorM consumidor2 = new ConsumidorM(2, recurso, 10);

		produtor1.start();
		consumidor1.start();
		produtor2.start();
		consumidor2.start();
	}

}
