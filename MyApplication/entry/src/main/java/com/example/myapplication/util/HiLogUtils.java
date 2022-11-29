package com.example.myapplication.util;

import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class HiLogUtils {

    public  static  final String  TAG = "HM_TAG";
    private static final HiLogLabel LABEL = new HiLogLabel(ohos.hiviewdfx.HiLog.LOG_APP, 0x10000, TAG);
    public static  boolean flag = false;

    public  static  void  d(String msg){
        if(!flag){
            HiLog.debug(LABEL, msg);
        }
    }
    public  static  void  e(String msg){
        if(!flag){
            HiLog.error(LABEL, msg);
        }
    }
    public  static  void  w(String msg){
        if(!flag){
            HiLog.warn(LABEL, msg);

        }
    }
    public  static  void i (String msg){
        if(!flag){
            HiLog.info(LABEL, msg);
        }
    }

    public  static  void  f(String msg){
        if(!flag){
            HiLog.fatal(LABEL, msg);
        }
    }
}
