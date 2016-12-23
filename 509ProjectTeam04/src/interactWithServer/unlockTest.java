package interactWithServer;
/**
 * Test_unlock.java
 * @author Team04
 */

public class unlockTest {
	/**
	 * Test Search.doUnlock(). 
	 * Case 1: At first one user try to lock the database 
	 */
	
	public static void doUnlockTest1() {
		lock lock = new lock();
		String ticketAgency = "Team04";
		boolean result = lock.doLock(ticketAgency);
		System.out.println("Sever situation:"+result);
		if(result==true)
		   System.out.println("Sever has been Locked!");
		else
	      System.out.println("Sever is UnLocking!");
	}
	/**
	 * Test Search.doLock(). 
	 * Case 2: Then other users try to unlock the database within two minutes
	 */
	
	public static void doUnlockTest2() {
		unlock unlock = new unlock();
		String ticketAgency = "Team04";
		boolean result = unlock.doUnlock(ticketAgency);
		boolean expected=true; 
		System.out.println("expected result:"+expected);
		System.out.println("actual result:"+result);
		if(expected == result)
		   System.out.println("test success!");
		else
		   System.out.println("test fail!");
	}

	
	public static void main(String[] args) {
		unlockTest.doUnlockTest2();
	}

}
