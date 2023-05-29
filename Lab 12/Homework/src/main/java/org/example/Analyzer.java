package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.objectweb.asm.*;

import javax.tools.*;
import java.io.FileInputStream;
import java.lang.reflect.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class Analyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print("Enter the name or the path: ");
            input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            try {
                List<Class<?>> classes = new ArrayList<>();
                File file = new File(input);
                if (file.isDirectory()) {
                    getAllClassesInDirectory(file, classes);
                } else if (input.endsWith(".class")) {
                    Path path = Paths.get(input);
                    byte[] bytes = Files.readAllBytes(path);
                    CustomClassLoader classLoader = new CustomClassLoader();
                    classes.add(classLoader.loadClass(bytes));
                } else if (input.endsWith(".jar")) {
                    getClassesInJarFile(file, classes);
                } else if (input.endsWith(".java")) {
                    compileAndLoadClass(file, classes);

                } else {
                    ClassLoader classLoader = Analyzer.class.getClassLoader();
                    classes.add(classLoader.loadClass(input));
                }

                if (!classes.isEmpty()) {
                    info(classes);

                } else {
                    System.out.println("No classes found.");
                }

            } catch (ClassNotFoundException e) {
                System.out.println("Class not found: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    public static void info(List<Class<?>> classes) throws InvocationTargetException, IllegalAccessException {
        int totalClasses = 0;
        int totalTestMethods = 0;
        int totalTestClasses = 0;
        for (Class<?> clasa : classes) {
            System.out.println();
            System.out.println("\u001B[35m" + clasa.getName() + "\u001B[0m");
            totalClasses++;
            Package classPackage = clasa.getPackage();
            System.out.println("Class: " + clasa.getName());
            System.out.println("\u001B[33mClass Hierarchy:" + "\u001B[0m");
            printClassHierarchy(clasa);
            System.out.println("Package: " + (classPackage != null ? classPackage.getName() : "default"));
            System.out.println("\u001B[31mFields:\u001B[0m");
            Field[] fields = clasa.getDeclaredFields();
            for (Field field : fields) {
                System.out.println("Name: " + field.getName());
                System.out.println("Type: " + field.getType().getSimpleName());
                System.out.println("Modifiers: " + Modifier.toString(field.getModifiers()));
                System.out.println("Annotations: " + Arrays.toString(field.getAnnotations()));
                System.out.println("---");
            }
            System.out.println("---------------------------------------------------");
            System.out.println("\u001B[32mConstructors:\u001B[0m");
            Constructor<?>[] constructors = clasa.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                System.out.println("Name: " + constructor.getName());
                System.out.println("Modifiers: " + Modifier.toString(constructor.getModifiers()));
                System.out.println("Annotations: " + Arrays.toString(constructor.getAnnotations()));
                System.out.println("---");
            }
            System.out.println("---------------------------------------------------");
            List<Method> allMethods = getAllMethods(clasa);
            System.out.println("All methods:");
            for (Method method : allMethods) {
                System.out.println(method.getName());
                System.out.println("\u001B[34mReturn type:\u001B[0m " + method.getReturnType().getSimpleName());
                System.out.println("\u001B[34mModifiers:\u001B[0m " + Modifier.toString(method.getModifiers()));
                System.out.println("\u001B[34mAnnotations:\u001B[0m " + Arrays.toString(method.getAnnotations()));
                System.out.println("---");
            }
            System.out.println("---------------------------------------------------");
            if (clasa.isAnnotationPresent(Test.class) && Modifier.isPublic(clasa.getModifiers())) {
                totalTestClasses++;
                List<Method> testMethods = getTestMethods(clasa);
                totalTestMethods = totalTestMethods + testMethods.size();
                if (!testMethods.isEmpty()) {
                    System.out.println("Test methods:");
                    for (Method method : testMethods) {
                        System.out.println(method.getName());
                        System.out.println("\u001B[34mReturn type:\u001B[0m " + method.getReturnType().getSimpleName());
                        System.out.println("\u001B[34mModifiers:\u001B[0m " + Modifier.toString(method.getModifiers()));
                        System.out.println("\u001B[34mAnnotations:\u001B[0m " + Arrays.toString(method.getAnnotations()));
                        System.out.println("---");

                    }
                } else {
                    System.out.println("No test methods found.");
                }
            } else {
                for (Method method : allMethods) {
                    if (method.isAnnotationPresent(Test.class) && Modifier.isStatic(method.getModifiers())) {
                        System.out.println("Test method: " + method.getName());
                        method.invoke(null);
                    }
                }
            }

        }
        System.out.println("----");
        System.out.println("Statistics:");
        System.out.println("Total classes: " + totalClasses);
        System.out.println("Total test methods: " + totalTestMethods);
        System.out.println("Total test classes: " + totalTestClasses);
        System.out.println("-------------------");
    }

    private static List<Method> getTestMethods(Class<?> clasa) {
        int staticTestMethods = 0;
        int nonStaticTestMethods = 0;
        int goodTestMethods=0;
        int badTestMethods=0;
        List<Method> testMethods = new ArrayList<>();
        Method[] methods = clasa.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                if (Modifier.isStatic(method.getModifiers())) {
                    staticTestMethods++;
                } else {
                    nonStaticTestMethods++;
                }

                try {
                    invokeTestMethod(method);
                    goodTestMethods++;
                } catch (ReflectiveOperationException e) {
                    badTestMethods++;
                    //throw new RuntimeException(e);
                    System.out.println("Error at invoking method.");
                }
                testMethods.add(method);

            }
        }
        System.out.println("-----");
        System.out.println("Total test methods: " + testMethods.size());
        System.out.println("Static test methods: " + staticTestMethods);
        System.out.println("Non-static test methods: " + nonStaticTestMethods);
        System.out.println("Passed test methods: "+ goodTestMethods);
        System.out.println("Failed test methods "+badTestMethods);
        System.out.println("------");
        return testMethods;
    }

    private static List<Method> getAllMethods(Class<?> clasa) {
        List<Method> allMethods = new ArrayList<>();
        Method[] methods = clasa.getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            allMethods.add(method);
        }
        return allMethods;
    }

    private static class CustomClassLoader extends ClassLoader {
        public Class<?> loadClass(byte[] classBytes) throws ClassNotFoundException {
            return defineClass(null, classBytes, 0, classBytes.length);
        }
    }

    private static void getAllClassesInDirectory(File directory, List<Class<?>> classes) throws IOException, ClassNotFoundException {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    getAllClassesInDirectory(file, classes);
                } else if (file.getName().endsWith(".class")) {
                    //byte[] bytes = Files.readAllBytes(file.toPath());
                    byte[] bytes= transformBytecode(file.toPath().toString());
                    CustomClassLoader classLoader = new CustomClassLoader();
                    classes.add(classLoader.loadClass(bytes));
                } else if (file.getName().endsWith(".java")) {
                    compileAndLoadClass(file, classes);
                }
            }
        }
    }

    private static void compileAndLoadClass(File file, List<Class<?>> classes) throws IOException, ClassNotFoundException {
        String className = compileJavaFile(file);
        if (className != null) {
            System.out.println("Class name: " + className);
            //byte[] bytes = Files.readAllBytes(Paths.get(className + ".class"));
            byte[] bytes= transformBytecode(className + ".class");
            CustomClassLoader classLoader = new CustomClassLoader();
            classes.add(classLoader.loadClass(bytes));
        }
    }


    private static String compileJavaFile(File file) throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnosticCollector, null, null);
        String className = null;
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnosticCollector, null, null, compilationUnits);
        boolean compilationSuccessful = task.call();
        if (compilationSuccessful) {
            className = file.getPath().replace(".java", "");
        } else {
            for (Diagnostic<? extends JavaFileObject> diagnostic : diagnosticCollector.getDiagnostics()) {
                System.out.format("Error on line %d in %s%n", diagnostic.getLineNumber(), diagnostic.getSource().getName());
                System.out.println(diagnostic.getMessage(null));
            }
        }
        fileManager.close();
        return className;
    }


    private static void invokeTestMethod(Method method) throws ReflectiveOperationException {
        System.out.println("\u001B[36m");
        System.out.println("Invoking method: " + method.getName());
        System.out.println("\u001B[0m");
        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Class<?> type = parameter.getType();

            if (type.isPrimitive()) {
                args[i] = generateValue(type);
            } else if (type == String.class) {
                args[i] = generateMockValueForString();
            } else {
                args[i] = null;
            }
        }

        Object instance = Modifier.isStatic(method.getModifiers()) ? null : method.getDeclaringClass().getDeclaredConstructor().newInstance();
        method.invoke(instance, args);
    }

    private static Object generateValue(Class<?> type) {
        Random random = new Random();


        if (type == boolean.class) {
            return false;
        } else if (type == byte.class) {
            return (byte) random.nextInt(Byte.MAX_VALUE);
        } else if (type == short.class) {
            return random.nextInt(Short.MAX_VALUE);
        } else if (type == int.class || type == long.class) {
            return random.nextInt();
        } else if (type == float.class) {
            return random.nextFloat();
        } else if (type == double.class) {
            return random.nextDouble();
        } else if (type == char.class) {
            return 'a';
        }
        throw new IllegalArgumentException("Unsupported primitive type: " + type.getName());
    }

    private static String generateMockValueForString() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = random.nextInt(10) + 1;
        for (int i = 0; i < length; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        return sb.toString();
    }

    private static void getClassesInJarFile(File file, List<Class<?>> classes) throws IOException, ClassNotFoundException {
        JarInputStream jarInputStream = new JarInputStream(new FileInputStream(file));
        JarEntry jarEntry;

        URL[] urls = {new URL("jar:file:" + file + "!/")};
        URLClassLoader cl = URLClassLoader.newInstance(urls);

        while ((jarEntry = jarInputStream.getNextJarEntry()) != null) {
            if (!jarEntry.isDirectory() && jarEntry.getName().endsWith(".class")) {
                String className = jarEntry.getName().substring(0, jarEntry.getName().length() - 6);
                className = className.replace('/', '.');

                if (className.startsWith("org.example")) {
                    System.out.println("Class name: " + className);
                    byte[] bytes = jarInputStream.readAllBytes();
                    CustomClassLoader classLoader = new CustomClassLoader();
                    classes.add(classLoader.loadClass(bytes));
                }
            }
        }

        jarInputStream.close();
    }


    private static byte[] transformBytecode(String filePath) throws IOException {
        var file = filePath.trim();
        Path path = Paths.get(file);

        byte[] bytecode = Files.readAllBytes(path);
        // System.out.println(Arrays.toString(bytecode));
        ClassReader classReader = new ClassReader(bytecode);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        ClassVisitor classVisitor = new MyClassVisitor(classWriter);

        try {
            classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return classWriter.toByteArray();
    }


    private static void printClassHierarchy(Class<?> clasa) {
        Class<?> superclass = clasa.getSuperclass();
        if (superclass != null) {
            System.out.println( "Superclass: " + superclass.getName());
        }

        Class<?>[] interfaces = clasa.getInterfaces();
        if (interfaces.length > 0) {
            System.out.println( "Implemented interfaces: " );
            for (Class<?> iface : interfaces) {
                System.out.println(iface.getName());
            }
        }

        System.out.println("---");

        if (superclass != null) {
            printClassHierarchy(superclass);
        }
    }
}
