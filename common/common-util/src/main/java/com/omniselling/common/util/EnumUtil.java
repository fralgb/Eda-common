package com.omniselling.common.util;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.omniselling.common.Enumeration;

public final class EnumUtil
{
    public static <E extends Enum<E>> boolean hasValue(Class<E> enumeration, String name)
    {
        for (E value : EnumSet.allOf(enumeration))
        {
            if (value.name().equals(name))
                return true;
        }
        return false;
    }

    public static <E extends Enum<E>> List<String> listSimpleName(Class<E> clazz)
    {
        List<String> list = new ArrayList<>();
        E[] arrays = clazz.getEnumConstants();
        for (int i = 0; i < arrays.length; i++)
        {
            String fullName = clazz.getSimpleName() + "." + arrays[i].name();
            list.add(fullName);
        }
        return list;
    }
    
    public static <E extends Enum<E>> E fromSimpleName(Class<E> clazz, String fullName)
    {
        if (fullName == null || fullName.length() == 0)
        {
            return null;
        }
        E enumValue = null;
        String name = fullName.substring(fullName.lastIndexOf('.') + 1);
        enumValue = Enum.valueOf(clazz, name);

        return enumValue;
    }
    
    public static <E extends Enum<E>> String toSimpleName(Class<E> clazz, String name)
    {
        String fullName = null;
        if (name == null || name.length() == 0)
        {
            throw new RuntimeException("All parameters are mandatory!");
        }

        E element = Enum.valueOf(clazz, name);
        fullName = element.getClass().getSimpleName() + "." + name;

        return fullName;
    }

    public static String toFullName(Enum<?> e)
    {
        if (e == null)
        {
            throw new RuntimeException("All parameters are mandatory!");
        }

        return e.getClass().getName() + "." + e.name();
    }

    public static String toFullName(Enumeration e)
    {
        if (e == null)
        {
            throw new RuntimeException("All parameters are mandatory!");
        }

        return e.getClass().getName() + "." + e.name();
    }

    static public <E extends Enum<E>> E fromFullName(String enumFullName)
    {
        // see http://stackoverflow.com/questions/4545937/
        String[] x = enumFullName.split("\\.(?=[^\\.]+$)");
        if (x.length == 2)
        {
            String enumClassName = x[0];
            String enumName = x[1];
            try
            {
                @SuppressWarnings("unchecked")
                Class<E> cl = (Class<E>) Class.forName(enumClassName);
                // #1                          
                return Enum.valueOf(cl, enumName);
            }
            catch (ClassNotFoundException e)
            {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

}