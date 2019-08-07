package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entidades.Departamento;
import model.entidades.Vendedor;

public class VendedorDaoJdbc implements VendedorDao {

	private Connection conn;

	public VendedorDaoJdbc(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void inserir(VendedorDao vd) {
		// TODO Auto-generated method stub

	}

	@Override
	public void atualizar(VendedorDao vd) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletarPorId(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Vendedor buscarPorId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName\r\n" + "FROM seller INNER JOIN department\r\n"
							+ "ON seller.DepartmentId = department.Id\r\n" + "WHERE seller.Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();

			// Verifica se há registros com o ID solicitado
			if (rs.next()) {
				// Cria o Objeto DP e seta as informações do resultset
				Departamento dp = new Departamento();
				dp.setId(rs.getInt("DepartmentId"));
				dp.setNome(rs.getString("DepName"));

				Vendedor vd = new Vendedor();
				vd.setId(rs.getInt("Id"));
				vd.setNome(rs.getString("Name"));
				vd.setEmail(rs.getString("Email"));
				vd.setSalarioBase(rs.getDouble("BaseSalary"));
				vd.setDataNascimento(rs.getDate("BirthDate"));
				vd.setDepartamento(dp);
				return vd;
			}

			// Se não houver registro com o ID informado, retorna NULL
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public List<Vendedor> buscarTudo() {
		// TODO Auto-generated method stub
		return null;
	}

}
