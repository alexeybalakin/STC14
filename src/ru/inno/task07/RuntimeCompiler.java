package ru.inno.task07;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Считывает с консоли код метода doWork() и в рантайме создает
 * и компилирует класс реализующий интерфейс Worker
 *
 * @author Alexey Balakin
 */
public class RuntimeCompiler {
    static final String SOME_CLASS = "SomeClass";
    static final String PACKAGE = "ru.inno.task07";

    /**
     * Возращает экземпляр объекта для созданного в рантайме класса,
     * реализующего интерфейс Worker
     *
     * @return объект созданного в рантайме класса
     */
    public Worker getSomeWorker() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        String someClass = "package " + PACKAGE + ";" +
                "public class " + SOME_CLASS + " implements Worker {" +
                "  public void doWork() {" +
                implementDoWork() +
                "  }" +
                "}";
        String filename = "./" + SOME_CLASS + ".java";
        // Сохраняем исходный код в файл
        Files.write(Paths.get(filename), someClass.getBytes());
        // Получаем компилятор
        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
        // Указываем имя .java файла
        String[] javacOpts = {filename};
        javac.run(null, null, null, javacOpts);

        ClassLoader cl = new MyClassLoader();
        Class<?> clazz = cl.loadClass(PACKAGE + "." + SOME_CLASS);
        return (Worker) clazz.newInstance();
    }

    /**
     * Считывает с консоли введённый код для метода doWork()
     *
     * @return код реализующий метод doWork()
     */
    private String implementDoWork() {
        StringBuilder result = new StringBuilder();
        String line = "";
        System.out.println("Please input implementation for method doWork()");
        System.out.println("For quit input empty line:");
        Scanner scanner = new Scanner(System.in);
        while (!(line = scanner.nextLine()).isEmpty()) {
            result.append(line);
        }
        return result.toString();
    }
}
