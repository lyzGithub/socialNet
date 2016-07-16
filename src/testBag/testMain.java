package testBag;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class testMain {
	public static  void main() throws FileNotFoundException{
		// conclude infor fron data
		System.out.println("Please wait, laoding data to compute num of ver!!");
		File netDataIn = new File("twitterData\\follower_gcc.anony.dat");//("twitterData\\follower_gcc.anony.dat");
		HashMap<String,String> netVer = new HashMap<String,String>();
		if(netDataIn.exists() ){ 
			Scanner netVerInput = new Scanner(netDataIn);
			while(netVerInput.hasNextLine()){
				String s = netVerInput.nextLine();
				String []ss = s.split(" ");
				netVer.put(ss[0], ss[0]);
				netVer.put(ss[1], ss[1]);
				
			}
			netVerInput.close();
			System.out.println("all vers num: " +  netVer.size());
		}
		else{
			System.out.println("net data file path wrong !" +netDataIn.getAbsolutePath());
		}
	}
}
