package com.daug.dao;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class UtilTest {

	@Test
	public void test() {

		Format format = new SimpleDateFormat("yyyyMMddhhMMss");
        System.out.println(format.format(new Date()));
	}

}
