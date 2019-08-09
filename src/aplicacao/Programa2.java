package aplicacao;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.entidades.Departamento;

public class Programa2 {

	public static void main(String[] args) {
		
		DepartamentoDao dpDao = DaoFactory.criarDepartamento();
		
		Departamento dp = dpDao.buscarPorId(4);
		
		System.out.println(dp);
		
		List<Departamento> lista = dpDao.buscarTudo();
		
		for (Departamento d : lista) {
			System.out.println(d);
		}
		
		//dpDao.deletarPorId(6);
//		Departamento d = new Departamento(3, null);
//		d.setNome("TI");
//		dpDao.atualizar(d);
		
		Departamento departamento = new Departamento(null, "Desenvolvemento");
		
		dpDao.inserir(departamento);
		System.out.println("ID gerado: " + departamento.getId());
	}

}
