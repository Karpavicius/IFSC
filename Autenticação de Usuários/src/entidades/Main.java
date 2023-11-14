package entidades;

import java.util.HashMap;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Map;
import java.security.NoSuchAlgorithmException;

public class Main {

  private static byte[] getSalt() throws NoSuchAlgorithmException { 
    byte[] salt = new byte[16]; //array de bytes
    try{
        SecureRandom sr = SecureRandom.getInstance //gera o sal aleatorio
        ("SHA1PRNG", "SUN"); //pega instancia de securerandom e define o tipo de algoritmo que vai usar (sha-256)
        sr.nextBytes(salt); //transforma a instancia em bytes
    }catch(Exception e){

    }
    return salt; //retorna o sal
    }

	public static void main(String[] args) throws  NoSuchAlgorithmException {
		try {
		Scanner scan = new Scanner(System.in); //cria um scanner

    Map<String, Usuarios> usuarios = new HashMap<String,Usuarios>(); //cria hashmap
		MessageDigest trans = MessageDigest.getInstance("SHA-256"); //pega instancia do tipo sha-256 e armazena em "trans"

		String id;
		String pass;
		String login;
		String senha;
		
		int opc = 0;

		do{

		System.out.println("Digite 1 para cadastrar um novo usuario ou 2 para fazer login: ");
		opc=scan.nextInt();
		
		switch(opc) {
		
		case 1:
			
			System.out.println("Escolha um id para cadastrar: ");
			id=scan.next();
				if(usuarios.getOrDefault(id, null)!=null){ //se for igual a null ele nao foi cadastrado ainda

					System.out.println("Id já ultilizado!"); //compara se ja ta cadastrado
       
					break;

				}else{
					System.out.println("Escolha uma senha: ");
					pass=scan.next();
        
					byte[] sal = getSalt(); //pegando o sal que retorna e amarzena em sal
					trans.update(sal); //atualiza para um sal novo a cada cadastro

					byte[] bytes = trans.digest(pass.getBytes()); //aplicando o algoritimo de gerar o hash

					StringBuilder sb = new StringBuilder(); //transformando os bytes em string de novo
					for(int i=0; i< bytes.length ;i++){
						sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1)); //concatena e devolve uma string
					}
					String generatedPassword = sb.toString(); //armazena em generated
					System.out.println(generatedPassword);//printa o  hash

					Usuarios user = new Usuarios(id, generatedPassword, sal); //cria um novo objeto de usuario
					usuarios.put(id, user); //add no hash, com 2 chaves id e user

				} break;

		case 2: 
			System.out.println("Informe o id cadastrado: ");
			login=scan.next();

			if(usuarios.getOrDefault(login, null)==null){ //se ele nao retornar null, ele nao existe
				System.out.println("Id invalido! Tente Novamente");
			}else{

				System.out.println("Informe a senha cadastrada: ");
				senha=scan.next();

				Usuarios userLogin = usuarios.get(login); //pegando o usuario do hashmap

				trans.update(userLogin.getsal()); //update do sal

				byte[] bytes = trans.digest(senha.getBytes()); //digerindo a senha

				StringBuilder sb = new StringBuilder(); //transformando os bytes em string de novo
				for(int i=0; i< bytes.length ;i++){
					sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
				}
				String generatedPassword = sb.toString();
				System.out.println(generatedPassword);

				if(userLogin.getsenha().compareTo(generatedPassword)==0){ //comparando se é a mesma
					System.out.println("Login efetuado!");
				}else{
					System.out.println("Login invalido!");
	            }

			}
			break;
    
		
			
		
			}

		}while(opc>0);

		}catch(Exception e){

		}

		}
	}