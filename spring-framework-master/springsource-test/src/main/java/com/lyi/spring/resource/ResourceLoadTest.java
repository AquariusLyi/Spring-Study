package com.lyi.spring.resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;

public class ResourceLoadTest {
	/**
	 * 下面示例是演示 DefaultResourceLoader 加载资源的具体策略，代码如下（该示例参考《Spring 揭秘》 P89）：
	 *
	 */
	public static void main(String[] args) {
		ResourceLoader resourceLoader = new DefaultResourceLoader();

		Resource fileResource1 = resourceLoader.getResource("D:/Users/chenming673/Documents/spark.txt");
		System.out.println("fileResource1 is FileSystemResource:" + (fileResource1 instanceof FileSystemResource));

		Resource fileResource2 = resourceLoader.getResource("/Users/chenming673/Documents/spark.txt");
		System.out.println("fileResource2 is ClassPathResource:" + (fileResource2 instanceof ClassPathResource));

		Resource urlResource1 = resourceLoader.getResource("file:/Users/chenming673/Documents/spark.txt");
		System.out.println("urlResource1 is UrlResource:" + (urlResource1 instanceof UrlResource));

		Resource urlResource2 = resourceLoader.getResource("http://www.baidu.com");
		System.out.println("urlResource1 is urlResource:" + (urlResource2 instanceof  UrlResource));
	}
	//其实对于 fileResource1 ，我们更加希望是 FileSystemResource 资源类型。但是，事与愿违，它是 ClassPathResource 类型。
	// 为什么呢？在 DefaultResourceLoader#getResource() 方法的资源加载策略中，我们知道 "D:/Users/chenming673/Documents/spark.txt"
	// 地址，其实在该方法中没有相应的资源类型，那么它就会在抛出 MalformedURLException 异常时，通过 DefaultResourceLoader#getResourceByPath(...) 方法，
	// 构造一个 ClassPathResource 类型的资源。
	//而 urlResource1 和 urlResource2 ，指定有协议前缀的资源路径，则通过 URL 就可以定义，所以返回的都是 UrlResource 类型。
}
