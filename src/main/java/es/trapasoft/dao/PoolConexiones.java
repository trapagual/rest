package es.trapasoft.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;








/**
 * Clase para explotar la pool de conexiones del servidor de aplicaciones
 * @author SGEN0290
 *
 */
public final class PoolConexiones {

	
	   /**
	    * El nombre de la pool de conexiones es el mismo para todos los entornos
	    */
	   private static String nombrePool = "jdbc/MySQLDS";
	   
	   /**
	    * Fuente de datos del servidor de aplicaciones
	    */
	   private static DataSource fuenteDatos = null;
	   
	   /**
	    * Indica si esta conectado o no a BBDD
	    */
	   private static boolean conectado = false;

	   /**

	    * Constructor. Conecta con BBDD si no esta conectado
	    * @throws SIAException
	    */
	   public PoolConexiones()  throws DAOConfigurationException {
	      if (!conectado)
	          conecta();
	   }
	 
	   /**
	    * Conecta con el pool de conexiones configurado en el servidor de aplicaciones
	    * 
	    */
	   public static void conecta() throws DAOException {

		      
	         try {
				Context contextoInicial = new InitialContext();
				 Context contexto = (Context) contextoInicial.lookup("java:comp/env");
				 fuenteDatos = (DataSource) contexto.lookup(nombrePool);
				 DatabaseMetaData dmd = fuenteDatos.getConnection().getMetaData();
				 System.out.println("JDBC Driver Name is " + dmd.getDriverName());
				 System.out.println("JDBC Driver Version is " + dmd.getDriverVersion());  
				 conectado = true;
			} catch (NamingException e) {
				e.printStackTrace();
				throw new DAOException("Error NamingException al conectar a la b.d. "+ e.getMessage(), e.getCause());
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("Error SQLException al conectar a la b.d. "+ e.getMessage(), e.getCause());
			}
	   }
	   
	   /**
	    * Obtiene una conexion del pool
	    * @return Conexion a BBDD
	    * @throws SQLException
	    */
	   public synchronized static Connection getConexion() throws SQLException {
	      if (!conectado) {
	          try {
	             conecta();
	          } catch(DAOException e) {
	             throw new SQLException("Error al obtener la conexion " + e.getMessage());
	          }
	      }
	      Connection  con = fuenteDatos.getConnection();
	      
	      return con;
	   }
	   
	   
	   public synchronized static void cierraConexion(Connection con) {
		   try {
			   if (con != null)  con.close();
		   } catch (SQLException e) {
			e.printStackTrace();
		} finally {
   
		}

	   }


}
