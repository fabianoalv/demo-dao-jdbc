package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public void inserir(Vendedor vd) {
		PreparedStatement st = null;
		try {
		st = conn.prepareStatement("INSERT INTO seller " + 
				"(Name, Email, BirthDate, BaseSalary, DepartmentId) " + 
				"VALUES " + 
				"(?, ?, ?, ?, ?) " , PreparedStatement.RETURN_GENERATED_KEYS);
		
		
		st.setString(1, vd.getNome());
		st.setString(2, vd.getEmail());
		st.setDate(3, new Date(vd.getDataNascimento().getTime()));
		st.setDouble(4,vd.getSalarioBase());
		st.setInt(5, vd.getDepartamento().getId());
		
		int linhasAfetadas = st.executeUpdate();
		
			if(linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				
				if(rs.next()) {
				int d = rs.getInt(1);
				vd.setId(d);
				
				}
				
				DB.closeResultSet(rs);
				
			} else {
				throw new DbException("Nenhuma linha foi afetada");
			}
		
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

	@Override
	public void atualizar(Vendedor vd) {
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
			
			Vendedor vd;
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();

			// Verifica se há registros com o ID solicitado
			if (rs.next()) {
				// Cria o Objeto DP e seta as informações do resultset
				Departamento dp = instanciarDepartamento(rs);
				
				// Cria o Objeto vd e seta as informações do resultset
				 vd = instanciarVendedor(rs, dp);
			}

			// Se não houver registro com o ID informado, retorna NULL
			else {
				return null;
			}
			
			return vd;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	private Vendedor instanciarVendedor(ResultSet rs, Departamento dp) throws SQLException {
		Vendedor vd = new Vendedor();
		vd.setId(rs.getInt("Id"));
		vd.setNome(rs.getString("Name"));
		vd.setEmail(rs.getString("Email"));
		vd.setSalarioBase(rs.getDouble("BaseSalary"));
		vd.setDataNascimento(rs.getDate("BirthDate"));
		vd.setDepartamento(dp);
		return vd;
	}

	private Departamento instanciarDepartamento(ResultSet rs) throws SQLException {
		Departamento dp = new Departamento();
		dp.setId(rs.getInt("DepartmentId"));
		dp.setNome(rs.getString("DepName"));
		return dp;
	}

	@Override
	public List<Vendedor> buscarTudo() {

		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName " + 
					"FROM seller INNER JOIN department " + 
					"ON seller.DepartmentId = department.Id " + 
					"ORDER BY Name ");
			
			rs = st.executeQuery();
			
			List<Vendedor> list = new ArrayList<Vendedor>();
			Map<Integer, Departamento> map = new HashMap<>();
			
			while (rs.next()) {
				
				Departamento d = map.get(rs.getInt("DepartmentId"));
				
				if (d == null) {
					d = instanciarDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), d);
				}
				
				Vendedor vd = instanciarVendedor(rs, d);
				list.add(vd);
			}
			
			return list;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Vendedor> bucarPorDepartamento(Departamento dp) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName " + 
					"FROM seller INNER JOIN department " + 
					"ON seller.DepartmentId = department.Id " + 
					"WHERE DepartmentId = ? " + 
					"ORDER BY Name ");
			
			st.setInt(1, dp.getId());
			rs = st.executeQuery();
			
			List<Vendedor> list = new ArrayList<Vendedor>();
			Map<Integer, Departamento> map = new HashMap<>();
			
			while (rs.next()) {
				
				Departamento d = map.get(rs.getInt("DepartmentId"));
				
				if (d == null) {
					d = instanciarDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), d);
				}
				
				Vendedor vd = instanciarVendedor(rs, d);
				list.add(vd);
			}
			
			return list;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

}
