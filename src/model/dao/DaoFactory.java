package model.dao;

import db.DB;
import model.dao.impl.DepartamentoDaoJdbc;
import model.dao.impl.VendedorDaoJdbc;

//Fabrica de DAO
public class DaoFactory {
	
	public static VendedorDao criarVendedorDao() {
		return new VendedorDaoJdbc(DB.getConnection());
	}
	
	public static DepartamentoDao criarDepartamento() {
		return new DepartamentoDaoJdbc(DB.getConnection());
	}

}
