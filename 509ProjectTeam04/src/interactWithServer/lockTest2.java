package interactWithServer;
/**
 * Test_lock.java
 * the lockTest is based on the two different users, the lockTest2 is based on the same user
 * @author Team04
 */
public class lockTest2 {
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
	 * Case 2: Then the same user repeat locking the database within two minutes
	 */
	public static void dolockTest2() {
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
	
	public static void main(String[] args) {
		lockTest.dolockTest1();
		lockTest.dolockTest2();
	}

}
