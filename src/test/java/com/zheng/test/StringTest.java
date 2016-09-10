package com.zheng.test;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class StringTest {

	@Test
	public void joiner() {
		Joiner joiner = Joiner.on(",").skipNulls();
		String[] arr = new String[] { "marry", null, "", "tom", null };
		String str = joiner.join(arr);
		System.out.println(str);
		str = joiner.join(Lists.newArrayList(1, 2, 3, null, 4));
		System.out.println(str);
		// stringutils将会把null用''代替
		str = StringUtils.join(arr, ',');
		System.out.println(str);
	}

	@Test
	public void splitter() {
		String str = "hello,, world, ";
		long a = System.currentTimeMillis();
		List<String> list = Splitter.on(",").omitEmptyStrings()
				.trimResults()
				.splitToList(str);
		for (String s : list) {
			System.out.println(s);
		}
		System.out.println(System.currentTimeMillis() - a);
		long b = System.currentTimeMillis();
		String[] arr = StringUtils.split(str, ",");
		for (String s : arr) {
			System.out.println(s);
		}
		System.out.println(System.currentTimeMillis() - b);
	}
	
	@Test
	public void charMatcher() {
		String str = " asdfjs    kdl22238,sad fj18";
		String s = CharMatcher.JAVA_DIGIT.retainFrom(str);
//		System.out.println(s);
//		s = CharMatcher.JAVA_DIGIT.removeFrom(str);
//		System.out.println(s);
//		
//		s = CharMatcher.JAVA_LOWER_CASE.retainFrom(str);
//		System.out.println(s);
//		//将空格替换成2并合并多个空格为一个空格
//		s = CharMatcher.WHITESPACE.trimAndCollapseFrom(str, '2');
//		System.out.println(s);
//		
//		s = CharMatcher.JAVA_LOWER_CASE.trimAndCollapseFrom(str, '2');
//		System.out.println(s);
//		s = CharMatcher.JAVA_LOWER_CASE.replaceFrom(str, '1');
//		System.out.println(s);
		
		//原样输出
		s = CharMatcher.ANY.trimAndCollapseFrom(str, '3');
		System.out.println(s);
	}

}
