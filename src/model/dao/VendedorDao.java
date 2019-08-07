package model.dao;

import java.util.List;

public interface VendedorDao {
	
	void inserir(VendedorDao vd);

	void atualizar(VendedorDao vd);

	void deletarPorId(Integer id);

	VendedorDao buscarPorId(Integer id);

	List<VendedorDao> buscarTudo();


}
