package br.edu.ifsc.canoinhas.ads.monitor;

public class RecursoM {
	 
    private int estoque;
    private boolean disponivel;
 
    public synchronized void set(int idProdutor, int qtde) {
        while (disponivel == true) {
            try {
                System.out.println("Produtor #" + idProdutor + " em espera.");
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        estoque = qtde;
        System.out.println("Produtor #" + idProdutor + " produziu " + estoque);
        disponivel = true;
        notifyAll();
    }
 
    public synchronized int get(int idConsumidor) {
        while (disponivel == false) {
            try {
                System.out.println("Consumidor #" + idConsumidor + " em espera.");
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Consumidor #" + idConsumidor + " consumiu " + estoque);
        disponivel = false;
        notifyAll();
        return estoque;
    }
}
