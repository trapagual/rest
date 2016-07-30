package es.trapasoft.dao;

public class DAOConfigurationException extends RuntimeException {

	/**
	 * Esta clase representa una excepcion en la configuracion del DAO que no puede resolverse
	 * en tiempo de ejecucion, tal como un recurso que no esta en el classpath, una propiedad
	 * que no esta en el fichero, etc.
	 *
	 * @author BalusC
	 * @link http://balusc.blogspot.com/2008/07/dao-tutorial-data-layer.html
	 */
	    // Constantes ----------------------------------------------------------------------------------

	    private static final long serialVersionUID = 1L;

	    // Constructores -------------------------------------------------------------------------------

	    /**
	     * Construye una excepcion con el detalle que se le pasa
	     * @param message El mensaje de detalle 
	     */
	    public DAOConfigurationException(String message) {
	        super(message);
	    }

	    /**
	     * Construye una excepcion con causa raiz
	     * @param cause La causa raiz de la excepcion
	     */
	    public DAOConfigurationException(Throwable cause) {
	        super(cause);
	    }

	    /**
	     * Construye una excepcion con mensaje y causa
	     * @param message El mensaje de detalle 
	     * @param cause La causa raiz de la excepcion
	     */
	    public DAOConfigurationException(String message, Throwable cause) {
	        super(message, cause);
	    }

}
