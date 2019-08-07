package model.dao;

import java.util.List;

public interface Vendedor {
	
	void inserir(Vendedor vd);

	void atualizar(Vendedor vd);

	void deletarPorId(Integer id);

	Vendedor buscarPorId(Integer id);

	List<Vendedor> buscarTudo();


}
