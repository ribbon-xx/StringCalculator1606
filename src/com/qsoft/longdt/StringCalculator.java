package com.qsoft.longdt;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

	public static int add(String input) {
		if (input.isEmpty()) {
			return 0;
		} else if (input.length() == 1) {
			return toInt(input);
		} else if (!input.startsWith("//")) {
			return methodSum(input);
		} else {
			String tmp = replaceDelim(input);
			return methodSum(tmp);
		}
	}

	private static int toInt(String input) {
		return Integer.parseInt(input);
	}

	private static int methodSum(String input) {
		ArrayList<Integer> ints = new ArrayList<Integer>();
		ArrayList<Integer> negativeInts = new ArrayList<Integer>();
		Pattern p = Pattern.compile("-?\\d+");
		Matcher m = p.matcher(input);
		while (m.find()) {
			int number = toInt(m.group());
			if (number < 0) {
				negativeInts.add(number);
			} else {
				ints.add(number);
			}
		}
		StringBuilder negativeStr = new StringBuilder();
		if (negativeInts.size() > 0) {
			for (int i = 0; i < negativeInts.size(); i++) {
				if (i < negativeInts.size() - 1) {
					negativeStr.append(negativeInts.get(i) + ",");
				} else {
					negativeStr.append(negativeInts.get(i));
				}
			}
			throw new NumberFormatException("negatives not allowed "
					+ negativeStr.toString());
		}

		int sum = 0;
		for (Integer integer : ints) {
			sum += integer;
		}
		return sum;
	}

	private static String replaceDelim(String input) {
		String tmp = input.replace("//", "");
		while (tmp.contains("[")) {
			String delimContent = tmp.substring(tmp.indexOf("[") + 1,
					tmp.indexOf("]"));
			String delimDefine = tmp.substring(tmp.indexOf("["),
					tmp.indexOf("]") + 1);
			tmp = tmp.replaceAll(Pattern.quote(delimDefine), "");
			tmp = tmp.replaceAll(Pattern.quote(delimContent), ",");
		}
		return tmp;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
