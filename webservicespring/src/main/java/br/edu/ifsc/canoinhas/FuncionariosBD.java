package br.edu.ifsc.canoinhas;

import java.util.HashMap;
import java.util.Map;

public class FuncionariosBD {
	
	public static FuncionariosBD instance;
	
	public static synchronized FuncionariosBD getInstance() {
		if (instance == null)
			instance = new FuncionariosBD();
		
		return instance;
	}
	
	private Map<Integer, Funcionario> funcionariosMap = new HashMap<Integer, Funcionario>();
	
	public synchronized void adcFuncionario(Integer  id, Funcionario f) {
		funcionariosMap.put(id, f);
	}

	public synchronized Funcionario buscaFuncionario(Integer  id) {
		return funcionariosMap.get(id);
	}
	
	public synchronized void atlzFuncionario(Integer  id, Funcionario f) {
		funcionariosMap.put(id, f);
	}	
	
	public synchronized void delFuncionario(Integer  id) {
		funcionariosMap.remove(id);
	}
	
	public synchronized Map<Integer, Funcionario> todosFunc(){
		return funcionariosMap;
	}
			
}
