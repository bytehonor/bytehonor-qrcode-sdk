package com.bytehonor.sdk.define.bytehonor.util;

import java.util.Objects;

import com.bytehonor.sdk.define.bytehonor.constant.RedisConstants;

/**
 * @author lijianqiang
 *
 */
public class RedisHelper {

	public static String formatKeyName(String rawKeyName) {
	    Objects.requireNonNull(rawKeyName, "rawKeyName");
		if (rawKeyName.startsWith(RedisConstants.PROJECT_PREFIX) == false) {
			String newKeyName = new StringBuilder(RedisConstants.PROJECT_PREFIX).append(rawKeyName).toString();
			return newKeyName;
		}
		return rawKeyName;
	}
}
