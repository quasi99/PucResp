import java.io.EOFException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Mercado {
	private ArrayList<Produto> produtos;

	public Mercado() {
		this.produtos = new ArrayList<Produto>();
	}
	public String[] leValores (String [] dadosIn) {
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0;i < dadosIn.length; i++) {
			dadosOut[i] = JOptionPane.showInputDialog ("Entre com " + dadosIn[i] + ": ");
		}
		return dadosOut;
	}

	public Bebida leBebida() {

		String [] valores = new String [3];
		String [] nomeVal = {"Nome", "Codigo", "Fornecedor"};
		valores = leValores (nomeVal);

		int codigo = this.retornaInteiro(valores[1]);

		Bebida bebida = new Bebida (valores[0],codigo,valores[2]);
		return bebida;		
	}

	public Latcinio leLatcinio() {

		String [] valores = new String [3];
		String [] nomeVal = {"Nome", "Codigo", "Fornecedor"};
		valores = leValores (nomeVal);

		int codigo = this.retornaInteiro(valores[1]);

		Latcinio latcinio = new Latcinio (valores[0],codigo,valores[2]);
		return latcinio;		
	}

	public Limpeza leLimpeza() {

		String [] valores = new String [3];
		String [] nomeVal = {"Nome", "Codigo", "Fornecedor"};
		valores = leValores (nomeVal);

		int codigo = this.retornaInteiro(valores[1]);

		Limpeza limpeza = new Limpeza (valores[0],codigo,valores[2]);
		return limpeza;		
	}

	public Mercearia leMercearia() {

		String [] valores = new String [3];
		String [] nomeVal = {"Nome", "Codigo", "Fornecedor"};
		valores = leValores (nomeVal);

		int codigo = this.retornaInteiro(valores[1]);

		Mercearia mercearia = new Mercearia (valores[0],codigo,valores[2]);
		return mercearia;		
	}

	public OrtiFrut leOrtiFrut() {

		String [] valores = new String [3];
		String [] nomeVal = {"Nome", "Codigo", "Fornecedor"};
		valores = leValores (nomeVal);

		int codigo = this.retornaInteiro(valores[1]);

		OrtiFrut ortifrut = new OrtiFrut (valores[0],codigo,valores[2]);
		return ortifrut;		
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}

	public int retornaInteiro(String entrada) {
		int numInt;

		while(!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um numero inteiro.");
		}
		return Integer.parseInt(entrada);
	}

	public void salvaProdutos(ArrayList<Produto> produtos) {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream("c:\\temp\\Mercado.dados"));
			for(int i = 0; i < produtos.size(); i++) {
				outputStream.writeObject(produtos.get(i));
			}
		} catch(FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Impossivel criar arquivo!");
			ex.printStackTrace();
		} catch(IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Produto> recuperaProdutos() {
		ArrayList<Produto> produtosTemp = new ArrayList<Produto>();

		ObjectInputStream inputStream = null;

		try {
			inputStream = new ObjectInputStream(new FileInputStream("c:\\temp\\Mercado.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Produto) {
					produtosTemp.add((Produto) obj);
				}
			}
		} catch(EOFException ex) {
			System.out.println("Fim de arquivo.");
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch(FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com produtos nao existe!");
			ex.printStackTrace();
		} catch(IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch(final IOException ex) {
				ex.printStackTrace();
			}
			return produtosTemp;
		}
	}

	public void menuMercado() {

		String menu = "";
		String entrada;
		int opc1, opc2;

		do {
			menu = "Controle Mercado\n" +
				   "Opcoes:\n" + 
				   "1. Entrar Produtos\n" +
				   "2. Exibir Produtos\n" +
				   "3. Limpar Produtos\n" +
				   "4. Gravar Produtos\n" +
				   "5. Recuperar Produtos\n" +
				   "9. Sair";
			entrada = JOptionPane.showInputDialog(menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch(opc1) {
				case 1:
					menu = "Entrada de Produtos\n" +
						   "Opcoes:\n" +
						   "1. Bebida\n" +
						   "2. Limpeza\n" +
						   "3. Latcinio\n" +
						   "4. Mercearia\n" +
						   "5. OrtiFrut\n";

					entrada = JOptionPane.showInputDialog(menu + "\n\n");
					opc2 = this.retornaInteiro(entrada);

					switch(opc2) {
						case 1: produtos.add((Produto)leBebida());
						break;
						case 2: produtos.add((Produto)leLimpeza());
						break;
						case 3: produtos.add((Produto)leLatcinio());
						break;
						case 4: produtos.add((Produto)leMercearia());
						break;
						case 5: produtos.add((Produto)leOrtiFrut());
						break;
						default: JOptionPane.showMessageDialog(null,"Produto para entrada nao escolhido!");
					}
					break;
				case 2:
					if (produtos.size() == 0) {
						JOptionPane.showMessageDialog(null,"Entre com produtos primeiramente");
						break;
					}
					String dados = "";
					for (int i = 0; i < produtos.size(); i++) {
						dados += produtos.get(i).toString() + "----------------\n";
					}
					JOptionPane.showMessageDialog(null,dados);
					break;
				case 3:
					if (produtos.size() == 0) {
						JOptionPane.showMessageDialog(null,"Entre com produtos primeiramente");
						break;
					}
					produtos.clear();
					JOptionPane.showMessageDialog(null,"Dados limpos com sucesso!");
					break;
				case 4:
					if (produtos.size() == 0) {
						JOptionPane.showMessageDialog(null,"Entre com produtos primeiramente");
						break;
					}
					salvaProdutos(produtos);
					JOptionPane.showMessageDialog(null,"Dados salvos com sucesso!");
					break;
				case 5:
					produtos = recuperaProdutos();
					if (produtos.size() == 0) {
						JOptionPane.showMessageDialog(null,"Sem dados para recuperar.");
						break;
					}
					JOptionPane.showMessageDialog(null,"Dados recuperados com sucesso!");
					break;
				case 9:
					JOptionPane.showMessageDialog(null,"Fim do apricativo MERCADO");
					break;	
			}
		} while (opc1 != 9);
	}

	public static void main(String[] args) {
		
		Mercado merc = new Mercado();
		merc.menuMercado();
	}
}
