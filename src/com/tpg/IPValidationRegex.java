package com.tpg;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPValidationRegex {
	private static final String IPADDRESS_PATTERN = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
			
	public static List<String> filterInvalidIPs(File file) throws IOException {
		List<String> invalidIPs = new ArrayList<String>();
		
		Pattern ipAddressPattern = Pattern.compile(IPADDRESS_PATTERN);
		Scanner scanner = new Scanner(file);
	    while (scanner.hasNextLine()) {
	        String ip = scanner.nextLine();
	        Matcher matcher = ipAddressPattern.matcher(ip);
	        if (!matcher.matches())
	        	invalidIPs.add(ip);
	    }
	    scanner.close();
	    
		return invalidIPs;
	}
}
