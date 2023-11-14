package br.edu.ifsc.canoinhas;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class FuncionarioController {

	@RequestMapping(value = "*", method = RequestMethod.GET)
	@ResponseBody
	public String getFallback() {
		return "Um endpont válido deve ser especificado.";
	}

//curl -i -X GET http://127.0.0.1:8080/funcionario/todos
	@RequestMapping(value = "/funcionario/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<Integer, Funcionario> todosFuncionarios() {
		return FuncionariosBD.getInstance().todosFunc();
	}

	// curl -i -X GET http://127.0.0.1:8080/funcionario/busca?id=1
	@RequestMapping(value = "/funcionario/busca", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Funcionario buscafuncionario(@RequestParam(value = "id", defaultValue = "0") Integer id) {
		return FuncionariosBD.getInstance().buscaFuncionario(id);
	}

//	curl -i -X GET http://127.0.0.1:8080/funcionario/buscaid/1
	@RequestMapping(value = "/funcionario/buscaid/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Funcionario funcionario(@PathVariable Integer id) {
		return FuncionariosBD.getInstance().buscaFuncionario(id);
	}

//	curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"id":"3", "nome":"ads", "idade":"3"}' http://localhost:8080/funcionario/adiciona	
	@RequestMapping(value = "/funcionario/adiciona", method = RequestMethod.POST, // MESMA IDEIA PARA O PUT
			produces = "application/json", consumes = "application/json")
	@ResponseBody
	public String adcfuncionario(@RequestBody String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Funcionario f = objectMapper.readValue(json, Funcionario.class);
			FuncionariosBD.getInstance().adcFuncionario(f.getId(), f);
			return "Sucesso";
		} catch (JsonParseException e) {
			e.printStackTrace();
			return "Erro";
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return "Erro";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

//	curl -i -X DELETE http://127.0.0.1:8080/funcionario/apaga?id=1
	@RequestMapping(value = "/funcionario/apaga", method = RequestMethod.DELETE)
	@ResponseBody
	public String apagafuncionario(@RequestParam(value = "id", defaultValue = "0") Integer id) {
		FuncionariosBD.getInstance().delFuncionario(id);
		return "Sucesso";
	}

}
