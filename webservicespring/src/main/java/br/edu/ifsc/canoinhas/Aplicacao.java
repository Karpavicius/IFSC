package br.edu.ifsc.canoinhas;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aplicacao {

    public static void main(String[] args) {
    	    	
    	FuncionariosBD.getInstance().adcFuncionario(1, new Funcionario(1, "Luciano", 35));
    	FuncionariosBD.getInstance().adcFuncionario(2, new Funcionario(2, "Barreto", 30));    	
    	
        SpringApplication.run(Aplicacao.class, args);
    }
    
}
