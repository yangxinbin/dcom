package com.mango.leo.dcom.util;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class Urls {
    public static final String HOST = "http://192.168.1.120:8080/dcomapi";
    //public static final String HOST = "http://dcom.hopesen.com.cn/dcomapi";
    public static final String AUTH = "/api/auth/auth";
    public static final String LOGINOUT = "/api/auth/logout";
    public static final String CHANGEPASSWORD = "/api/secure/users/changePassword";
    public static final String PROFILE = "/api/secure/users/profile";
    public static final String ASSET = "/api/secure/inspection/asset";
    public static final String SAVEPHOTO = "/api/secure/inspection/savePhoto";
    public static final String INSPECT = "/api/secure/inspection/inspect";
    public static final String MYEVENT = "/api/secure/event/list/my";
    public static final String ALLEVENT = "/api/secure/event/list/all";


    public static final String HOST_AUTH = HOST + AUTH;
    public static final String HOST_LOGINOUT = HOST + LOGINOUT;
    public static final String HOST_CHANGEPASSWORD = HOST + CHANGEPASSWORD;
    public static final String HOST_PROFILE = HOST + PROFILE;
    public static final String HOST_ASSET = HOST + ASSET;
    public static final String HOST_SAVEPHOTO = HOST + SAVEPHOTO;
    public static final String HOST_INSPECT = HOST + INSPECT;
    public static final String HOST_MYEVENT = HOST + MYEVENT;
    public static final String HOST_ALLEVENT = HOST + ALLEVENT;



}
