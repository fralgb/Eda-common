package com.omniselling.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class TxtFileReader
{
    public static String readTxt(String fileName) throws IOException
    {
        BufferedReader reader = null;
        String str = null;
        StringBuffer sbf = new StringBuffer();
        try
        {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8")); // 指定读取文件的编码格式，要和写入的格式一致，以免出现中文乱码,

            while ((str = reader.readLine()) != null)
            {
                sbf.append(str);
            }
        }
        finally
        {
            if (reader != null)
            {
                reader.close();
            }

        }
        return sbf.toString();
    }

    public static List<String> readLines(String fileName) throws IOException
    {
        List<String> lines = new ArrayList<String>();
        BufferedReader reader = null;
        String str = null;
        try
        {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8")); // 指定读取文件的编码格式，要和写入的格式一致，以免出现中文乱码,

            while ((str = reader.readLine()) != null)
            {
                lines.add(str);
            }
        }
        finally
        {
            reader.close();
        }
        return lines;
    }

}
