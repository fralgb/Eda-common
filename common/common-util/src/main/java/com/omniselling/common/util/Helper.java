package com.omniselling.common.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.omniselling.common.Constant;

public abstract class Helper
{
    private final static Logger logger = LoggerFactory.getLogger(Helper.class);
    private final static Pattern NUMERICPATTERN = Pattern.compile("[-+]?\\d*\\.?\\d+");

    public static final String getGenericFieldType(Class<?> clazz, String fieldName)
    {
        String dateType = null;
        Field f = Helper.getTheField(clazz, fieldName);
        Type fc = f.getGenericType();
        if (fc == null)
        {
            return null;
        }

        if (fc instanceof ParameterizedType)
        {
            ParameterizedType pt = (ParameterizedType) fc;

            Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[0];
            dateType = genericClazz.getSimpleName();
        }

        return dateType;
    }

    public static final <T> void setFieldValueList(T obj, String fieldName, Object valueList)
    {
        Class<?> clazz = obj.getClass();
        Field f = Helper.getTheField(clazz, fieldName);
        if (f == null)
        {
            return;
        }

        if (valueList == null)
        {
            return;
        }
        try
        {
            f.setAccessible(true);
            Class<?> fieldTypeClazz = f.getType();
            String fieldTypeName = fieldTypeClazz.getSimpleName();
            if ("List".equalsIgnoreCase(fieldTypeName))
            {
                f.set(obj, valueList);
            }

        }
        catch (Exception e)
        {
            logger.error("setFieldValueList exception :" + e.getMessage(), e);
        }
    }

    public static String getSubString(String source, String prefix, String suffix)
    {
        String result;
        String fromLead;
        int leadingIndex = 0;
        if (source == null)
        {
            return null;
        }

        if ((prefix == null || prefix.length() == 0))
        {
            fromLead = source;
        }
        else
        {
            leadingIndex = source.indexOf(prefix);
            if (leadingIndex == -1)
            {
                return null;
            }
            fromLead = source.substring(leadingIndex + prefix.length());
        }

        if (suffix == null || suffix.length() == 0 || fromLead.indexOf(suffix) == -1)
        {
            result = fromLead;
        }
        else
        {
            result = fromLead.substring(0, fromLead.indexOf(suffix));
        }

        return result.trim();
    }

    public static boolean isNumeric(String s)
    {
        return NUMERICPATTERN.matcher(s).matches();
    }

    public static Integer convertStringToInteger(String s)
    {
        Integer i = 0;
        try
        {
            i = Integer.parseInt(s);
        }
        catch (Exception e)
        {
            logger.error("Can not convert " + s + " to integer.");
            i = null;
        }

        return i;

    }

    public static Double convertStringToDouble(String s)
    {
        Double i = 0.0;
        try
        {
            i = Double.parseDouble(s);
        }
        catch (Exception e)
        {
            logger.error("Can not convert " + s + " to double.");
            i = null;
        }

        return i;

    }

    public static Long convertIntegerToLong(Integer i)
    {
        return Long.valueOf(i.longValue());
    }

