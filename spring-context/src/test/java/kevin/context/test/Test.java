package kevin.context.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/*******************************************************************************
 * @author wj
 * @date 2018-12-14
 * @description ss
 ******************************************************************************/
public class Test {

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
        URL url= new File("E:\\office\\util\\maven\\repository\\org\\apache\\commons\\commons-lang3\\3.7\\commons-lang3-3.7.jar").toURL();
        URL[] urls1 = new URL[]{url};
        ClassLoader classLoader1 = new URLClassLoader(urls1);
        Class c1 = classLoader1.loadClass("org.apache.commons.lang3.StringUtils");


        File f = new File("E:\\office\\util\\maven\\repository\\org\\apache\\commons\\commons-lang3\\3.7\\commons-lang3-3.7.jar");
        URL[] urls2 = new URL[]{ url};
        URLClassLoader classLoader2 = new URLClassLoader(urls2);
        Class c2 = classLoader2.loadClass("org.apache.commons.lang3.StringUtils");



        Class c3 = classLoader1.loadClass("org.apache.commons.lang3.StringUtils");

        System.out.println(c1 == c2);
        System.out.println(c1 == c3);

        System.out.println(c1.hashCode());
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());

    }
}
