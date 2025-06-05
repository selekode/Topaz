package com.selekode.topaz;

import java.io.IOException;
import java.util.Scanner;

public class KillPortIfOccupied {
	public static void killPortIfOccupied(int port) {
	    try {
	        Process process = Runtime.getRuntime().exec("cmd /c netstat -ano | find \":" + port + "\"");
	        Scanner sc = new Scanner(process.getInputStream());
	        while (sc.hasNextLine()) {
	            String line = sc.nextLine().trim();
	            if (!line.isEmpty()) {
	                String[] parts = line.split("\\s+");
	                if (parts.length >= 5) {
	                    String pid = parts[4];
	                    Runtime.getRuntime().exec("taskkill /PID " + pid + " /F");
	                    System.out.println("Killed process using port " + port);
	                }
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
