package com.board.web.common.util;

import java.util.Collection;

/**
 * List, Set 과 관련된 유틸
 * @author mcjan
 *
 */
public class CollectionUtil {

	/**
	 * List, Set이 비어있다면 true
	 * @param collection List, Set
	 * @return
	 */
	public static boolean isEmpty(Collection<? extends Object> collection) {
		return collection == null || collection.size() == 0;
	}

	/**
	 * List, Set이 비어있지않다면 true
	 * @param collection List, Set
	 * @return
	 */
	public static boolean isNotEmpty(Collection<? extends Object> collection) {
		return collection != null && collection.size() > 0;
	}
	
}
