package horizon.controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import horizon.dao.*;

import horizon.modelo.*;


@WebServlet("/controlador")
public class Controlador extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public Controlador() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if (action == null)
			action = "gerenciarVendedors";
		
		try {
			if (action.equals("gerenciarVendedores")) {
				gerenciarVendedores(request, response);
			} else if (action.equals("detalhesVendedor")) { 
				detalhesVendedor(request, response);
			} else if (action.equals("formCadastrarVendedor")) { 
				formCadastrarVendedor(request, response);
			} else if (action.equals("cadastrarVendedor")) {
				cadastrarVendedor(request, response);
			} else if (action.equals("formAtualizarVendedor")) { 
				formAtualizarVendedor(request, response);
			} else if (action.equals("atualizarVendedor")) {
				atualizarVendedor(request, response);
			} else if (action.equals("removerVendedor")) { 
				removerVendedor(request, response);
			} else if (action.equals("gerenciarClientes")) {
				gerenciarClientes(request, response);
			} else if (action.equals("detalhesCliente")) {
				detalhesCliente(request, response);
			} else if (action.equals("formCadastrarCliente")) {
				formCadastrarCliente(request, response);
			} else if (action.equals("cadastrarCliente")) {
				cadastrarCliente(request, response);
			} else if (action.equals("formAtualizarCliente")) {
				formAtualizarCliente(request, response);
			} else if (action.equals("atualizarCliente")) {
				atualizarCliente(request, response);
			} else if (action.equals("removerCliente")) { 
				removerCliente(request, response);
			} else if (action.equals("gerenciarProdutos")) {
				gerenciarProdutos(request, response);
			} else if (action.equals("detalhesProduto")) {
				detalhesProduto(request, response);
			} else if (action.equals("formCadastrarProduto")) {
				formCadastrarProduto(request, response);
			} else if (action.equals("cadastrarProduto")) {
				cadastrarProduto(request, response);
			} else if (action.equals("formAtualizarProduto")) {
				formAtualizarProduto(request, response);
			} else if (action.equals("atualizarProduto")) {
				atualizarProduto(request, response);
			} else if (action.equals("removerProduto")) { 
				removerProduto(request, response);
			} else if (action.equals("formCadastrarModerador")) {
				formCadastrarModerador(request, response);
			} else if (action.equals("cadastrarModerador")) {
				cadastrarModerador(request, response);
			} else if (action.equals("index")) {
				index(request, response);
			} else if (action.equals("verProdutos")) {
				verProdutos(request, response);
			}else if (action.equals("loginCliente")) {
				loginCliente(request, response);
			}else if (action.equals("loginVendedor")) {
				loginVendedor(request, response);
			}else if (action.equals("loginModerador")) {
				loginModerador(request, response);
			}else if (action.equals("confirmLoginCliente")) {
				confirmLoginCliente(request, response);
			}else if (action.equals("confirmLoginVendedor")) {
				confirmLoginVendedor(request, response);
			}else if (action.equals("confirmLoginModerador")) {
				confirmLoginModerador(request, response);
			}else {				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/vendedor/erroVendedor.jsp"); 
				rd.forward(request, response);
			}
		} catch (ServletException | IOException | DAOException e) {
			e.printStackTrace();
		}
	} 
	
	 
	private void gerenciarVendedores(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		
		DAOVendedor dao = new DAOVendedorImpl();
		List<Vendedor> vendedores = dao.todosVendedors();

		request.setAttribute("vendedores", vendedores);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/vendedor/gerenciarVendedores.jsp");

		rd.forward(request, response);
	}

	private void detalhesVendedor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		
		DAOVendedor dao = new DAOVendedorImpl();
		
		int idVendedor = Integer.valueOf(request.getParameter("idVendedor"));

		Vendedor vendedor = dao.buscarVendedor(idVendedor);

		if (vendedor != null) {
			request.setAttribute("vendedor", vendedor);
		} else {
			request.setAttribute("msg", "O vendedor n�o est� cadastrado!");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/vendedor/detalhesVendedor.jsp");
		rd.forward(request, response);
	}

	private void formCadastrarVendedor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/vendedor/formularioCadastroVendedor.jsp");
		rd.forward(request, response);
	}

	private void cadastrarVendedor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		
		String nome = request.getParameter("nome");
		String sobreNome = request.getParameter("sobreNome");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String cpf = request.getParameter("cpf");
		String rg = request.getParameter("rg");
		String dtn = request.getParameter("dtn");
		String senha = request.getParameter("senha");
		String endereco = request.getParameter("endereco");
		String ppv = request.getParameter("ppv");
		int idVendedor = 0;

		Vendedor novoVendedor = new Vendedor(nome, sobreNome, email, tel, cpf, dtn, senha, rg, endereco, ppv, idVendedor);
		
		DAOVendedor dao = new DAOVendedorImpl();
		dao.cadastrar(novoVendedor);

		request.setAttribute("vendedor", novoVendedor);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/vendedor/vendedorCadastrado.jsp");
		rd.forward(request, response);
		
	}

	private void formAtualizarVendedor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {

		int idVendedor = Integer.valueOf(request.getParameter("idVendedor"));

		DAOVendedor dao = new DAOVendedorImpl();
		Vendedor vendedor = dao.buscarVendedor(idVendedor);

		if (vendedor != null)
			request.setAttribute("vendedor", vendedor);
		else
			request.setAttribute("msg", "O vendedor n�o est� cadastrado!");

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/vendedor/formularioAtualizarVendedor.jsp");
		rd.forward(request, response);
	}

	private void atualizarVendedor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {

		String nome = request.getParameter("nome");
		String sobreNome = request.getParameter("sobreNome");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String cpf = request.getParameter("cpf");
		String rg = request.getParameter("rg");
		String dtn = request.getParameter("dtn");
		String senha = request.getParameter("senha");
		String endereco = request.getParameter("endereco");
		String ppv = request.getParameter("ppv");
		int idVendedor = Integer.valueOf(request.getParameter("idVendedor"));

		Vendedor vendedorAtualizado = new Vendedor(nome, sobreNome, email, tel, cpf, dtn, senha, rg, endereco, ppv, idVendedor);

		DAOVendedor dao = new DAOVendedorImpl();
		Vendedor vendedor = dao.buscarVendedor(idVendedor);

		if (vendedor != null) {
			dao.atualizar(vendedorAtualizado);
			request.setAttribute("vendedor", vendedorAtualizado);
		} else {
			request.setAttribute("msg", "O vendedor n�o est� cadastrado!");
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/vendedor/vendedorAtualizado.jsp");
		rd.forward(request, response);
	}

	private void removerVendedor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {

		int idVendedor = Integer.valueOf(request.getParameter("idVendedor"));

		DAOVendedor dao = new DAOVendedorImpl();
		Vendedor vendedor = dao.buscarVendedor(idVendedor);

		if (vendedor != null) {
			dao.remover(dao.buscarVendedor(idVendedor));
			request.setAttribute("vendedor", vendedor);
		} else {
			request.setAttribute("msg", "O vendedor n�o est� cadastrado!");
		}
		RequestDispatcher rd = request.getRequestDispatcher("controlador?action=gerenciarVendedores");
		rd.forward(request, response);
	}
	

	private void gerenciarClientes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException{

		DAOCliente dao = new DAOClienteImpl();
		List<Cliente> clientes = dao.todosClientes();

		request.setAttribute("clientes", clientes);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/cliente/gerenciarCliente.jsp");

		rd.forward(request, response);
	}

	private void detalhesCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {

		DAOCliente dao = new DAOClienteImpl();
		Cliente cliente = dao.buscarCliente(Integer.valueOf(request.getParameter("idCliente")));

		if (cliente != null) {
			request.setAttribute("cliente", cliente);
		} else {
			request.setAttribute("msg", "O cliente n�o est� cadastrado!");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/cliente/detalhesCliente.jsp");
		rd.forward(request, response);
	}
	
	private void formCadastrarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/cliente/formularioCadastroCliente.jsp");
		rd.forward(request, response);
	}

	private void cadastrarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		
		String nome = request.getParameter("nome");
		String sobreNome = request.getParameter("sobreNome");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String cpf = request.getParameter("cpf");
		String dtn = request.getParameter("dtn");
		String senha = request.getParameter("senha");
		int idCliente = 0;

		Cliente novoCliente = new Cliente(nome, sobreNome, email, tel, cpf, dtn, senha, idCliente);
		
		DAOCliente dao = new DAOClienteImpl();
		dao.cadastrar(novoCliente);

		request.setAttribute("cliente", novoCliente);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/cliente/clienteCadastrado.jsp");
		rd.forward(request, response);
		
	}

	private void formAtualizarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {

		int idCliente = Integer.valueOf(request.getParameter("idCliente"));

		DAOCliente dao = new DAOClienteImpl();
		Cliente cliente = dao.buscarCliente(idCliente);

		if (cliente != null)
			request.setAttribute("cliente", cliente);
		else
			request.setAttribute("msg", "O cliente n�o est� cadastrado!");

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/cliente/formularioAtualizarCliente.jsp");
		rd.forward(request, response);
	}

	private void atualizarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {

		String nome = request.getParameter("nome");
		String sobreNome = request.getParameter("sobreNome");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String cpf = request.getParameter("cpf");
		String dtn = request.getParameter("dtn");
		String senha = request.getParameter("senha");
		int idCliente = Integer.valueOf(request.getParameter("idCliente"));

		Cliente clienteAtualizado = new Cliente(nome, sobreNome, email, tel, cpf, dtn, senha, idCliente);

		DAOCliente dao = new DAOClienteImpl();
		Cliente cliente = dao.buscarCliente(idCliente);

		if (cliente != null) {
			dao.atualizar(clienteAtualizado);
			request.setAttribute("cliente", clienteAtualizado);
		} else {
			request.setAttribute("msg", "O cliente n�o est� cadastrado!");
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/cliente/clienteAtualizado.jsp");
		rd.forward(request, response);
	}

	private void removerCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {

		int idCliente = Integer.valueOf(request.getParameter("idCliente"));

		DAOCliente dao = new DAOClienteImpl();
		Cliente cliente = dao.buscarCliente(idCliente);

		if (cliente != null) {
			dao.remover(dao.buscarCliente(idCliente));
			request.setAttribute("cliente", cliente);
		} else {
			request.setAttribute("msg", "O cliente n�o est� cadastrado!");
		}
		RequestDispatcher rd = request.getRequestDispatcher("controlador?action=gerenciarClientes");
		rd.forward(request, response);
	}	
	
	private void gerenciarProdutos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {

		DAOProduto dao = new DAOProdutoImpl();
		List<Produto> produtos = dao.todosProdutos();

		request.setAttribute("produtos", produtos);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/produto/gerenciarProdutos.jsp");

		rd.forward(request, response);
	}

	private void detalhesProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		
		DAOProduto dao = new DAOProdutoImpl();
				
		Produto produto = dao.buscarProduto(Integer.valueOf(request.getParameter("idProduto")));

		if (produto != null) {
			request.setAttribute("produto", produto);
		} else {
			request.setAttribute("msg", "O produto n�o est� cadastrado!");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/produto/detalhesProduto.jsp");
		rd.forward(request, response);
	}
	
	private void formCadastrarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/produto/formularioCadastrarProduto.jsp");
		rd.forward(request, response);
	}

	private void cadastrarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		
		String nome = request.getParameter("nome");
		int quantidade = Integer.valueOf(request.getParameter("quantidade"));
		String jogo = request.getParameter("jogo");
		String tipo = request.getParameter("tipo");
		String raridade = request.getParameter("raridade");
		float preco = Float.valueOf(request.getParameter("preco"));
		String descricao = request.getParameter("descricao");
		String plataforma = request.getParameter("plataforma");
		int idProduto = 0;

		Produto novoProduto = new Produto( nome,  plataforma,  jogo,  tipo,  raridade,  descricao, quantidade,  preco,  idProduto);
		
		DAOProduto dao = new DAOProdutoImpl();
		dao.cadastrar(novoProduto);

		request.setAttribute("produto", novoProduto);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/produto/produtoCadastrado.jsp");
		rd.forward(request, response);
		
	}

	private void formAtualizarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {

		DAOProduto dao = new DAOProdutoImpl();
		int idProduto = Integer.valueOf(request.getParameter("idProduto"));
		Produto produto = dao.buscarProduto(idProduto);

		if (produto != null)
			request.setAttribute("produto", produto);
		else
			request.setAttribute("msg", "O produto n�o est� cadastrado!");

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/produto/formularioAtualizarProduto.jsp");
		rd.forward(request, response);
	}

	private void atualizarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {

		String nome = request.getParameter("nome");
		int quantidade = Integer.valueOf(request.getParameter("quantidade"));
		String jogo = request.getParameter("jogo");
		String tipo = request.getParameter("tipo");
		String raridade = request.getParameter("raridade");
		float preco = Float.valueOf(request.getParameter("preco"));
		String descricao = request.getParameter("descricao");
		String plataforma = request.getParameter("plataforma");
		int idProduto = Integer.valueOf(request.getParameter("idProduto"));

		Produto produtoAtualizado = new Produto(nome,  plataforma,  jogo,  tipo,  raridade,  descricao, quantidade,  preco,  idProduto);
		DAOProduto dao = new DAOProdutoImpl();

		Produto produto = dao.buscarProduto(idProduto);

		if (produto != null) {
			dao.atualizar(produtoAtualizado);
			request.setAttribute("produto", produtoAtualizado);
		} else {
			request.setAttribute("msg", "O produto n�o est� cadastrado!");
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/produto/produtoAtualizado.jsp");
		rd.forward(request, response);
	}

	private void removerProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {

		DAOProduto dao = new DAOProdutoImpl();
		int idProduto = Integer.valueOf(request.getParameter("idProduto"));

		Produto produto = dao.buscarProduto(idProduto);

		if (produto != null) {
			dao.remover(dao.buscarProduto(idProduto));
			request.setAttribute("produto", produto);
		} else {
			request.setAttribute("msg", "O produto n�o est� cadastrado!");
		}
		RequestDispatcher rd = request.getRequestDispatcher("controlador?action=gerenciarProdutos");
		rd.forward(request, response);
	}	
	
	private void formCadastrarModerador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/moderador/formularioCadastroModerador.jsp");
		rd.forward(request, response);
	}

	private void cadastrarModerador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException {
		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		int idModerador = 0;
		
		Moderador novoModerador = new Moderador(nome, senha, email, idModerador);

		
		DAOModerador dao = new DAOModeradorImpl();
		dao.cadastrar(novoModerador);

		request.setAttribute("moderador", novoModerador);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
		rd.forward(request, response);
		
	}
	private void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
		rd.forward(request, response);
	}
	private void verProdutos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException{
		
		DAOProduto dao = new DAOProdutoImpl();
		List<Produto> produtos = dao.todosProdutos();

		request.setAttribute("produtos", produtos);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/produto/verProdutos.jsp");

		rd.forward(request, response);
	}
	private void confirmLoginCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException{
		
		DAOCliente dao = new DAOClienteImpl();
		List<Cliente> clientes = dao.todosClientes();
		
		for(int i = 0; i < clientes.size(); i++)
		{
			if((clientes.get(i).getSenha()).equals(request.getParameter("senhaCliente")) && (clientes.get(i).getEmail()).equals(request.getParameter("usuarioCliente"))) {
				RequestDispatcher rd = request.getRequestDispatcher("controlador?action=verProdutos");
				rd.forward(request, response);
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("controlador?action=index");
		rd.forward(request, response);
		
	}
	private void confirmLoginVendedor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException{
		
		DAOVendedor dao = new DAOVendedorImpl();
		List<Vendedor> vendedores = dao.todosVendedors();
		
		for(int i = 0; i < vendedores.size(); i++)
		{			
			if((vendedores.get(i).getSenha()).equals(request.getParameter("senhaVendedor")) && (vendedores.get(i).getEmail()).equals(request.getParameter("usuarioVendedor"))) {
				RequestDispatcher rd = request.getRequestDispatcher("controlador?action=verProdutos");
				rd.forward(request, response);
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("controlador?action=index");
		rd.forward(request, response);
		
	}
	private void confirmLoginModerador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DAOException{
		
		DAOModerador dao = new DAOModeradorImpl();
		List<Moderador> moderadores = dao.todosModeradors();
		
		for(int i = 0; i < moderadores.size(); i++)
		{
			if((moderadores.get(i).getSenha()).equals(request.getParameter("senhaModerador")) && (moderadores.get(i).getEmail()).equals(request.getParameter("usuarioModerador"))) {
				RequestDispatcher rd = request.getRequestDispatcher("controlador?action=verProdutos");
				rd.forward(request, response);
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("controlador?action=index");
		rd.forward(request, response);
		
	}
	private void loginModerador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/moderador/loginModerador.jsp");
		rd.forward(request, response);
	}
	private void loginVendedor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/vendedor/loginVendedor.jsp");
		rd.forward(request, response);
	}
	private void loginCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front/cliente/loginCliente.jsp");
		rd.forward(request, response);
	}

}