    public static String computeContentMD5(String plainText)
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(plainText.getBytes());
        DigestInputStream dis = null;
        String res = null;
        try
        {
            dis = new DigestInputStream(bais, MessageDigest.getInstance("MD5"));
            byte[] buffer = new byte[8192];
            while (dis.read(buffer) > 0)
                ;
            res = new String(Base64.encodeBase64(dis.getMessageDigest().digest()));
            bais.close();
        }
        catch (Exception e)
        {
            logger.error("computeContentMD5 plainText=" + plainText + e.getMessage(), e);
        }
        return res;
    }

    public static String computeContentMD5(FileInputStream fis)
    {
        DigestInputStream dis = null;
        try
        {
            dis = new DigestInputStream(fis, MessageDigest.getInstance("MD5"));
        }
        catch (NoSuchAlgorithmException e)
        {
            logger.error("computeContentMD5 NoSuchAlgorithmException :" + e.getMessage(), e);
        }
        if (dis == null)
        {
            return null;
        }
        byte[] buffer = new byte[8192];
        try
        {
            while (dis.read(buffer) > 0)
                ;
        }
        catch (IOException e)
        {
            logger.error("computeContentMD5 IOException :" + e.getMessage(), e);
            return null;
        }
        String md5Content = new String(Base64.encodeBase64(dis.getMessageDigest().digest()));
        try
        {
            fis.getChannel().position(0);
        }
        catch (IOException e)
        {
            logger.error("computeContentMD5 IOException :" + e.getMessage(), e);
            return null;
        }
        return md5Content;
    }

    public static XMLGregorianCalendar dateToXMLGregorianCalendar(Date d)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return calendarToXMLGregorianCalendar(cal);
    }

    public static XMLGregorianCalendar calendarToXMLGregorianCalendar(Calendar cal)
    {

        try
        {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar) cal);
        }
        catch (Exception e)
        {
            logger.error("calendarToXMLGregorianCalendar cal=" + cal.getClass().getSimpleName() + e.getMessage(), e);
        }

        return null;
    }

    public static boolean createFilePath(String path)
    {
        boolean res = false;
        try
        {
            File d = new File(path);
            if (d.exists())
                return true;
            boolean mkFlag = d.mkdirs();
            if (!mkFlag)
            {
                logger.error("Can not create directory: " + path);
            }
            return mkFlag;
        }
        catch (Exception e)
        {
            logger.error("createFilePath path=" + path + e.getMessage(), e);
        }
        return res;
    }

    public static byte[] downloadFileToCache(String url)
    {
        URL website;
        byte[] result = null;
        try
        {
            website = new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            for (;;)
            {
                ByteBuffer buffer = ByteBuffer.allocate(4096);
                int len = rbc.read(buffer);
                if (len <= 0)
                    break;
                bos.write(buffer.array(), 0, len);
            }
            result = bos.toByteArray();
        }
        catch (Exception e)
        {
            logger.error("Error while downloading url: " + url);
        }

        return result;
    }

    public static <E extends Enum<E>> List<String> listFullName(Class<E> clazz)
    {
        List<String> list = new ArrayList<String>();
        E[] arrays = clazz.getEnumConstants();
        for (int i = 0; i < arrays.length; i++)
        {
            String fullName = clazz.getSimpleName() + "." + arrays[i].name();
            list.add(fullName);
        }
        return list;
    }

    public static <E extends Enum<E>> E generateEnumFromFullName(Class<E> clazz, String fullName)
    {
        if (fullName == null || fullName.length() == 0)
        {
            return null;
        }
        E enumValue = null;
        try
        {
            String name = fullName.substring(fullName.lastIndexOf('.') + 1);
            enumValue = Enum.valueOf(clazz, name);
        }
        catch (Exception e)
        {
            logger.error("generateEnumFromFullName error :" + e.getMessage(), e);
            enumValue = null;
        }
        return enumValue;
    }

    public static <E extends Enum<E>> String getFullName(Class<E> clazz, String name)
    {
        String fullName = null;
        if (name == null || name.length() == 0)
        {
            return null;
        }
        try
        {
            E element = Enum.valueOf(clazz, name);
            fullName = element.getClass().getSimpleName() + "." + name;
        }
        catch (Exception e)
        {
            logger.error("getFullName error :" + e.getMessage(), e);
            fullName = null;
        }
        return fullName;
    }

    public static <T> void setFieldValue(T obj, String fieldName, String fieldValue) throws Exception
    {
        Class<?> clazz = obj.getClass();
        Field f = Helper.getTheField(clazz, fieldName);
        if (f == null)
        {
            return;
        }

        if (fieldValue == null)
        {
            return;
        }

        f.setAccessible(true);
        Class<?> fieldTypeClazz = f.getType();
        String fieldTypeName = fieldTypeClazz.getSimpleName();
        if ("Date".equalsIgnoreCase(fieldTypeName))
        {
            Date date = TimeFormatterUtil.convertToYYYYMMDD(fieldValue);
            f.set(obj, date);
        }
        else if ("String".equalsIgnoreCase(fieldTypeName))
        {
            f.set(obj, fieldValue);
        }
        else if ("boolean".equals(fieldTypeName))
        {
            if ("true".equals(fieldValue) || "1".equals(fieldValue))
            {
                f.setBoolean(obj, true);
            }
            else
            {
                f.setBoolean(obj, false);
            }
        }
        else if ("Boolean".equals(fieldTypeName))
        {
            if ("null".equals(fieldValue))
            {
                f.set(obj, null);
            }
            else if ("true".equals(fieldValue) || "1".equals(fieldValue))
            {
                f.set(obj, Boolean.TRUE);
            }
            else
            {
                f.set(obj, Boolean.FALSE);
            }
        }
        else if ("int".equalsIgnoreCase(fieldTypeName))
        {
            f.setInt(obj, Integer.parseInt(fieldValue));
        }
        else if ("Integer".equalsIgnoreCase(fieldTypeName))
        {
            if (fieldValue == "")
            {
                f.set(obj, 0);
            }
            else
            {
                f.set(obj, new Integer(fieldValue));
            }
        }
        else if ("long".equals(fieldTypeName))
        {
            f.setLong(obj, Long.parseLong(fieldValue));
        }
        else if ("Long".equals(fieldTypeName))
        {
            if (fieldValue == "")
            {
                f.set(obj, 0);
            }
            else
            {
                f.set(obj, new Long(fieldValue));
            }
        }
        else if ("short".equals(fieldTypeName))
        {
            f.setShort(obj, Short.parseShort(fieldValue));
        }
        else if ("Short".equals(fieldTypeName))
        {
            f.set(obj, new Short(fieldValue));
        }
        else if ("byte".equals(fieldTypeName))
        {
            f.setShort(obj, Byte.parseByte(fieldValue));
        }
        else if ("Byte".equals(fieldTypeName))
        {
            f.set(obj, new Byte(fieldValue));
        }
        else if ("float".equals(fieldTypeName))
        {
            f.setFloat(obj, Float.parseFloat(fieldValue));
        }
        else if ("Float".equals(fieldTypeName))
        {
            f.set(obj, new Float(fieldValue));
        }
        else if ("double".equals(fieldTypeName))
        {
            f.setDouble(obj, Double.parseDouble(fieldValue));
        }
        else if ("Double".equals(fieldTypeName))
        {
            f.set(obj, new Double(fieldValue));
        }
        else if ("char".equalsIgnoreCase(fieldTypeName))
        {
            f.setChar(obj, Character.valueOf(fieldValue.charAt(0)));
        }
        else if ("Character".equalsIgnoreCase(fieldTypeName))
        {
            f.set(obj, new Character(fieldValue.charAt(0)));
        }
        else if ("BigDecimal".equalsIgnoreCase(fieldTypeName))
        {
            f.set(obj, new BigDecimal(fieldValue));
        }
    }

    private static Field getTheField(Class<?> clazz, String fieldName)
    {
        Field f = null;
        if (fieldName == null || fieldName.length() == 0)
        {
            return null;
        }
        if (clazz.getSimpleName().equalsIgnoreCase("Object"))
            return f;
        try
        {
            f = clazz.getDeclaredField(fieldName);
            if (f != null)
            {
                return f;
            }
        }
        catch (NoSuchFieldException e)
        {
            return getTheField(clazz.getSuperclass(), fieldName);
        }
        catch (Exception e)
        {
            logger.error("getTheField error :" + e.getMessage(), e);
            f = null;
        }
        return f;
    }

    public static <T> String getFieldValue(T obj, String fieldName)
    {
        Class<?> clazz = obj.getClass();
        Field f = Helper.getTheField(clazz, fieldName);
        if (f == null)
        {
            return null;
        }
        Object fieldValue = null;
        try
        {
            f.setAccessible(true);
            fieldValue = f.get(obj);

        }
        catch (IllegalArgumentException e)
        {
            logger.error("getFieldValue IllegalArgumentException :" + e.getMessage(), e);
            return null;
        }
        catch (IllegalAccessException e)
        {
            logger.error("getFieldValue IllegalAccessException" + e.getMessage(), e);
            return null;
        }

        if (fieldValue == null)
            return null;

        return fieldValue.toString();
    }

    public static <T> Object getFieldObjectValue(T obj, String fieldName)
    {
        Class<?> clazz = obj.getClass();
        Field f = Helper.getTheField(clazz, fieldName);
        if (f == null)
        {
            return null;
        }
        Object fieldValue = null;
        try
        {
            f.setAccessible(true);
            fieldValue = f.get(obj);

        }
        catch (IllegalArgumentException e)
        {
            logger.error("getFieldObjectValue IllegalArgumentException" + e.getMessage(), e);
            return null;
        }
        catch (IllegalAccessException e)
        {
            logger.error("getFieldObjectValue IllegalAccessException" + e.getMessage(), e);
            return null;
        }

        if (fieldValue == null)
        {
            return null;
        }

        // formatter number
        if (f.getType().getSimpleName().equalsIgnoreCase("double")
                || f.getType().getSimpleName().equalsIgnoreCase("BigDecimal"))
        {
            BigDecimal bigDecimal = new BigDecimal(fieldValue.toString());
            bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
            fieldValue = bigDecimal.toString();
        }

        // formatter date
        if (f.getType().getSimpleName().equalsIgnoreCase("Date"))
        {
            fieldValue = TimeFormatterUtil.convertToYYYYMMDDHHMMSS((Date) fieldValue);
        }

        return fieldValue;
    }

    public static String readFile(String path, Charset encoding)
    {
        String data = null;
        byte[] encoded = null;
        try
        {
            encoded = Files.readAllBytes(Paths.get(path));
        }
        catch (IOException e)
        {
            logger.error("ReadFile ERROR. path=" + path, e);
        }
        data = new String(encoded, encoding);
        return data;
    }

    public static void writeFile(String path, String data)
    {
        try
        {
            Files.write(Paths.get(path), data.getBytes(Constant.ENCODING));
        }
        catch (IOException e)
        {
            logger.error("writeFile ERROR. path=" + path, e);
        }
    }

    public static void writeFile(String path, String data, Charset charset)
    {
        try
        {
            Files.write(Paths.get(path), data.getBytes(charset));
        }
        catch (IOException e)
        {
            logger.error("writeFile ERROR. path=" + path, e);
        }
    }
	
   public static BigDecimal convertKgToLb(BigDecimal weight)
    {
        if (weight == null)
        {
            return null;
        }
        BigDecimal weightInLb = BigDecimal.valueOf(Constant.KG_PER_LB * weight.doubleValue());
        return weightInLb;
    }

    public static BigDecimal convertCmToIn(BigDecimal cm)
    {
        if (cm == null)
        {
            return null;
        }
        BigDecimal inch = BigDecimal.valueOf(Constant.INCH_PER_CM * cm.doubleValue());
        return inch;
    }

    public static double convertKgToLb(double weight)
    {
        double weightInLb = DoubleUtil.mul(weight, Constant.KG_PER_LB);
        return weightInLb;
    }

    public static double convertKgToLbCeiling1(double weight)
    {
        BigDecimal b = new BigDecimal(Constant.KG_PER_LB * weight);
        double result = b.setScale(1, BigDecimal.ROUND_CEILING).doubleValue();

        return result;
    }

    public static double convertLbToKg(double weightLbs)
    {
        return DoubleUtil.div(weightLbs, Constant.KG_PER_LB, 5);
    }

    public static double convertCmToInCeiling1(double cm)
    {
        BigDecimal b = new BigDecimal(Constant.INCH_PER_CM * cm);
        double result = b.setScale(1, BigDecimal.ROUND_CEILING).doubleValue();

        return result;
    }

    public static double convertCmToIn(double cm)
    {
        double inch = DoubleUtil.mul(cm, Constant.INCH_PER_CM);
        return inch;
    }

    public static double convertCmToFt(double cm)
    {
        double inch = DoubleUtil.mul(cm, Constant.FT_PER_CM);
        return inch;
    }

    public static double convertKgToOZS(double weight)
    {
        double weightInOZS = DoubleUtil.mul(weight, Constant.KG_PER_OZS);
        return weightInOZS;
    }

    public static <T> Map<String, String> getTheFieldsInfo(T obj)
    {
        Class<?> clazz = obj.getClass();
        Map<String, String> fieldsInfo = new HashMap<String, String>();
        Field[] fieldTemp = clazz.getDeclaredFields();
        if (fieldTemp != null && fieldTemp.length > 0)
        {
            for (Field f : fieldTemp)
            {
                fieldsInfo.put(f.getName(), f.getType().getSimpleName());
            }
        }
        return fieldsInfo;
    }

    public static BufferedImage Rotate(Image src, int angel)
    {
        int src_width = src.getWidth(null);
        int src_height = src.getHeight(null);
        // calculate the new image size
        Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(src_width, src_height)), angel);

        BufferedImage res = null;
        res = new BufferedImage(rect_des.width, rect_des.height, BufferedImage.TYPE_BYTE_INDEXED);
        Graphics2D g2 = res.createGraphics();
        // transform
        g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);
        g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);

        g2.drawImage(src, null, null);
        return res;
    }

    public static Rectangle CalcRotatedSize(Rectangle src, int angel)
    {
        // if angel is greater than 90 degree, we need to do some conversion
        if (angel >= 90)
        {
            if (angel / 90 % 2 == 1)
            {
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }

        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) src.height / src.width);
        double angel_dalta_height = Math.atan((double) src.width / src.height);

        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
        int des_width = src.width + len_dalta_width * 2;
        int des_height = src.height + len_dalta_height * 2;
        return new java.awt.Rectangle(new Dimension(des_width, des_height));
    }

    public static BufferedImage ImgMerge(BufferedImage imgBuf, BufferedImage businessNumImgBuf, int x, int y)
    {
        y = imgBuf.getHeight() - y;
        int width = businessNumImgBuf.getWidth();// 图片宽度
        int height = businessNumImgBuf.getHeight();// 图片高度
        // 从图片中读取RGB
        int[] imageArray = new int[width * height];
        imageArray = businessNumImgBuf.getRGB(0, 0, width, height, imageArray, 0, width);
        imgBuf.setRGB(x, y, width, height, imageArray, 0, width);// 设置部分的RGB
        return imgBuf;
    }

    public static List<List<String>> convertCsvToTable(String csvData, String delimiter)
    {
        List<List<String>> arrData = new ArrayList<>();
        // Check to see if the delimiter is defined. If not,
        // then default to comma.
        if (StringUtils.isEmpty(csvData))
        {
            return arrData;
        }
    
        delimiter = delimiter == null ? "," : delimiter;
    
        // Create a regular expression to parse the CSV values.
        Pattern objPattern = Pattern.compile(
                //delimiter
                "(\\" + delimiter + "|\\r?\\n|\\r|^)" +
                        // Quoted fields.
                        "(?:\"([^\"]*(?:\"\"[^\"]*)*)\"|" +
                        // Standard fields.
                        "([^\"\\" + delimiter + "\\r\\n]*))",
                Pattern.CASE_INSENSITIVE);
        // Create an array to hold our data. Give the array
        // a default empty first row.
        arrData.add(new ArrayList<String>());
    
        // Create an array to hold our individual pattern
        // matching groups.
        Matcher matcher = objPattern.matcher(csvData);
        // Keep looping over the regular expression matches
        // until we can no longer find a match.
        while (matcher.find())
        {
    
            // Get the delimiter that was found.
            String strMatchedDelimiter = matcher.group(1);
    
            // Check to see if the given delimiter has a length
            // (is not the start of string) and if it matches
            // field delimiter. If id does not, then we know
            // that this delimiter is a row delimiter.
            if (strMatchedDelimiter.length() > 0 && !delimiter.equals(strMatchedDelimiter))
            {
                arrData.add(new ArrayList<String>());
            }
    
            // Now that we have our delimiter out of the way,
            // let's check to see which kind of value we
            // captured (quoted or unquoted).
            String quoted = matcher.group(2);
            String strMatchedValue;
            if (!StringUtils.isEmpty(quoted))
            {
                // We found a quoted value. When we capture
                // this value, unescape any double quotes.
                strMatchedValue = quoted.replace("\"\"", "\"");
            }
            else
            {
                // We found a non-quoted value.
                strMatchedValue = matcher.group(3);
            }
    
            // Now that we have our value string, let's add
            // it to the data array.
            arrData.get(arrData.size() - 1).add(strMatchedValue);
        }
        return arrData;
    }

    public static String arrayToTableHTML(List<List<String>> arrData)
    {
        if (arrData == null)
        {
            return null;
        }
        String style = "<style type='text/css'>"
                + ".dlgtitle{color:black; font-weight:bold; height:30px;padding: 2px, 4px;align:center;background-color: #dfe4eb;}"
                + ".oddrow {background-color: #dfe4eb;}                                                                           "
                + ".even{background-color: #dfe4eb;}                                                                              "
                + ".ocenter{text-align: center;}                                                                                  "
                + "tbody>tr:hover{                                                                                                "
                + "  color: white;                                                                                                "
                + "  background-color: grey;                                                                                      "
                + "}                                                                                                              "
                + "table {                                                                                                        "
                + "  overflow: auto;                                                                                              "
                + "}                                                                                                              "
                + ".tbody>tr:hover{                                                                                               "
                + "  color: white;                                                                                                "
                + "  background-color: gray;                                                                                      "
                + "}                                                                                                              "
                + "tr td{                                                                                                         "
                + "  padding: 2px;                                                                                                "
                + "}                                                                                                              "
                + "</style>";
        String tableStr = style + "<table>";
        for (int i = 0; i < arrData.size(); i++) {
          List<String> row = arrData.get(i);
          if (i == 0) {
            tableStr += "<thead><tr class='dlgtitle ocenter'>";
          } else if (i == 1) {
            tableStr += "<thbody><tr class='event ocenter'>";
          } else if (i % 2 == 0) {
            tableStr += "<tr class='oddrow ocenter'>";
          } else {
            tableStr += "<tr class='event ocenter'>";
          }
    
          if (row!=null && row.size()>0) {
            for (int j = 0; j < row.size(); j++) {
              tableStr += "<td>" + row.get(j) + "</td>";
            }
          }
    
          if (i == 0) {
                tableStr += "</tr></thead>";
          } else {
                tableStr += "</tr>";
          }
    
        }
        tableStr += "</thbody>";
        tableStr += "</table>";
        return tableStr;
    }

    /**
     * 随机生成一位字母+四位数字
     * 
     * @return
     */
    public static String generateRandom()
    {
        String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "M", "N", "P", "Q", "R", "S", "T", "U",
                "V", "W", "X", "Y", "Z" };//过滤 O  L  I
    
        int[] nums = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    
        int n = (int) (Math.random() * 23);
        String letter = letters[n];
        int length = letter.length();
    
        while (length < 5)
        {
            int m = (int) (Math.random() * 10);
            letter += nums[m];
            length++;
        }
    
        return letter;
    }

    public static String stackTraceToString(Throwable e)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString(); // stack trace as a string
    }

    public static <E> String collectionToString(Collection<E> col)
    {
        String res = "";
        if (col == null || col.isEmpty())
        {
            return res;
        }
    
        StringBuilder sb = new StringBuilder();
        for (E s : col)
        {
            sb.append(s);
            sb.append(",");
        }
        res = sb.toString();
        return res.substring(0, res.length() - 1);
    }
}
