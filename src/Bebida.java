public class Bebida extends Produto {

	private static final long serialVersionUID = 1L;

	public String tag() {
		return "Otimo para refrescar";
	}
	public Bebida(String nome, int codigo, String fornecedor) {
		super(nome, codigo, fornecedor);
		this.categoria = "Bebida";
	}
}
