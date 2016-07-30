package es.trapasoft.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		String SQL = "SELECT p.ID, p.CITY, p.FIRSTNAME, p.LASTNAME, p.PHONENUMBER, p.STREET, p.ZIPCODE, p.DEPARTMENT_ID, d.NAME DEPARTMENT_NAME FROM person p, department d WHERE p.ID = ? AND p.DEPARTMENT_ID = d.ID";
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
				elmenda.setDeptName(rs.getString("DEPARTMENT_NAME"));
				
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
	
	public Persona addPersona(Persona p) {
		Connection con = null;
		PreparedStatement pstm = null;
		Statement s = null;
		ResultSet rs = null;
		int numSeq = -1;
		String SQLReadSequence = "SELECT SEQ_COUNT FROM sequence WHERE SEQ_NAME = 'SEQ_GEN'";
													// 1		2		3			4			5		6		7				8
		String SQLInsertPerson = "INSERT INTO person (CITY, FIRSTNAME, LASTNAME, PHONENUMBER, STREET, ZIPCODE, DEPARTMENT_ID, ID) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
		String SQLUpdateSequence = "UPDATE sequence SET SEQ_COUNT = ? WHERE SEQ_NAME = 'SEQ_GEN'";
		try {
			con = PoolConexiones.getConexion();
			// empezar Transaccion
			con.setAutoCommit(false);
			// leer la secuencia
			s = con.createStatement();
			rs = s.executeQuery(SQLReadSequence);
			while (rs.next()) {
				numSeq = rs.getInt("SEQ_COUNT");
			}
			rs.close();
			s.close();
			// aumentar el contador
			if (numSeq > 0) numSeq++;
			
			// cargar el insert
			pstm = con.prepareStatement(SQLInsertPerson);
			pstm.setString(1, p.getCity());
			pstm.setString(2, p.getFirstName());
			pstm.setString(3, p.getLastName());
			pstm.setString(4, p.getPhoneNumber());
			pstm.setString(5, p.getStreet());
			pstm.setString(6, p.getPhoneNumber());
			pstm.setInt(7, p.getDeptID());
			pstm.setInt(8, numSeq);
			
			// ejecutar el insert
			
			pstm.executeUpdate();
			
			// insertar el nuevo valor de secuencia
			
			pstm = con.prepareStatement(SQLUpdateSequence);
			pstm.setInt(1, numSeq);
			pstm.executeUpdate();
			
			// finalizar transaccion con exito
			con.commit();
			
			// cargar el valor del ID en el objeto a devolver 
			p.setID(numSeq);

		} catch (Exception e) {
			e.printStackTrace();
			try {
				// si error, rollback
				con.rollback();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return p;

	}

}
