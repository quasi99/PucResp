import java.io.Serializable;

public abstract class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private int codigo;
	private String fornecedor;
	protected String categoria;

	public Produto(String nome, int codigo, String fornecedor) {
		this.nome = nome;
		this.codigo = codigo;
		this.fornecedor = fornecedor;
	}
	public String toString() {
		String retorno = "";
		retorno += "Nome: "			+ this.nome 		+ "\n";
		retorno += "Codigo: "		+ this.codigo 		+ "\n";
		retorno += "Fornecedor: "	+ this.fornecedor	+ "\n";
		retorno += "Categoria: "	+ this.categoria 	+ "\n";
		retorno += "Tag: "			+ tag()				+ "\n";
		return retorno;
	}
	public abstract String tag();
}
