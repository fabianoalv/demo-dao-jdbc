package model.dao;

import java.util.List;

import model.entidades.Departamento;
import model.entidades.Vendedor;

public interface VendedorDao {
	
	void inserir(Vendedor vd);

	void atualizar(Vendedor vd);

	void deletarPorId(Integer id);

	Vendedor buscarPorId(Integer id);

	List<Vendedor> buscarTudo();
	
	List<Vendedor> bucarPorDepartamento(Departamento dp);


}
