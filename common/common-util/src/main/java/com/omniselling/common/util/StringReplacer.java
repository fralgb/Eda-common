package com.omniselling.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * @author xslong 
 * @version 创建时间：Jan 6, 2017 5:24:09 PM 
 * 
*/

public class StringReplacer
{

	public static interface StringReplacerCallback
	{
		String replace(Matcher match);
	}

	public static String replace(String input, Pattern regex, StringReplacerCallback callback)
	{
		StringBuffer resultString = new StringBuffer();
		Matcher regexMatcher = regex.matcher(input);
		while (regexMatcher.find())
		{
			regexMatcher.appendReplacement(resultString, callback.replace(regexMatcher));
		}
		regexMatcher.appendTail(resultString);

		return resultString.toString();
	}
}
