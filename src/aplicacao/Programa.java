package aplicacao;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entidades.Departamento;
import model.entidades.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		VendedorDao vendedorDao = DaoFactory.criarVendedorDao();
		
		Vendedor vendedor = vendedorDao.buscarPorId(3);
		
		System.out.println(vendedor);

	}

}
