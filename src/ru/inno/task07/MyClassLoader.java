package ru.inno.task07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Загрузчик классов, для получения экземпляра
 * созданного в рантайме класса
 *
 * @author Alexey Balakin
 */
public class MyClassLoader extends ClassLoader {

    /**
     * Загружает класс с указанными именем
     *
     * @return загруженный класс
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if ((RuntimeCompiler.PACKAGE + "." + RuntimeCompiler.SOME_CLASS).equals(name)) {
            return findClass(name);
        }
        return super.loadClass(name);
    }

    /**
     * Загружает класс с полученным именем из скомпиллированного файла
     *
     * @return загруженный класс
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if ((RuntimeCompiler.PACKAGE + "." + RuntimeCompiler.SOME_CLASS).equals(name)) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get("./" + RuntimeCompiler.SOME_CLASS + ".class"));
                return defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.findClass(name);
    }
}
