package com.omniselling.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.omniselling.common.util.report.Reportable;

public class ExcelUtil<T extends Reportable> extends ExcelReportFileReaderWriterUtil<T>
{
    private String defaultChartSet = System.getProperties().getProperty("file.encoding");
    private String inputFileChartSet = "";
    Logger log = LoggerFactory.getLogger(this.getClass());

    public List<T> readReportFile(Class<T> rowClass, InputStream inputStream) throws Exception
    {
        List<T> list = new ArrayList<T>();
        Workbook workbook = new XSSFWorkbook(inputStream);
        List<String> mapName2List = new ArrayList<String>();
        Map<String, String> name2HeadMap = rowClass.newInstance().fetchFieldNameAndHeaderMapping()
                .getFieldNameToHeaderMap();
        for (Map.Entry<String, String> map : name2HeadMap.entrySet())
        {
            mapName2List.add(map.getKey());
        }

        for (int i = 0; i < workbook.getNumberOfSheets(); i++)
        {
            Sheet sheet = workbook.getSheetAt(i);
            Iterator<Row> rowIterator = sheet.iterator();
            int rownum = 0;

            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                if (!(rownum == 0 && 0 == i))
                {
                    T t = rowClass.newInstance();
                    int cellCount = 0;
                    // For each row, iterate through each columns
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext())
                    {
                        Cell cell = cellIterator.next();
                        if (cellCount >= mapName2List.size())
                        {
                            log.error("excel date error");
                            break;
                        }

                        int colIndex = cell.getColumnIndex();

                        while (cellCount != colIndex)
                        {
                            log.debug("cellCount : " + cellCount);
                            cellCount++;
                        }
                        log.debug("colIndex : " + colIndex);

                        Field field = getTheField(rowClass, mapName2List.get(cellCount));
                        if (field == null)
                            continue;

                        field.setAccessible(true);
                        Class<?> fieldCz = field.getType();
                        if (fieldCz.equals(java.lang.Short.class) || fieldCz.equals(short.class))
                        {
                            Short shortValue = cell2Short(cell);
                            if (null != shortValue)
                            {
                                field.set(t, shortValue);
                            }
                        }
                        else if (fieldCz.equals(java.lang.Integer.class) || fieldCz.equals(int.class))
                        {
                            Integer intValue = cell2Int(cell);
                            if (null != intValue)
                            {
                                field.set(t, intValue);
                            }
                        }
                        else if (fieldCz.equals(java.lang.Long.class) || fieldCz.equals(long.class))
                        {
                            Long longValue = cell2Long(cell);
                            if (null != longValue)
                            {
                                field.set(t, longValue);
                            }
                        }
                        else if (fieldCz.equals(java.lang.Float.class) || fieldCz.equals(float.class))
                        {
                            Float floatValue = cell2Float(cell);
                            if (null != floatValue)
                            {
                                field.set(t, floatValue);
                            }
                        }
                        else if (fieldCz.equals(java.lang.Double.class) || fieldCz.equals(double.class))
                        {
                            Double d = cell2Double(cell);
                            if (null != d)
                            {
                                field.set(t, d);
                            }

                        }
                        else if (fieldCz.equals(java.lang.String.class))
                        {
                            String str = cell2String(cell);
                            field.set(t, str);
                        }
                        else if (fieldCz.equals(java.util.Date.class))
                        {
                            Date d = cell2Date(cell);
                            field.set(t, d);
                        }
                        else if (fieldCz.equals(java.lang.Boolean.class) || fieldCz.equals(boolean.class))
                        {
                            Boolean b = cell2Boolean(cell);
                            if (null != b)
                            {
                                field.set(t, b);
                            }
                        }
                        else if (fieldCz.equals(java.math.BigDecimal.class))
                        {
                            BigDecimal big = cell2BigDecimal(cell);
                            if (null != big)
                            {
                                field.set(t, big);
                            }
                        }

                        cellCount++;
                    }
                    if (cellCount <= mapName2List.size())
                    {
                        list.add(t);
                    }
                    else
                    {
                        log.error("near rowNum:" + (rownum + 1) + "  date error");
                    }

                }
                rownum++;
            }

        }
        inputStream.close();

        return list;
    }

    /**
     * get excel sheet data by file and sheet index
     */
    public static List<String[]> getExcelSheetData(File file, int sheetIndex, Integer beginRow)
    {
        List<String[]> dataList = new ArrayList<String[]>();
        try
        {
            InputStream inputStream = new FileInputStream(file);
            dataList = getExcelSheetData(inputStream, sheetIndex, beginRow);
            inputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return dataList;
        }
        return dataList;
    }

    /**
     * get excel sheet data by input stream
     */
    public static List<String[]> getExcelSheetData(InputStream is, int sheetIndex, Integer beginRow) throws Exception
    {
        List<String[]> dataList = new ArrayList<String[]>();

        // 1. get sheet data
        //HSSFWorkbook wb = new HSSFWorkbook(is);
        Workbook wb = new XSSFWorkbook(is);
        Sheet sheet = wb.getSheetAt(sheetIndex);

        // 2. get excel content
        int ii = 0;
        beginRow = (beginRow == null ? 0 : beginRow);
        for (Row r : sheet)
        {
            if (ii++ < beginRow)
            {
                continue;
            }
            if (r == null)
            {
                continue;
            }
            int lastColumn = r.getLastCellNum();
            if (lastColumn <= 0)
            {
                continue;
            }

            String[] singleRow = new String[lastColumn];

            for (int i = 0; i < lastColumn; i++)
            {
                //Cell cell = r.getCell(i, Row.CREATE_NULL_AS_BLANK);
                Cell cell = r.getCell(i);
                //System.out.println("233, ExcelUtil.java, cell:"+cell.getStringCellValue());
                switch (cell.getCellType())
                {
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    singleRow[i] = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell))
                    {
                        singleRow[i] = String.valueOf(cell.getDateCellValue());
                    }
                    else
                    {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String temp = cell.getStringCellValue();
                        // numeric value or integer string
                        if (temp.indexOf(".") > -1)
                        {
                            singleRow[i] = String.valueOf(new Double(temp)).trim();
                        }
                        else
                        {
                            singleRow[i] = temp.trim();
                        }
                    }
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    singleRow[i] = cell.getRichStringCellValue().getString();
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:
                    singleRow[i] = cell.getCellFormula();
                    break;
                default:
                    singleRow[i] = "";
                    break;
                }
            }
            dataList.add(singleRow);
        }

        //is.close();
        return dataList;
    }

    private Field getTheField(Class<?> clazz, String fieldName)
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
            logger.error(e.getMessage(), e);
            f = null;
        }
        return f;
    }

    private Short cell2Short(Cell cell)
    {
        Short s = null;
        switch (cell.getCellType())
        {
        case HSSFCell.CELL_TYPE_STRING:
            s = Short.parseShort(cell.getStringCellValue());
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            s = (short) cell.getNumericCellValue();
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            break;
        default:
            break;
        }
        return s;
    }

    private Integer cell2Int(Cell cell)
    {
        switch (cell.getCellType())
        {
        case HSSFCell.CELL_TYPE_STRING:
            return Integer.parseInt(cell.getStringCellValue());
        case HSSFCell.CELL_TYPE_NUMERIC:
            return (int) cell.getNumericCellValue();
        case HSSFCell.CELL_TYPE_BOOLEAN:
            return null;
        case HSSFCell.CELL_TYPE_BLANK:
            return null;
        default:
            return null;
        }
    }

    private Long cell2Long(Cell cell)
    {
        Long s = null;
        switch (cell.getCellType())
        {
        case HSSFCell.CELL_TYPE_STRING:
            s = Long.parseLong(cell.getStringCellValue());
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            s = (long) cell.getNumericCellValue();
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            break;
        default:
            break;
        }
        return s;
    }

    private Float cell2Float(Cell cell)
    {
        Float s = null;
        switch (cell.getCellType())
        {
        case HSSFCell.CELL_TYPE_STRING:
            s = Float.parseFloat(cell.getStringCellValue());
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            s = (float) cell.getNumericCellValue();
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            break;
        default:
            break;
        }
        return s;
    }

    private BigDecimal cell2BigDecimal(Cell cell)
    {
        switch (cell.getCellType())
        {
        case HSSFCell.CELL_TYPE_STRING:
            return new BigDecimal(cell.getStringCellValue());
        case HSSFCell.CELL_TYPE_NUMERIC:
            return new BigDecimal(cell.getNumericCellValue());
        case HSSFCell.CELL_TYPE_BOOLEAN:
            return null;
        case HSSFCell.CELL_TYPE_BLANK:
            return null;
        default:
            return null;
        }
    }

    private Double cell2Double(Cell cell)
    {
        switch (cell.getCellType())
        {
        case HSSFCell.CELL_TYPE_STRING:
            return Double.parseDouble(cell.getStringCellValue());
        case HSSFCell.CELL_TYPE_NUMERIC:
            return cell.getNumericCellValue();
        case HSSFCell.CELL_TYPE_BOOLEAN:
            return null;
        case HSSFCell.CELL_TYPE_BLANK:
            return null;
        default:
            return null;
        }
    }

    private Boolean cell2Boolean(Cell cell)
    {
        Boolean b = null;
        switch (cell.getCellType())
        {
        case HSSFCell.CELL_TYPE_STRING:
            String str = cell.getStringCellValue();
            if ("true".equals(str))
            {
                b = true;
            }
            else if ("false".equals(str))
            {
                b = false;
            }
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            b = cell.getBooleanCellValue();
        case HSSFCell.CELL_TYPE_BLANK:
            break;
        default:
            break;
        }
        return b;
    }

    private Date cell2Date(Cell cell)
    {
        Date date = null;
        switch (cell.getCellType())
        {
        case HSSFCell.CELL_TYPE_STRING:
            String strCell = cell.getStringCellValue();
            String dataStr = cell.getStringCellValue();
            try
            {
                // yyyy-mm-dd
                if (strCell.indexOf("-") > -1 && strCell.length() == 10)
                {
                    date = getDateFormat().parse(strCell);
                    // yyyy-mm-dd HH:MM:SS
                }
                else if (strCell.indexOf("-") > -1 && strCell.length() == 19)
                {
                    date = getDateTimeFormat().parse(dataStr);
                }
                else
                {
                    String extraDateString = getExtraDatePatternString();
                    SimpleDateFormat df = new SimpleDateFormat(extraDateString);
                    date = df.parse(dataStr);
                }
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            return date;
        case HSSFCell.CELL_TYPE_NUMERIC:
            if (HSSFDateUtil.isCellDateFormatted(cell))
            {
                date = cell.getDateCellValue();
            }
            return date;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            break;
        default:
            break;
        }
        return date;
    }

    private String cell2String(Cell cell)
    {
        String strCell = "";
        switch (cell.getCellType())
        {
        case HSSFCell.CELL_TYPE_STRING:
            if (defaultChartSet.toLowerCase().indexOf("utf") > -1
                    && inputFileChartSet.toLowerCase().indexOf("gbk") > -1)
            {
                try
                {
                    strCell = new String(cell.getStringCellValue().getBytes(inputFileChartSet), defaultChartSet);
                }
                catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                strCell = cell.getStringCellValue();
            }
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            // strCell = String.valueOf(cell.getNumericCellValue());
            // String str = strCell.substring(strCell.length()-2);
            // if(str.equals(".0"))
            // strCell = strCell.substring(0, strCell.length()-2);
            strCell = new BigDecimal(cell.getNumericCellValue()).toPlainString();
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            break;
        }
        return strCell;
    }
}
