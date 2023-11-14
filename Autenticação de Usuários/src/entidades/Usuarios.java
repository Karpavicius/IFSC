package entidades;

public class Usuarios {
	
	  private String nome;
	  private String senha;
	  private byte[] sal;

	  public Usuarios(String nome, String senha, byte[] sal){
	    this.nome = nome;
	    this.senha = senha;
	    this.sal = sal;
	  }

	  public String getnome(){

	    return this.nome;
	  }

	   public String getsenha(){

	    return this.senha;
	  }

	   public byte[] getsal(){

	    return this.sal;
	  }


}
