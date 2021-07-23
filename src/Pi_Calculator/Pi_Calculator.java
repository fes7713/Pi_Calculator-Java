package Pi_Calculator;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Pi_Calculator {
    public static void main(String[] argv)
    {
//        System.out.println(coefficient(0.5, 4));
//        System.out.println(String.format("%.20f", piSeries(8)));
//        System.out.println(root_3(20));
//        BigDecimal a = new BigDecimal("0.00300000090807006005040030215");
//        System.out.println(a);
        System.out.println(Big_root_3(50).divide(new BigDecimal(8)));
        System.out.println(Big_root_3(50/2).divide(new BigDecimal(8)));
        System.out.println(Big_root_3(50));

        System.out.println(new BigDecimal(3).sqrt(new MathContext(50, RoundingMode.HALF_UP)));
//        System.out.println(Big_integrate(0.5, 10, 200));
        System.out.println(Big_piSequence_nTerm(0.5, 10, 50));
        System.out.println(Big_coefficient(0.5, 10, 50));
//        System.out.println(Big_piSeries(3000));
        System.out.println(Big_piSeries_Precise_Root3(20000));
    }

    public static BigDecimal Big_piSeries(int size)
    {
//        size /= 4;
        BigDecimal result = new BigDecimal("0.5").subtract(Big_root_3(size).divide(new BigDecimal(8)));

        result = result.setScale(size, RoundingMode.HALF_UP);
//        System.out.println(result);
        for(int i = 1; i < size+1; i++)
        {
            if(i%(size/100)==0)
                System.out.print("*");
            result = result.subtract(Big_piSequence_nTerm(0.5, i, size));
        }
        return result.multiply(new BigDecimal(12));
    }

    public static BigDecimal Big_piSeries_Precise_Root3(int size)
    {
//        size /= 4;
//        BigDecimal result = new BigDecimal("0.5").subtract(Big_root_3(size).divide(new BigDecimal(8)));
        BigDecimal result = new BigDecimal("0.5").subtract(new BigDecimal(3).sqrt(new MathContext(size, RoundingMode.HALF_UP)).divide(new BigDecimal(8)));

        result = result.setScale(size, RoundingMode.HALF_UP);
//        System.out.println(result);
        for(int i = 1; i < size+1; i++)
        {
            if(i%(size/100)==0)
                System.out.print("*");
            result = result.subtract(Big_piSequence_nTerm(0.5, i, size));
        }
        return result.multiply(new BigDecimal(12));
    }
    public static BigDecimal Big_root_3(int precision)
    {
        BigDecimal result = new BigDecimal(1);
//        result.setScale(precision, RoundingMode.HALF_UP);
        for(int i = 1; i < precision+1; i++)
        {
            result = result.subtract(Big_coefficient(0.5, i, precision).abs().multiply(new BigDecimal("0.25").pow(i)), new MathContext(precision, RoundingMode.HALF_UP));
        }
        return result.multiply(new BigDecimal(2));
    }
    public static BigDecimal Big_piSequence_nTerm(double x, int nTerm, int precision)
    {
        BigDecimal result =  Big_coefficient(x, nTerm, precision).multiply(Big_integrate(x, nTerm, precision), new MathContext(precision, RoundingMode.HALF_UP)).abs();
        result = result.setScale(precision, RoundingMode.HALF_UP);
//        System.out.println(result);
        return result;
    }

    public static BigDecimal Big_coefficient(double constant, int nTerms, int precision)
    {
        BigDecimal result = new BigDecimal(1);
//        result.setScale(precision, RoundingMode.HALF_UP);
        for(int i = 0; i < nTerms; i++)
        {
            BigDecimal multi_result = result.multiply(new BigDecimal(constant - i));
            multi_result = multi_result.add(new BigDecimal("0.2").pow(precision));
            result = multi_result.divide(new BigDecimal(1+i), RoundingMode.HALF_UP);
        }
        return result;
    }

    public static BigDecimal Big_integrate(double x, int nTerm, int precision)
    {
        int coefficient = nTerm * 2 + 1;
        BigDecimal result = new BigDecimal(x).pow(coefficient);
        result = result.add(new BigDecimal("0.2").pow(precision));
        return result.divide(new BigDecimal(coefficient), RoundingMode.HALF_UP);
    }

//    public static double piSeries(int size)
//    {
//        double result = 0.5 - root_3(20)/8;
//        for(int i = 1; i < size+1; i++)
//        {
//            result -= piSequence_nTerm(0.5, i);
//        }
//        return result * 12;
//    }
//    public static double root_3(int precision)
//    {
//        double result = 1;
//        for(int i = 1; i < precision+1; i++)
//        {
//            result -= Math.abs(coefficient(0.5, i)) * Math.pow(0.25, i);
//        }
//        return result * 2;
//    }
//    public static double piSequence_nTerm(double x, int nTerm)
//    {
//        return Math.abs(coefficient(x, nTerm)*integrate(x, nTerm));
//    }
//    public static double coefficient(int constant, int nTerms)
//    {
//        double result = 1;
//        for(int i = 0; i < nTerms; i++)
//        {
//            result *= (constant - i) / (double)(1+i);
//        }
//        return result;
//    }
//    public static double coefficient(double constant, int nTerms)
//    {
//        double result = 1;
//        for(int i = 0; i < nTerms; i++)
//        {
//            result *= (constant - i) / (double)(1+i);
//        }
//        return result;
//    }
//
//    public static double integrate(double x, int nTerm)
//    {
//        int coefficient = nTerm * 2 + 1;
//        return Math.pow(x, coefficient)/coefficient;
//    }
}
