//package edu.pwr.backend.config;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DataSourceContextHolder {
//    private static final ThreadLocal<String> threadLoal = new ThreadLocal<>();
//    private static List<String> datSourceLookupKeys = new ArrayList<>();
//
//    public static void switchDataSource(String ds) {
//      threadLoal.set(ds);
//
//    }
//
//    public static String getDataSource() {
//        return threadLoal.get();
//    }
//
//    public static void clearDataSource() {
//        threadLoal.remove();
//    }
//
//    public static boolean containDataSource(String ds) {
//        return datSourceLookupKeys.contains(ds);
//    }
//
//
//    public static void addDataSource(String ds) {
//        if (!containDataSource(ds)) {
//            datSourceLookupKeys.add(ds);
//        }
//    }
//
//
//
//
//
//}
