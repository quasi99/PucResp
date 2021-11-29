public class Latcinio extends Produto {

	private static final long serialVersionUID = 1L;

	public String tag() {
		return "Util em receitas no geral";
	}
	public Latcinio(String nome, int codigo, String fornecedor) {
		super(nome, codigo, fornecedor);
		this.categoria = "Latcinio";
	}
}
