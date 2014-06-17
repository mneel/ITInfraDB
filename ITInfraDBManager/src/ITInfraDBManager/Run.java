/**
 * 
 */
package ITInfraDBManager;

/**
 * @author mike.neel
 * @Date 06.13.2014
 *Version 1.0
 */
public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			ITInfraDBManager_Main window = new ITInfraDBManager_Main();
			window.frmServersdb.setVisible(true);
		}catch (Exception e){
			e.printStackTrace();
		}

	}

}
