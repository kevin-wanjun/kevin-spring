package org.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Juergen Hoeller
 *
 * 作为 java InputStream 的对象的简单接口 {@link java.io.InputStream}
 *
 * @see java.io.InputStream
 * @see Resource
 * @see InputStreamResource
 * @see ByteArrayResource
 */
public interface InputStreamSource {

	/**
	 * {@link InputStream}
	 * @return 底层资源的输入流（不能是{@code null}）
	 * 每次调用这个方法都会得到一个新的流，这是非常重要的，当你考虑某个API时。
	 * 比如JavaMail，它在创建邮件附件的时候需要读取多次读取流。对于这样的一个用例，
	 * 它要求getInputStream()每次返回一个新的流对象，输入流的底层资源不能为空，如果不能正确的获得输入流，
	 * 将会跑出IOException
	 *
	 * @throws java.io.FileNotFoundException 资源不存在
	 * @throws IOException 如果内容流无法打开
	 */
	InputStream getInputStream() throws IOException;

}
