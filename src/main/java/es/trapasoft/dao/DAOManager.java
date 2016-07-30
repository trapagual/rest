package es.trapasoft.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.trapasoft.modelo.Persona;


/**
 * Esta clase gestiona todos los accesos a datos
 * Utiliza PoolConexiones para conectar, y las excepciones DAOException para la gestion de errores
 * @author SGEN0290
 *
 */
public class DAOManager {
	
	private static boolean debug = true;
	
	
	
	
	public DAOManager() {
		super();
	}
	
	public Persona getPersona(int id) throws DAOException {

		Persona elmenda = new Persona();
		
		// Pool de Conexiones
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs=null;
		String SQL = "SELECT ID, CITY, FIRSTNAME, LASTNAME, PHONENUMBER, STREET, ZIPCODE, DEPARTMENT_ID FROM person WHERE ID = ?";
		try {
			con = PoolConexiones.getConexion();
			stm = con.prepareStatement(SQL);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			if (rs.next()) {
				elmenda.setID(rs.getInt("ID"));
				elmenda.setCity(rs.getString("CITY"));
				elmenda.setFirstName(rs.getString("FIRSTNAME"));
				elmenda.setLastName(rs.getString("LASTNAME"));
				elmenda.setPhoneNumber(rs.getString("PHONENUMBER"));
				elmenda.setStreet(rs.getString("STREET"));
				elmenda.setZipCode(rs.getString("ZIPCODE"));
				elmenda.setDeptID(rs.getInt("DEPARTMENT_ID"));
			} else {
				throw new DAOException("getPersona: No se encuentra persona con el ID:"+id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("getPersona: Error SQL: "+e.getLocalizedMessage(),e.getCause());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return elmenda;
	}
	
	public List<Persona> getPersonas(String nombreOapellido) {
		
		List<Persona> listamendas = new ArrayList<Persona>();
		Persona elmenda = new Persona();
		
		// Pool de Conexiones
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs=null;
		String SQL = "SELECT ID, CITY, FIRSTNAME, LASTNAME, PHONENUMBER, STREET, ZIPCODE, DEPARTMENT_ID FROM person WHERE UPPER(FIRSTNAME) LIKE ? OR UPPER(LASTNAME) LIKE ?";
		System.out.println("getPersonas antes: "+SQL);
		String par = "%"+nombreOapellido.toUpperCase()+"%";
		try {
			con = PoolConexiones.getConexion();
			stm = con.prepareStatement(SQL);
			stm.setString(1, par);
			stm.setString(2, par);
			
			rs = stm.executeQuery();
			while (rs.next()) {
				elmenda = new Persona();
				
				elmenda.setID(rs.getInt("ID"));
				elmenda.setCity(rs.getString("CITY"));
				elmenda.setFirstName(rs.getString("FIRSTNAME"));
				elmenda.setLastName(rs.getString("LASTNAME"));
				elmenda.setPhoneNumber(rs.getString("PHONENUMBER"));
				elmenda.setStreet(rs.getString("STREET"));
				elmenda.setZipCode(rs.getString("ZIPCODE"));
				elmenda.setDeptID(rs.getInt("DEPARTMENT_ID"));
				
				listamendas.add(elmenda);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("getPersonas: Error SQL: "+e.getLocalizedMessage(),e.getCause());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return listamendas;
	}

	public List<Persona> getPersonasByDept(int deptId) {
		
		List<Persona> listamendas = new ArrayList<Persona>();
		Persona elmenda = new Persona();
		
		// Pool de Conexiones
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs=null;
		String SQL = "SELECT ID, CITY, FIRSTNAME, LASTNAME, PHONENUMBER, STREET, ZIPCODE, DEPARTMENT_ID FROM person WHERE DEPARTMENT_ID = ?";
		try {
			con = PoolConexiones.getConexion();
			stm = con.prepareStatement(SQL);
			stm.setInt(1, deptId);

			
			rs = stm.executeQuery();
			while (rs.next()) {
				
				elmenda = new Persona();
				
				elmenda.setID(rs.getInt("ID"));
				elmenda.setCity(rs.getString("CITY"));
				elmenda.setFirstName(rs.getString("FIRSTNAME"));
				elmenda.setLastName(rs.getString("LASTNAME"));
				elmenda.setPhoneNumber(rs.getString("PHONENUMBER"));
				elmenda.setStreet(rs.getString("STREET"));
				elmenda.setZipCode(rs.getString("ZIPCODE"));
				elmenda.setDeptID(rs.getInt("DEPARTMENT_ID"));
				
				listamendas.add(elmenda);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("getPersonasByDept: Error SQL: "+e.getLocalizedMessage(),e.getCause());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return listamendas;
	}

	public Persona getPersonaWithDept(int id) throws DAOException {
	
		Persona elmenda = new Persona();
		
		// Pool de Conexiones
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs=null;
		String SQL = "SELECT p.ID, p.CITY, p.FIRSTNAME, p.LASTNAME, p.PHONENUMBER, p.STREET, p.ZIPCODE, p.DEPARTMENT_ID, d.NAME FROM person p, department d WHERE p.ID = ? AND p.DEPARTMENT_ID = d.ID";
		try {
			con = PoolConexiones.getConexion();
			stm = con.prepareStatement(SQL);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			if (rs.next()) {
				elmenda.setID(rs.getInt("ID"));
				elmenda.setCity(rs.getString("CITY"));
				elmenda.setFirstName(rs.getString("FIRSTNAME"));
				elmenda.setLastName(rs.getString("LASTNAME"));
				elmenda.setPhoneNumber(rs.getString("PHONENUMBER"));
				elmenda.setStreet(rs.getString("STREET"));
				elmenda.setZipCode(rs.getString("ZIPCODE"));
				elmenda.setDeptID(rs.getInt("DEPARTMENT_ID"));
			} else {
				throw new DAOException("getPersona: No se encuentra persona con el ID:"+id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("getPersona: Error SQL: "+e.getLocalizedMessage(),e.getCause());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return elmenda;
	}

}
