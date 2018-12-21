/*
 * Copyright 2002-2017 the original author or authors.
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

package org.springframework.context.support;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;
import org.springframework.lang.Nullable;

/**
 * Base class for {@link org.springframework.context.ApplicationContext}
 * implementations which are supposed to support multiple calls to {@link #refresh()},
 * creating a new internal bean factory instance every time.
 * Typically (but not necessarily), such a context will be driven by
 * a set of config locations to load bean definitions from.
 *
 * <p>The only method to be implemented by subclasses is {@link #loadBeanDefinitions},
 * which gets invoked on each refresh. A concrete implementation is supposed to load
 * bean definitions into the given
 * {@link org.springframework.beans.factory.support.DefaultListableBeanFactory},
 * typically delegating to one or more specific bean definition readers.
 *
 * <p><b>Note that there is a similar base class for WebApplicationContexts.</b>
 * {@link org.springframework.web.context.support.AbstractRefreshableWebApplicationContext}
 * provides the same subclassing strategy, but additionally pre-implements
 * all context functionality for web environments. There is also a
 * pre-defined way to receive config locations for a web context.
 *
 * <p>Concrete standalone subclasses of this base class, reading in a
 * specific bean definition format, are {@link ClassPathXmlApplicationContext}
 * and {@link FileSystemXmlApplicationContext}, which both derive from the
 * common {@link AbstractXmlApplicationContext} base class;
 * {@link org.springframework.context.annotation.AnnotationConfigApplicationContext}
 * supports {@code @Configuration}-annotated classes as a source of bean definitions.
 *
 * @author Juergen Hoeller
 * @author Chris Beams
 * @since 1.1.3
 * @see #loadBeanDefinitions
 * @see org.springframework.beans.factory.support.DefaultListableBeanFactory
 * @see org.springframework.web.context.support.AbstractRefreshableWebApplicationContext
 * @see AbstractXmlApplicationContext
 * @see ClassPathXmlApplicationContext
 * @see FileSystemXmlApplicationContext
 * @see org.springframework.context.annotation.AnnotationConfigApplicationContext
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

	@Nullable
	private Boolean allowBeanDefinitionOverriding;

	@Nullable
	private Boolean allowCircularReferences;

	/** Bean factory for this context */
	@Nullable
	private DefaultListableBeanFactory beanFactory;

	/** Synchronization monitor for the internal BeanFactory */
	private final Object beanFactoryMonitor = new Object();


	/**
	 * Create a new AbstractRefreshableApplicationContext with no parent.
	 */
	public AbstractRefreshableApplicationContext() {
	}

	/**
	 * Create a new AbstractRefreshableApplicationContext with the given parent context.
	 * @param parent the parent context
	 */
	public AbstractRefreshableApplicationContext(@Nullable ApplicationContext parent) {
		super(parent);
	}


	/**
	 * Set whether it should be allowed to override bean definitions by registering
	 * a different definition with the same name, automatically replacing the former.
	 * If not, an exception will be thrown. Default is "true".
	 * @see org.springframework.beans.factory.support.DefaultListableBeanFactory#setAllowBeanDefinitionOverriding
	 */
	public void setAllowBeanDefinitionOverriding(boolean allowBeanDefinitionOverriding) {
		this.allowBeanDefinitionOverriding = allowBeanDefinitionOverriding;
	}

	/**
	 * Set whether to allow circular references between beans - and automatically
	 * try to resolve them.
	 * <p>Default is "true". Turn this off to throw an exception when encountering
	 * a circular reference, disallowing them completely.
	 * @see org.springframework.beans.factory.support.DefaultListableBeanFactory#setAllowCircularReferences
	 */
	public void setAllowCircularReferences(boolean allowCircularReferences) {
		this.allowCircularReferences = allowCircularReferences;
	}


	/**
	 * 在这个方法中创建了 BeanFactory ,再穿件Ioc容器前，如果已经有容器存在，那么需要把已有的容器销毁和关闭，
	 * 保证在refresh()以后使用的实行建立起来的Ioc容器。
	 * 这么看来，这个refresh非常像重启动容器，在建立好当前的Ioc容器以后，
	 * 开始对容器的初始化过程，比如BeanDefinition 的载入
	 *
	 * 1.创建DefaultListableBeanFactory
	 * 2.指定序列化ID
	 * 3.定制BeanFactory
	 * 4.加载BeanDefinition
	 * 5.使用全局变量几率BeanFactory类实例
	 *
	 */
	@Override
	protected final void refreshBeanFactory() throws BeansException {
		// 这里判断，如果已经建立了 BeanFactory，则销毁并关闭该 BeanFactory
		if (hasBeanFactory()) {
			destroyBeans();
			//销毁该BeanFactory
			closeBeanFactory();
		}
		//这里是创建并设置持有的 DefaultListableBeanFactory 的地方同时调用 loadBeanDefinitions
		//在载入 BeanDefinition的信息
		try {
			//创建Ioc容器，这里使用的是 DefaultListableBeanFactory
			DefaultListableBeanFactory beanFactory = createBeanFactory();
			//为了序列化指定id,如果需要的话，让这个BeanFactory从id反序列化到BeanFactory对象
			beanFactory.setSerializationId(getId());
			//定制 beanFactory，设置相关属性，包括是否允许覆盖同名称的不同定义的对象以及循环依赖
			customizeBeanFactory(beanFactory);
			/**
			 *
			 * 启动对 BeanDefinition 的载入,初始化 DocumentReader,并进行 XML 文件读取及解析
			 * @see org.springframework.context.support.AbstractXmlApplicationContext#loadBeanDefinitions(DefaultListableBeanFactory)
			 * 同时会对 <context:component-scan></context:component-scan> 等等自定义标签完成扫描，实现注解
			 * BeanDefinitions 的注入封装成 BeanDefinitionHolder 对象
			 *
			 */
			loadBeanDefinitions(beanFactory);
			synchronized (this.beanFactoryMonitor) {
				this.beanFactory = beanFactory;
			}
		}
		catch (IOException ex) {
			throw new ApplicationContextException("I/O error parsing bean definition source for " + getDisplayName(), ex);
		}
	}

	@Override
	protected void cancelRefresh(BeansException ex) {
		synchronized (this.beanFactoryMonitor) {
			if (this.beanFactory != null)
				this.beanFactory.setSerializationId(null);
		}
		super.cancelRefresh(ex);
	}

	@Override
	protected final void closeBeanFactory() {
		synchronized (this.beanFactoryMonitor) {
			if (this.beanFactory != null) {
				this.beanFactory.setSerializationId(null);
				this.beanFactory = null;
			}
		}
	}

	/**
	 * 判断 beanFactory 是否被创建
	 */
	protected final boolean hasBeanFactory() {
		synchronized (this.beanFactoryMonitor) {
			return (this.beanFactory != null);
		}
	}

	@Override
	public final ConfigurableListableBeanFactory getBeanFactory() {
		synchronized (this.beanFactoryMonitor) {
			if (this.beanFactory == null) {
				throw new IllegalStateException("BeanFactory not initialized or already closed - " +
						"call 'refresh' before accessing beans via the ApplicationContext");
			}
			return this.beanFactory;
		}
	}

	/**
	 * Overridden to turn it into a no-op: With AbstractRefreshableApplicationContext,
	 * {@link #getBeanFactory()} serves a strong assertion for an active context anyway.
	 */
	@Override
	protected void assertBeanFactoryActive() {
	}

	/**
	 * 这就是在上下文中创建DefaultListableBeanFactory 的地方，而getInternalParentBeanFactory()的具体实现
	 * 可以参考 AbstractApplicationContext 中的实现，会根据容器已有的Ioc容器的信息来生成
	 * DefaultListableBeanFactory 的双亲Ioc容器
	 *
	 * Create an internal bean factory for this context.
	 * Called for each {@link #refresh()} attempt.
	 * <p>The default implementation creates a
	 * {@link org.springframework.beans.factory.support.DefaultListableBeanFactory}
	 * with the {@linkplain #getInternalParentBeanFactory() internal bean factory} of this
	 * context's parent as parent bean factory. Can be overridden in subclasses,
	 * for example to customize DefaultListableBeanFactory's settings.
	 * @return the bean factory for this context
	 * @see org.springframework.beans.factory.support.DefaultListableBeanFactory#setAllowBeanDefinitionOverriding
	 * @see org.springframework.beans.factory.support.DefaultListableBeanFactory#setAllowEagerClassLoading
	 * @see org.springframework.beans.factory.support.DefaultListableBeanFactory#setAllowCircularReferences
	 * @see org.springframework.beans.factory.support.DefaultListableBeanFactory#setAllowRawInjectionDespiteWrapping
	 */
	protected DefaultListableBeanFactory createBeanFactory() {
		//创建容器并指定父容器，父容器可以是null
		return new DefaultListableBeanFactory(getInternalParentBeanFactory());
	}

	/**
	 * 对于允许覆盖和允许依赖的设置这里只是判断了是否为空，如果不为空要进行设置，但是没有看到在哪里进行设置
	 * 这里使用 ClassPathXmlApplicationContext 子类重写 当前方法即可
	 * {@code
	 * 	@Override
	 * 	protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
	 * 		super.setAllowBeanDefinitionOverriding(false);
	 * 		super.setAllowCircularReferences(false);
	 * 		super.customizeBeanFactory(beanFactory);
	 * 	}
	 * }
	 *
	 *
	 * Customize the internal bean factory used by this context.
	 * Called for each {@link #refresh()} attempt.
	 * <p>The default implementation applies this context's
	 * {@linkplain #setAllowBeanDefinitionOverriding "allowBeanDefinitionOverriding"}
	 * and {@linkplain #setAllowCircularReferences "allowCircularReferences"} settings,
	 * if specified. Can be overridden in subclasses to customize any of
	 * {@link DefaultListableBeanFactory}'s settings.
	 * @param beanFactory the newly created bean factory for this context
	 * @see DefaultListableBeanFactory#setAllowBeanDefinitionOverriding
	 * @see DefaultListableBeanFactory#setAllowCircularReferences
	 * @see DefaultListableBeanFactory#setAllowRawInjectionDespiteWrapping
	 * @see DefaultListableBeanFactory#setAllowEagerClassLoading
	 */
	protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
		//如果属性 allowBeanDefinitionOverriding 不为空，设置给 beanFactory 对象相应属性
		//此属性的含义：是否允许覆盖同名称的不同定义对象
		if (this.allowBeanDefinitionOverriding != null) {
			beanFactory.setAllowBeanDefinitionOverriding(this.allowBeanDefinitionOverriding);
		}
		//如果属性 allowCircularReferences 不为空，设置给 beanFactory 对象相应属性
		//此属性的含义：是否允许 bean 之间存在循环依赖
		if (this.allowCircularReferences != null) {
			beanFactory.setAllowCircularReferences(this.allowCircularReferences);
		}
	}

	/**
	 * 这里是使用BeanDefinitionReader 载入Bean定义的地方，
	 * 因为允许有多种载入方式，虽然用的最多的是 xml定义的形式
	 * 这里用火一个抽象函数把具体的实现委托给子类来完成
	 *
	 * Load bean definitions into the given bean factory, typically through
	 * delegating to one or more bean definition readers.
	 * @param beanFactory the bean factory to load bean definitions into
	 * @throws BeansException if parsing of the bean definitions failed
	 * @throws IOException if loading of bean definition files failed
	 * @see org.springframework.beans.factory.support.PropertiesBeanDefinitionReader
	 * @see org.springframework.beans.factory.xml.XmlBeanDefinitionReader
	 */
	protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory)
			throws BeansException, IOException;

}
