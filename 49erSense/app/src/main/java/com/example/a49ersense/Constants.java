package com.example.a49ersense;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Constants {

    //These are constants defined for the weather page
    public static final String WEATHER_API_KEY = "ae2d88a96051226bd49105c46e7ce638";
    public static final String CURRENT_CITY = "Charlotte";
    public static final String localhostURL = "http://10.216.9.31/";
    public static final String scheduleFilePath = "/home/mluffman/Code/IoT/49erSense/schedule.txt";



    public static String URL_REGISTER = localhostURL + "register.php";
    public static String URL_LOGIN = localhostURL + "login.php";

    public static String URL_UPDATE_SENSORS = localhostURL + "updateSecurity.php";
    public static String URL_READ_SENSORS = localhostURL + "readSecurity.php";

    public static String URL_UPDATE_SCHEDULE = localhostURL + "updateSchedule.php";


}
