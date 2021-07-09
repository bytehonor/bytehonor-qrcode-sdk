package com.bytehonor.sdk.define.bytehonor.result;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JsonResponseTest {

	@Test
	public void testGetCode() {
		JsonResponse<String> res = new JsonResponse<String>();
		int code = 1;
		res.setCode(code);
		assertTrue("testGetCode should return 'true'", res.getCode() == code);
	}

	@Test
	public void testGetMessage() {
		JsonResponse<String> res = new JsonResponse<String>();
		String msg = "test";
		res.setMessage(msg);
		assertTrue("testGetMessage should return 'true'", msg.equals(res.getMessage()));
	}

}
