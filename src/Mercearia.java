public class Mercearia extends Produto {

	private static final long serialVersionUID = 1L;

	public String tag() {
		return "Graos em geral";
	}
	public Mercearia(String nome, int codigo, String fornecedor) {
		super(nome, codigo, fornecedor);
		this.categoria = "Mercearia";
	}	
}
