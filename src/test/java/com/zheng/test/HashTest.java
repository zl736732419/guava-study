package com.zheng.test;

import java.util.List;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.hash.PrimitiveSink;
import com.zheng.domain.User;

public class HashTest {

	@SuppressWarnings("serial")
	@Test
	public void simple() {
		
		HashFunction hf = Hashing.md5();
		HashCode hc = hf.newHasher().putObject(new User(1L, "zhangsan", "123456"), new Funnel<User>() {
			public void funnel(User from, PrimitiveSink into) {
				into.putLong(from.getId())
					.putString(from.getName(), Charsets.UTF_8)
					.putString(from.getPassword(), Charsets.UTF_8);
			}
		}).hash();
		
		long code = hc.asLong();
		System.out.println(code);
	}
	
	@SuppressWarnings("serial")
	@Test
	public void bloomFilter() {
		BloomFilter<User> filter = BloomFilter.create(new Funnel<User>() {
			public void funnel(User from, PrimitiveSink into) {
				into.putLong(from.getId())
					.putString(from.getName(), Charsets.UTF_8)
					.putString(from.getPassword(), Charsets.UTF_8);
			}
		}, 500, 0.01);
		
		List<User> users = Lists.newArrayList();
		for(long i = 0; i < 5; i++) {
			User user = new User(i, "name" + i, "password" + i);
			users.add(user);
		}
		
		for(User user : users) {
			filter.put(user);
		}
		
		User user = users.get(0);
		user.setName("zl");
		boolean status = filter.mightContain(user);
		System.out.println(status);
		
	}
	
}
