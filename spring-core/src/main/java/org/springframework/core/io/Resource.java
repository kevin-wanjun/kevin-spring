package org.springframework.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import org.springframework.lang.Nullable;

/**
 * @author Juergen Hoeller
 *
 * 在Java中，将不同来源的资源抽象成URL，通过注册不同的handler(URLStreamHandler)来处理不同来源的逻辑 。
 * 一般handler使用不同的前缀（协议，Protocol)来识别，如file,jar,http:等。
 * 然而URL没有默认定义相对的Classpath或ServletContext等资源的handler。
 * 虽然可以注册自己的URLStreamHandler来解析特定的URL前缀，比如classpath.然而这个很麻烦，
 * 因而spring实现了自己的抽象结构:Resource接口来封装底层资源。
 *
 */
public interface Resource extends InputStreamSource {

	/**
	 * 确定此资源是否实际以物理形式存在
	 * @return  true 存在 false 不存在
	 */
	boolean exists();

	/**
	 *
	 * 是否可以通过读取此资源的内容
	 * @return
	 * {@code true} 请注意，尝试时实际内容读取可能仍然失败。
	 * {@code false}的值是明确的指示无法读取资源内容。
	 * @see #getInputStream()
	 */
	default boolean isReadable() {
		return true;
	}

	/**
	 *
	 * 指示此资源是否表示具有开放流的句柄。
	 * @return
	 *  @{code} 则无法多次读取InputStream，必须阅读并关闭以避免资源泄漏。
	 *  @{code} 对于典型的资源描述符
	 */
	default boolean isOpen() {
		return false;
	}

	/**
	 * 确定此资源是否表示文件系统中的文件。
	 * @return
	 *  @{code} 表示是(但不保证) {@link #getFile()} 调用将成功
	 *  @{code} 默认情况下为false
	 * @see #getFile()
	 */
	default boolean isFile() {
		return false;
	}

	/**
	 * 返回此资源的URL句柄
	 * @return {@link java.net.URL}
	 * @throws IOException 如果资源无法解析为URL 即，如果资源不可用作描述符
	 */
	URL getURL() throws IOException;

	/**
	 * 返回此资源的URI句柄。
	 * @return {@link java.net.URI}
	 * @throws IOException 如果资源无法解析为URI 即，如果资源不可用作描述符
	 */
	URI getURI() throws IOException;

	/**
	 * 返回此资源的File句柄。
	 * @return {@link java.io.File}
	 * @throws java.io.FileNotFoundException 如果资源无法解析为绝对文件路径，即资源在文件系统中不可用
	 * @throws IOException 在一般读取失败的情况下
	 * @see #getInputStream()
	 */
	File getFile() throws IOException;

	/**
	 * Return a {@link ReadableByteChannel}.
	 * <p>It is expected that each call creates a <i>fresh</i> channel.
	 * <p>The default implementation returns {@link Channels#newChannel(InputStream)}
	 * with the result of {@link #getInputStream()}.
	 * @return the byte channel for the underlying resource (must not be {@code null})
	 * @throws java.io.FileNotFoundException if the underlying resource doesn't exist
	 * @throws IOException if the content channel could not be opened
	 * @since 5.0
	 * @see #getInputStream()
	 */
	default ReadableByteChannel readableChannel() throws IOException {
		return Channels.newChannel(getInputStream());
	}

	/**
	 * 确定此资源的内容长度。
	 * @return 长度
	 * @throws IOException
	 */
	long contentLength() throws IOException;

	/**
	 * 确定此资源的上次修改时间戳。
	 * @return 时间戳
	 * @throws IOException
	 */
	long lastModified() throws IOException;

	/**
	 * 依据当前资源创建一个相对的资源，并返回资源对象
	 * @param relativePath 创建相对资源的路径
	 * @return 返回创建的资源
	 * @throws IOException
	 */
	Resource createRelative(String relativePath) throws IOException;

	/**
	 * 返回资源的文件名
	 * @return 资源名称
	 */
	@Nullable
	String getFilename();

	/**
	 * 返回资源的描述信息
	 * @return 资源的描述信息
	 */
	String getDescription();

}
