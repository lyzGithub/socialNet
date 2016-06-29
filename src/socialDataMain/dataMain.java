package socialDataMain;
import java.io.*;
import java.util.*;
public class dataMain{
	@SuppressWarnings("resource")
	public static void main(String []args) throws FileNotFoundException{
		System.out.println("I back for Java!");
		File netDataIn = new File("twitterData\\follower_gcc.anony.dat");
		if(netDataIn.exists()){
			Scanner netInput = new Scanner(netDataIn);
			while(netInput.hasNextLine()){
				String s = netInput.nextLine();
				System.out.println(s);
			}
		}
		else {
			System.out.println("net data file path wrong !" +netDataIn.getAbsolutePath());
			return;
		}
	}
}