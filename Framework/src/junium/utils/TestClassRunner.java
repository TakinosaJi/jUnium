package junium.utils;



import junium.selenium.SeleniumBase;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.LinkedList;
import java.util.List;

public class TestClassRunner {
    public static Class[] GetClassesInPackages(String... packages){
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        FilterBuilder filters = new FilterBuilder();
        for (String p : packages){
            filters.include(FilterBuilder.prefix(p));
        }

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(filters));

        return reflections.getSubTypesOf(SeleniumBase.class).toArray(new Class[0]);
    }
}
