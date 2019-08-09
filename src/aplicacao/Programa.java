package aplicacao;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entidades.Departamento;
import model.entidades.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		VendedorDao vendedorDao = DaoFactory.criarVendedorDao();
		
		Vendedor vendedor = vendedorDao.buscarPorId(1);
		
		System.out.println(vendedor);
		
		Departamento dp = new Departamento(4, null);
		
		List<Vendedor> list = vendedorDao.bucarPorDepartamento(dp);
	
		for (Vendedor v : list) {
			System.out.println(v);
		}
		
		System.out.println("=====================================================================================");
		System.out.println("");
		
		list = vendedorDao.buscarTudo();
		
		for (Vendedor v : list) {
			System.out.println(v);
		}
		
//		System.out.println("####################################################################################");
//		Vendedor v = new Vendedor(null, "Jose", "joao@gmail.com", new Date(), 5000.5, dp);
//		vendedorDao.inserir(v);
		
		
		vendedor = vendedorDao.buscarPorId(11);
		vendedor.setNome("Fabiano");
		vendedor.setEmail("fabiano@gmail.com");
		vendedor.setSalarioBase(11000.30);
		vendedorDao.atualizar(vendedor);
	}

}
