package org.example;

import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Analyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path to the .class file: ");
        String classFilePath = scanner.nextLine();
        scanner.close();
        Path path = Paths.get(classFilePath);

        try {
            //byte[] bytes = Files.readAllBytes(path);
//            ClassLoader classLoader = new ClassLoader() {
//                @Override
//                protected Class<?> findClass(String name)  {
//                    return defineClass(name, bytes, 0, bytes.length);
//                }
//            };
            Class<?> clasa = Class.forName(getClassName(classFilePath));
            //Class<?> clasa = classLoader.loadClass(getClassName(classFilePath));
            Package classPackage = clasa.getPackage();
            System.out.println("Class: " + clasa.getName());
            System.out.println("Package: " + (classPackage != null ? classPackage.getName() : "default"));
            List<Method> allMethods = getAllMethods(clasa);
            System.out.println("All methods:");
            for (Method method : allMethods) {
                System.out.println(method.getName());
            }


            List<Method> testMethods = getTestMethods(clasa);
            if (!testMethods.isEmpty()) {
                System.out.println("Test methods:");
                for (Method method : testMethods) {
                    System.out.println(method.getName());
                    method.invoke(null);
                }
            } else {
                System.out.println("No test methods found.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static String getClassName(String classFilePath) {
        String className = classFilePath.replace(".class", "");
        className = className.replace('/', '.');
        return className;
    }

    private static List<Method> getTestMethods(Class<?> clasa) {
        List<Method> testMethods = new ArrayList<>();
        Method[] methods = clasa.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class) && method.getParameterCount() == 0 && method.getReturnType() == void.class ) {
                testMethods.add(method);
            }
        }
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
}