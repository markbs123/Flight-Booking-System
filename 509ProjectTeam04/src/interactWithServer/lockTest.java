package interactWithServer;
/**
 * Test_lock.java
 * @author Team04
 */
public class lockTest {
	/**
	 * Test Search.doLock(). 
	 * Case 1: At first one user try to lock the database 
	 */
	public static void dolockTest1() {
		lock lock = new lock();
		String ticketAgency = "Team04";
		boolean result = lock.doLock(ticketAgency);
		boolean expected=true; 
		System.out.println("expected result:"+expected);
		System.out.println("actual result:"+result);
		if(expected == result)
		   System.out.println("test success!");
		else
		   System.out.println("test fail!");
	}
	/**
	 * Test Search.doLock(). 
	 * Case 2: Then other users try to lock the database again within two minutes
	 */
	public static void dolockTest2() {
		lock lock = new lock();
		String ticketAgency = "Team03";
		boolean result = lock.doLock(ticketAgency);
		boolean expected=false; 
		System.out.println("expected result:"+expected);
		System.out.println("actual result:"+result);
		if(expected == result)
		   System.out.println("test success!");
		else
	      System.out.println("test fail!");
	}
	
	public static void main(String[] args) {
		lockTest.dolockTest1();
        lockTest.dolockTest2();
	}

}