package telefonia.controller;

import java.util.ArrayList;

import telefonia.model.bo.EnderecoBO;
import telefonia.model.vo.Endereco;

public class EnderecoController {
	private static final int TAMANHO_MINIMO_CAMPO_RUA = 3;
	private static final int TAMANHO_MAXIMO_CAMPO_RUA = 255;
	
	private static final int TAMANHO_MINIMO_CAMPO_NUMERO = 1;
	private static final int TAMANHO_MAXIMO_CAMPO_NUMERO = 10;
	
	private static final int TAMANHO_MINIMO_CAMPO_BAIRRO = 3;
	private static final int TAMANHO_MAXIMO_CAMPO_BAIRRO = 255;

	private static final int TAMANHO_MINIMO_CAMPO_CIDADE = 3;
	private static final int TAMANHO_MAXIMO_CAMPO_CIDADE = 255;

	private EnderecoBO bo = new EnderecoBO();

	public String excluir(String textoIdSelecionado) {
		String mensagem = "";
		try {
			int idSelecionado = Integer.parseInt(textoIdSelecionado);
			mensagem = bo.excluir(idSelecionado);
		} catch (NumberFormatException ex) {
			mensagem = "Informe um n�mero inteiro";
		}
		return mensagem;
	}

	public String salvar(String rua, String bairro, String numero, String cep, String cidade, String estado) {
		String mensagem = "";

		// Valida��es dos campos
		mensagem += validarCampoDeTexto("Rua", rua, TAMANHO_MINIMO_CAMPO_RUA, TAMANHO_MAXIMO_CAMPO_RUA, true);
		mensagem += validarCampoDeTexto("Cidade", cidade, TAMANHO_MINIMO_CAMPO_CIDADE, TAMANHO_MAXIMO_CAMPO_CIDADE,
				true);
		mensagem += validarCampoDeTexto("Sigla do estado", estado, 2, 2, true);

		if (mensagem.isEmpty()) {
			Endereco novoEndereco = new Endereco(rua, cep, estado, cidade, bairro, numero);
			mensagem = bo.salvar(novoEndereco);
		}

		return mensagem;
	}

	private String validarCampoDeTexto(String nomeDoCampo, String valor, int tamanhoMinimo, int tamanhoMaximo,
			boolean obrigatorio) {
		String mensagemValidacao = "";

		if (obrigatorio && valor != null 
					&& !valor.isEmpty() 
					|| valor.length() < tamanhoMinimo 
					|| valor.length() > tamanhoMaximo) {
				mensagemValidacao = nomeDoCampo + " deve possuir pelo menos " + tamanhoMinimo + " e no m�ximo "
						+ tamanhoMaximo + " caracteres \n";
			}

		return mensagemValidacao;
	}
	
	public String consultarPorId(String txtIdInformado) {
		String msg ="";
		try {
			int idInformado = Integer.parseInt(txtIdInformado);
			msg = bo.consultarPorId(idInformado);
		} catch (NumberFormatException ex) {
			msg = "Informe um n�mero inteiro";
		}
		return msg;
	}

	public String editar(String txtIdEndEdit,String rua, String bairro, String numero, 
			String cep, String cidade, String estado) {
		
		String mensagem = "";
		int idInformado;
		
		try {
			idInformado = Integer.parseInt(txtIdEndEdit);
			
		} catch (NumberFormatException ex) {
			mensagem = "Informe um n�mero inteiro para o Id do Endere�o a Editar.";
		}
		mensagem += validarCampoDeTexto("Lougradouro", rua, TAMANHO_MINIMO_CAMPO_RUA, TAMANHO_MAXIMO_CAMPO_RUA, true);
		mensagem += validarCampoDeTexto("Bairro", bairro, TAMANHO_MINIMO_CAMPO_BAIRRO, TAMANHO_MAXIMO_CAMPO_BAIRRO, true);
		mensagem += validarCampoDeTexto("N�mero", numero, TAMANHO_MINIMO_CAMPO_NUMERO, TAMANHO_MAXIMO_CAMPO_NUMERO, true);
		mensagem += validarCampoDeTexto("Cidade", cidade, TAMANHO_MINIMO_CAMPO_CIDADE, TAMANHO_MAXIMO_CAMPO_CIDADE,true);
		mensagem += validarCampoDeTexto("Sigla do estado", estado, 2, 2, true);
		
		if (mensagem.isEmpty()) {
			Endereco endereco = new Endereco();
			bo.editar(endereco);
		}
		
		return mensagem;
		
		
	}
	
	public ArrayList<Endereco> consultarTodos() {
		return bo.consultarTodos();
		
	}

}
