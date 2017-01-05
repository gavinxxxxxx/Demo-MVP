package com.gavin.mvvm.base;

import android.os.Environment;

import java.io.File;

/**
 * 常量类
 */
public class Constants {

    /**
     * 常规参数
     */
    public static final class General {
        public static final String IMAGE_END = ".jpg";
        public static final String UTF8 = "UTF-8";
        public static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
        public static final String DATEFORMAT2 = "yyyyMMddHHmmss";
        public static final String DB_NAME = "gavin.db";
        public static final int PAGE_SIZE = 15;
    }

    public static class SDCard {
        /**
         * SDCARD根目录
         */
        public final static String SDCARD = Environment.getExternalStorageDirectory().getPath();
        /**
         * 项目文件夹
         */
        private static final String STORAGE_DIR = SDCARD + File.separatorChar + "omg" + File.separatorChar;
        /**
         * 图片缓存文件夹
         */
        public final static String CACHE = "cache" + File.separatorChar;

        public static String getCacheDir() {
            File file = Environment.getExternalStorageDirectory();
            if (file.canWrite()) {
                file = new File(STORAGE_DIR + CACHE);
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
            return STORAGE_DIR + CACHE;
        }
    }
}
