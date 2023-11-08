package de.sebsprenger;

import de.sebsprenger.domain.AdminUser;
import de.sebsprenger.domain.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        User user1 = new User("Paul", "eb23u1-dbsa");

        Class<?> clazz1 = User.class;
        Class<?> clazz2 = user1.getClass();
        Class<?> clazz3 = Class.forName("de.sebsprenger.domain.User");

        displayClassInformation(clazz1);

        System.out.println();
        overcomeVisibility(clazz1, user1);

        System.out.println();
        User user2 = constructNewObject(clazz2);

        System.out.println();
        invokeMethod(clazz3, user1, user2);

    }

    private static void displayClassInformation(Class<?> clazz) {
        final String SEPARATOR = "  ----------------------------------------------------------------------------------------------------";
        System.out.println("Type description:");

        display("declared constructors", clazz.getDeclaredConstructors());
        display("         constructors", clazz.getConstructors());
        System.out.println(SEPARATOR);
        display("      declared fields", clazz.getDeclaredFields());
        display("               fields", clazz.getFields());
        System.out.println(SEPARATOR);
        display("     declared methods", clazz.getDeclaredMethods());
        display("              methods", clazz.getMethods());
        System.out.println(SEPARATOR);
        display(" declared annotations", clazz.getDeclaredAnnotations());
        display("          annotations", clazz.getAnnotations());
    }

    private static void overcomeVisibility(Class<?> clazz, User user) throws NoSuchFieldException, IllegalAccessException {
        System.out.println("Accessing private elements:");

        Field passwordField = clazz.getDeclaredField("password");
        passwordField.setAccessible(true);

        System.out.println("  before update: " + user);
        passwordField.set(user, "123456");
        System.out.println("  after update:  " + user);
    }

    private static User constructNewObject(Class<?> clazz) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        System.out.println("Constructing a new object:");

        Constructor<?> constructor = clazz.getDeclaredConstructor(String.class, String.class);
        User user = (User) constructor.newInstance("Adrian", "y389-12nkdjas");
        System.out.println("  user=" + user);

        return user;
    }

    private static void invokeMethod(Class<?> clazz, User user1, User user2) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("Invoking a method:");

        Method passwordHashMethod = clazz.getDeclaredMethod("getPasswordHash");
        System.out.println("  user1=" + passwordHashMethod.invoke(user1));
        System.out.println("  user2=" + passwordHashMethod.invoke(user2));
    }

    private static <T> void display(String text, T[] elements) {
        Arrays.stream(elements).forEach(element -> System.out.println("  " + text + ": " + element));
    }

}
