public class OrtiFrut extends Produto {

	private static final long serialVersionUID = 1L;

	public String tag() {
		return "Vindos dos campos";
	}
	public OrtiFrut(String nome, int codigo, String fornecedor) {
		super(nome, codigo, fornecedor);
		this.categoria = "OrtiFrut";
	}
}
