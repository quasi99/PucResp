import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Mercado1 {

	private ArrayList<Produto> produtos;

	
	public Mercado1() {
		this.produtos = new ArrayList<Produto>();
	}

	public void adicionaProduto(Produto prod) {
		this.produtos.add(prod);
	}

	public void listarProdutos() {
		for (Produto prod:produtos) {
			System.out.println(prod.toString());
		}
		System.out.println("Total = " + this.produtos.size() + " produtos listados com sucesso!\n");
	}

	public void excluirProduto(Produto prod) {
		if (this.produtos.contains(prod)) {
			this.produtos.remove(prod);
			System.out.println("[Produto "+ prod.toString() + "excluido com sucesso!]\n");
		} else {
			System.out.println("Produto inexistente!\n");
		}
	}

	public void excluirProdutos() {
		produtos.clear();
		System.out.println("Produtos excluidos com sucesso!\n");		
	}

	public void gravarProdutos() {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream (new FileOutputStream("c:\\temp\\produtos.dat"));
			for(Produto prod:produtos) {
				outputStream.writeObject(prod);
			}
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void recuperarProdutos() {
		ObjectInputStream inputStream = null;
		try {
			inputStream = new ObjectInputStream (new FileInputStream ("c:\\temp\\produtos.dat"));
			Object obj = null;
			while((obj = inputStream.readObject()) != null)
				if (obj instanceof Bebida) {
					this.produtos.add((Bebida)obj);
				}else if (obj instanceof Latcinio) {
					this.produtos.add((Latcinio)obj);
				}else if (obj instanceof Limpeza) {
					this.produtos.add((Limpeza)obj);
				}else if (obj instanceof Mercearia) {
					this.produtos.add((Mercearia)obj);
				}else if (obj instanceof OrtiFrut) {
					this.produtos.add((OrtiFrut)obj);
				}
		}catch (EOFException ex) {
			System.out.println ("End of file reached");
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				inputStream.close();
				System.out.println("Produtos recuperados com sucesso!\n");
				}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Mercado1 merc = new Mercado1();

		Bebida agua 		 = new Bebida("Agua", 252, "Aguas da Fonte Inc");
		Bebida refrigerante  = new Bebida("Redrigerante" , 365 , "Cosca Inc");
		Latcinio queijo 	 = new Latcinio("Queijo", 521, "D'roca latcineos Ltda");
		Latcinio leite 	 = new Latcinio("Leite", 223, "Tiroleza Inc");
		Limpeza desinfetante = new Limpeza("Desinfetante", 56, "ABeW Inc");
		Limpeza amaciante 	 = new Limpeza("Amaciante", 51, "FoFinho Ltd");
		Mercearia arroz 	 = new Mercearia("Arroz", 65, "De casa Inc");
		Mercearia aveia 	 = new Mercearia("Aveia", 254, "De casa Inc");
		OrtiFrut cebola 	 = new OrtiFrut("Cebola", 253, "AZ ort");
		OrtiFrut pera 		 = new OrtiFrut("Pera", 636, "Optime fruits");

		merc.adicionaProduto(agua);
		merc.adicionaProduto(refrigerante);
		merc.adicionaProduto(queijo);
		merc.adicionaProduto(leite);
		merc.adicionaProduto(desinfetante);
		merc.adicionaProduto(amaciante);
		merc.adicionaProduto(arroz);
		merc.adicionaProduto(aveia);
		merc.adicionaProduto(cebola);
		merc.adicionaProduto(pera);
		merc.listarProdutos();
		merc.gravarProdutos();
		merc.excluirProduto(leite);
		merc.listarProdutos();
		merc.excluirProdutos();
		merc.listarProdutos();
		merc.recuperarProdutos();
		merc.listarProdutos();
	}
}
