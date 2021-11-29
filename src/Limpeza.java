public class Limpeza extends Produto {

	private static final long serialVersionUID = 1L;

	public String tag() {
		return "Limpeza em geral";
	}
	public Limpeza(String nome, int codigo, String fornecedor) {
		super(nome, codigo, fornecedor);
		this.categoria = "Limpeza";
	}
}
