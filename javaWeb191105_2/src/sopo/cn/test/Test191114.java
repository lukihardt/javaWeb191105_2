package sopo.cn.test;

import java.util.UUID;

public class Test191114 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(System.getProperty("java.io.tmpdir"));
		System.out.println(UUID.randomUUID());
		
		String a = "ask;dlfja;ls;j";
		
		System.out.println(a.lastIndexOf(";"));
		System.out.println(a.substring(3));
	}

}
