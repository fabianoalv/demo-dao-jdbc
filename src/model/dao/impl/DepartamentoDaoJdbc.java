package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartamentoDao;
import model.entidades.Departamento;

public class DepartamentoDaoJdbc implements DepartamentoDao {

	private Connection conn;

	public DepartamentoDaoJdbc(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void inserir(Departamento dp) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO department (Name) VALUES (?) ", PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setString(1, dp.getNome());
			
			int linhasAfetadas = st.executeUpdate();
			
			if (linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				
				if (rs.next()) {
					int id = rs.getInt(1);
					dp.setId(id);
				}
				
				else {
					 throw new DbException("Nenhuma linha afetada!");
				}
				
				DB.closeResultSet(rs);
				
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void atualizar(Departamento dp) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE department SET Name = ? WHERE Id = ? ");
			
			st.setString(1, dp.getNome());
			st.setInt(2, dp.getId());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deletarPorId(Integer id) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("delete from department where Id = ? ");

			st.setInt(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Departamento buscarPorId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM department WHERE Id = ? ");

			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				Departamento dp = new Departamento();
				dp.setId(rs.getInt("Id"));
				dp.setNome(rs.getString("Name"));
				return dp;

			}

			return null;

		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}

		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Departamento> buscarTudo() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM department ");

			rs = st.executeQuery();

			List<Departamento> lista = new ArrayList<Departamento>();

			while (rs.next()) {
				Departamento dp = new Departamento();
				dp.setId(rs.getInt("Id"));
				dp.setNome(rs.getString("Name"));
				lista.add(dp);
			}

			return lista;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

}
