package com.omniselling.common.util;

import java.math.BigDecimal;

public class DoubleUtil {
	private static final int DEF_DIV_SCALE = 2;

	/**
	 * v1 add v2
	 * 
	 * @param v1
	 *            *
	 * @param v2
	 *            *
	 * @return Double
	 */
	public static Double add(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return new Double(b1.add(b2).doubleValue());
	}

	/**
	 * v1 sub v2
	 * 
	 * @param v1
	 *            *
	 * @param v2
	 *            *
	 * @return Double
	 */
	public static Double sub(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return new Double(b1.subtract(b2).doubleValue());
	}

	/**
	 * v1 mul v2
	 * 
	 * @param v1
	 *            *
	 * @param v2
	 *            *
	 * @return Double
	 */
	public static Double mul(Double v1, Double v2) {
		
		return mul(v1.toString(), v2.toString());
	}

	public static Double mul(Double v1, Integer v2) {

		return mul(v1.toString(), v2.toString());
	}

	/**
	 * v1 div v2
	 * 
	 * @param v1
	 *            *
	 * @param v2
	 *            *
	 * @return Double
	 */
	public static Double div(Double v1, Double v2) {
		return div(v1.toString(), v2.toString(), DEF_DIV_SCALE);
	}

	public static Double divRoundUp(Double v1, Double v2) {
		return div(v1.toString(), v2.toString(), DEF_DIV_SCALE);
	}

	public static Double div(Long v1, Long v2) {
		return div(v1.toString(), v2.toString(), DEF_DIV_SCALE);
	}

	public static double div(Integer v1, Double v2, int scale) {
		return div(v1.toString(), v2.toString(), scale);
	}

	/**
	 * v1 div v2
	 * 
	 * @param v1
	 *            *
	 * @param v2
	 *            *
	 * @param scale
	 *            *
	 * @return Double
	 */
	public static Double div(Double v1, Double v2, int scale) {
		return div(v1.toString(), v2.toString(), scale);
	}

	public static Double divRoundUp(Double v1, Double v2, int scale) {
		return divRoundUp(v1.toString(), v2.toString(), scale);
	}

	/**
	 * doubel to int. eg:10.1 return 10 ,10.5 return 11 , 10.5001 return 11
	 * 
	 * @param v
	 * @return
	 */
	public static Double rint(Double v) {
		return round(v, 0);
	}
	
	/**
	 * doubel to int. eg:10.1 return 11
	 * 
	 * @param v
	 * @return
	 */
	public static Double ceil(Double v) {
		return Math.ceil(v);
	}

    public static double div(Long v1, Double v2, int scale)
    {
    	return div(v1.toString(), v2.toString(), scale);
    }

    public static Double add(Double v1, Integer v2)
    {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return new Double(b1.add(b2).doubleValue());
    }

    public static Double round(Double num, int scale)
    {
        if (num == null)
        {
            return null;
        }

        BigDecimal mal = new BigDecimal(num.toString());

        return mal.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

	public static Double floor(Double v) {
		return Math.floor(v);
	}

	protected static Double div(String v1, String v2, int scale) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return new Double(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue());
	}

	protected static Double divRoundUp(String v1, String v2, int scale) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return new Double(b1.divide(b2, scale, BigDecimal.ROUND_UP).doubleValue());
	}
	
	protected static Double mul(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return new Double(b1.multiply(b2).doubleValue());
	}
}
