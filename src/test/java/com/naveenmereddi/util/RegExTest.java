package com.naveenmereddi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegExTest {

	String taskStatus="!Complete";
	String NotInProgress = "!In Progress";

	@Test
	public void testRegEx1() {
		Pattern pattern = Pattern.compile(Constants.REGEX);
		if(taskStatus != null) {
			Matcher matcher = pattern.matcher(taskStatus);
			while(matcher.find()) {
				String g1 =matcher.group(1);
				String g2 =matcher.group(2);
				System.out.println(g1 + "," + g2);
			}
		}
	}

	@Test
	public void testRegEx2() {
		Pattern pattern = Pattern.compile(Constants.REGEX);
		if(NotInProgress != null) {
			Matcher matcher = pattern.matcher(NotInProgress);
			while(matcher.find()) {
				String g1 =matcher.group(1);
				String g2 =matcher.group(2);
				System.out.println(g1 + "," + g2);
			}
		}
	}


}
