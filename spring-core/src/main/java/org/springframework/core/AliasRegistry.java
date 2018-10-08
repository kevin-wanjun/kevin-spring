/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.core;

/**
 * 用于管理别名的接口，此接口为最顶层接口
 * {@link org.springframework.beans.factory.support.BeanDefinitionRegistry}.
 *
 * @author Juergen Hoeller
 * @since 2.5.2
 */
public interface AliasRegistry {

	/**
	 * 给定一个名称，注册一个别名。
	 * @param name 规范名称
	 * @param alias 要注册的别名
	 * @throws IllegalStateException 如果别名已被使用
	 * 不可重写
	 */
	void registerAlias(String name, String alias);

	/**
	 * 从注册表中删除指定别名。
	 * @param alias 要删除的别名
	 * @throws IllegalStateException 如果没有发现指定的别名
	 */
	void removeAlias(String alias);

	/**
	 * 判断指定名称是否为别名
	 * @param name 要检测的名称
	 * @return true 存在 false 不存在
	 */
	boolean isAlias(String name);

	/**
	 * 返回指定名称的所有别名
	 * @param name 检查别名的名称
	 * @return 别名数组，如果没有则是空数组
	 */
	String[] getAliases(String name);

}
