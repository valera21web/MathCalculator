package org.varel.calculator.MathCalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import org.varel.calculator.Search.Search;
import org.varel.calculator.V;

public abstract class RePlace extends Validation {
	
	private static Convert Data = new Convert();
   private static long step = 10000000000l;
	
	public RePlace(){
		super();
	}
	
	protected static String MathFunction(String str) {
		String tmpStr = "";
		double tmpDouble;
		List<Integer> IndexFun = Search.aSort(ReFun(str));
      int countFunctions = IndexFun.size();
		for(int i = 0; i < countFunctions; ++i) {
			tmpStr = str.substring(IndexFun.get(i), str.length());
			char[] StrChar = tmpStr.toCharArray();
			int count = 1, j;
			int t = tmpStr.indexOf(V.CHAR_STR_LEFT_BRACKET);
			for(j = t + 1; count > 0 && j < tmpStr.length(); ++j){
				if((int)StrChar[j] == V.CHAR_INT_LEFT_BRACKET) {
               ++count;
            } else if((int)StrChar[j] == V.CHAR_INT_RIGHT_BRACKET) {
               --count;
            }
			}
			tmpStr = tmpStr.substring(0, j);
         tmpDouble = FunMath(tmpStr);
         str = str.replace(tmpStr, Data.add(tmpDouble));
      }
		str = ReOperand(str);
		return str;
	}
	
	protected static List<Integer> ReFun(String str) {
      String templateForReplace = "aaaaaaaaaa";
		List<Integer> result = new ArrayList<Integer>();
      String[] allFunctions = new String[] {
         V.FUNCTION_ASIN,
         V.FUNCTION_ACOS,
         V.FUNCTION_ATAN,
         V.FUNCTION_SIN,
         V.FUNCTION_COS,
         V.FUNCTION_TAN,
         V.FUNCTION_LOG,
         V.FUNCTION_LG,
         V.FUNCTION_LN,
         V.FUNCTION_EXP,
         V.FUNCTION_ABS
      };

      int countFunctions = allFunctions.length;
      for(int i = 0; i < countFunctions; ++i) {
         String function = allFunctions[i];
         int lengthNameFunction = function.length();
         while(str.indexOf(function) != -1 && !Search.InList(str.indexOf(function), result)) {
            result.add(str.indexOf(function));
            str = str.replaceFirst(function, templateForReplace.substring(0, lengthNameFunction));
         }
      }
	   return result;
	}
	
	protected static double FunMath(String arg) {
		double result = 0.0;
		String fun = arg.substring(0, arg.indexOf(V.CHAR_STR_LEFT_BRACKET));
		String paramStr = arg.substring(arg.indexOf(V.CHAR_STR_LEFT_BRACKET), arg.length());
      paramStr = Brackets(paramStr);
		double paramDouble = EasyStr(paramStr);
      double radians = Math.toRadians(paramDouble);       // value in radians
      double intDouble = 0.0;
		double doubleDouble = 0.0;
		double deg = 0.0;

		if(Search.Are(fun, V.FUNCTION_SIN)) {
			result = Math.sin(radians);
		} else if(Search.Are(fun, V.FUNCTION_ASIN)) {
			if(paramDouble > 1) {
				doubleDouble =  paramDouble % 1;
				intDouble =  paramDouble - doubleDouble;
				deg = 90 * intDouble;
				paramDouble = doubleDouble;
			}
			result = Math.toDegrees(Math.asin(paramDouble));
			result += deg;
		} else if(Search.Are(fun, V.FUNCTION_COS)) {
			result = Math.cos(radians);
		} else if(Search.Are(fun, V.FUNCTION_ACOS)) {
			if(paramDouble > 1) {
				doubleDouble =  paramDouble % 1;
				intDouble =  paramDouble - doubleDouble;
				deg = 90 * intDouble;
				paramDouble = doubleDouble;
			}
			result = Math.toDegrees(Math.acos(paramDouble));
			result = intDouble % 2 == 1 ? deg + (90 - result) : deg + result ;
		} else if(Search.Are(fun, V.FUNCTION_TAN)) {
			paramDouble = paramDouble == 90 ? 0 : Math.toRadians(paramDouble);
			result = Math.tan(paramDouble);
		} else if(Search.Are(fun, V.FUNCTION_ATAN)) {
			result = Math.toDegrees(Math.atan(paramDouble));
		} else if(Search.Are(fun, V.FUNCTION_LOG)) {
			result = Math.log(paramDouble);
		} else if(Search.Are(fun, V.FUNCTION_LG)) {
			result = Math.log10(paramDouble);
		} else if(Search.Are(fun, V.FUNCTION_LN)) {
			result = Math.log1p(paramDouble);
		} else if(Search.Are(fun, V.FUNCTION_EXP)) {
			result = Math.exp(paramDouble);
		} else if(Search.Are(fun, V.FUNCTION_ABS)) {
			result = Math.abs(doubleDouble);
		}
		
		// round return value
		BigDecimal res = new BigDecimal(result);
		res = res.setScale(4, BigDecimal.ROUND_HALF_DOWN);

		return res.doubleValue();
	}
	
	protected static String Pi(String str){
		while(str.indexOf(V.VAR_PI) != -1) {
			str = str.replace(V.VAR_PI, Data.add(Math.PI));
      }
	   return str;
	}

	protected static String E(String str){
		while(str.indexOf(V.VAR_E) != -1) {
         str = str.replace(V.VAR_E, Data.add(Math.E));
      }
	   return str;
	}
	
	protected static String Factorial(String str) {
		int i, key = 0, t = 0;
		double valD = 0, resultDouble = 0;
		String val = "",valSTR = "";
		char[] strChar =  str.trim().toCharArray();
		char[] strCharP = new char[] {'+', '-', '*', '/', '^', '%', '(', ')'};

      Log.d("FACTORUAL", "start(" + str + ")");
		while((t = str.indexOf(V.CHAR_STR_FACTORIAL)) != -1) {
         Log.d("FACTORUAL", "while(charOf = " + t + ")");
         t -= 1;
			if((int)strChar[t] != V.CHAR_INT_RIGHT_BRACKET){
				for(i = t; i >= 0; --i){
					if(!Search.InArray(strChar[i], strCharP)) {
                  key = i;
               } else
                  break;
				}
				val = str.substring(key, str.indexOf(V.CHAR_STR_FACTORIAL));
				valSTR = str.substring(key, str.indexOf(V.CHAR_STR_FACTORIAL));
			} else {
				int count = 1;
				for(i = t - 1; count > 0 && i >= 0; --i){
					if((int)strChar[i] == V.CHAR_INT_RIGHT_BRACKET) {
                  --count;
               } else if((int)strChar[i] == V.CHAR_INT_LEFT_BRACKET) {
                  ++count;
               }
				}
				val = Brackets(str.substring(i + 1, str.indexOf(V.CHAR_STR_FACTORIAL)));
				valSTR = str.substring(i + 1, str.indexOf(V.CHAR_STR_FACTORIAL));
			}
			valD = EasyStr(val);
			resultDouble = Factorial((int) valD);
			str = str.replace(valSTR + V.CHAR_STR_FACTORIAL, Data.add(resultDouble));
			str = ReOperand(str);
			strChar =  str.trim().toCharArray();
		}
      Log.d("FACTORUAL", "end(" + str + ")");
	   return str;
	}
	
	protected static String Modulo(String str) {
		int i, keyL = 0,keyR = 0, t = 0;
		double val1D = 0,val2D = 0, resultDouble = 0;
		String val1 = "", val2 = "",valSTR1 = "",valSTR2 = "";
		char[] strChar =  str.trim().toCharArray();
		char[] strCharP = new char[] {'+', '-', '*', '/', '^', '%', '(', ')'};
		
		while(str.indexOf(V.CHAR_STR_MODULO) != -1) {
			t = str.indexOf(V.CHAR_STR_MODULO)-1;
			if((int) strChar[t] != V.CHAR_INT_RIGHT_BRACKET) {
				for(i = t; i >= 0; --i) {
					if(!Search.InArray(strChar[i], strCharP))
						keyL = i;
					else
						break;
				}
				val1 = str.substring(keyL, str.indexOf(V.CHAR_STR_MODULO));
				valSTR1 = str.substring(keyL, str.indexOf(V.CHAR_STR_MODULO));
			} else {
				int count = 1;
				for(i = t - 1; count > 0 && i >= 0; --i) {
					if((int)strChar[i] == V.CHAR_INT_LEFT_BRACKET) {
						--count;
					} else if((int)strChar[i] == V.CHAR_INT_RIGHT_BRACKET) {
                  ++count;
               }
            }
				val1 = Brackets( str.substring(i + 1,str.indexOf(V.CHAR_STR_MODULO)) );
				valSTR1 = str.substring(i + 1, str.indexOf(V.CHAR_STR_MODULO));
			}
			
			if((int)strChar[t + 2] != V.CHAR_INT_LEFT_BRACKET) {
				for(i = t + 2; i < str.length(); ++i){
					if(!Search.InArray(strChar[i], strCharP)) {
                  keyR = i;
               } else
						break;
				}
				val2 = str.substring(t + 2, keyR + 1);
				valSTR2 = str.substring(t + 2, keyR + 1);
			} else {
				int count = 1;
				for(i = t + 3; count > 0 && i < str.length(); ++i) {
					if((int)strChar[i] == V.CHAR_INT_LEFT_BRACKET) {
                  ++count;
               } else if((int)strChar[i] == V.CHAR_INT_RIGHT_BRACKET) {
                  --count;
               }
				}
				val2 = Brackets(str.substring(t + 2, i));
				valSTR2 = str.substring(t + 2, i);
			}
			val1D = EasyStr(val1);
			val2D = EasyStr(val2);
			resultDouble = val1D % val2D;
			str = str.replace(valSTR1 + V.CHAR_STR_MODULO + valSTR2, Data.add(resultDouble));
			str = ReOperand(str);
			strChar =  str.trim().toCharArray();
		} 
	  return str;
	}
	
	protected static String Brackets(String str){
		String strRep = "";
		char[] strChar =  str.trim().toCharArray();
		int i, t;
		int count = 0;
		
		while((t = str.indexOf(V.CHAR_STR_LEFT_BRACKET)) != -1) {
			count = 1;
			for(i = t + 1; count > 0 && i >= 0; ++i){
				if((int)strChar[i] == V.CHAR_INT_LEFT_BRACKET) {
               t = i;
            } else if((int)strChar[i] == V.CHAR_INT_RIGHT_BRACKET) {
					--count;
            }
			}
			strRep = str.substring(t, i);
			str = str.replace(strRep, Data.add(SubBrackets(strRep)));
			str = ReOperand(str);
			strChar =  str.trim().toCharArray();
		}
	  return str;
	}

	protected static double SubBrackets(String str){
	  double result = 0;
		str = str.substring(1, str.length() - 1);
		str = Factorial(str);         
		str = Modulo(str);            
		str = Degree(str);  
		str = Multiplication(str);
		str = Division(str);
		result = EasyStr(str);
	  return result;
	}

	protected static String Multiplication(String str){
		int i, key = 0, t = 0;
		double val1D = 0, val2D = 0, resultDouble = 0;
		String val1 = "", val2 = "";
		char[] strChar =  str.trim().toCharArray();
		char[] strCharP = new char[] {'+', '-', '*', '/', '^'};
		
		while(str.indexOf(V.CHAR_STR_MULTIPLICATION) != -1) {
			t = str.indexOf(V.CHAR_STR_MULTIPLICATION) - 1;
			for(i = t; i >= 0; --i) {
				if(!Search.InArray(strChar[i],strCharP))
					key = i;
                else
					break;
			}
			val1 = str.substring(key,str.indexOf(V.CHAR_STR_MULTIPLICATION));
			val1D = EasyStr(val1);
			
			t = str.indexOf(V.CHAR_STR_MULTIPLICATION) + 1;
			key = t;
			for(int j = t + 1; j < str.length(); ++j){
				if(!Search.InArray(strChar[j], strCharP)) {
					key = j;
            } else {
					break;
            }
			}
			val2 = str.substring(str.indexOf(V.CHAR_STR_MULTIPLICATION) + 1, key + 1);
			val2D = EasyStr(val2);
			
			resultDouble = val1D * val2D;
			str = str.replace(val1 + V.CHAR_STR_MULTIPLICATION + val2, Data.add(resultDouble));
			str = ReOperand(str);
			strChar =  str.trim().toCharArray();
		} 
		
		return str;
	}
	
	protected static String Division(String str){
		int i, key = 0, t = 0;
		double val1D = 0, val2D = 0, resultDouble = 0;
		String val1 = "", val2 = "";
		char[] strChar =  str.trim().toCharArray();
		char[] strCharP = new char[] {'+', '-', '*' ,'/', '^'};
		
		while(str.indexOf(V.CHAR_STR_DIVISION) != -1) {
			t = str.indexOf(V.CHAR_STR_DIVISION) - 1;
			for(i = t; i >= 0; --i) {
				if(!Search.InArray(strChar[i], strCharP)) {
					key = i;
            } else {
					break;
            }
			}
			val1 = str.substring(key,str.indexOf(V.CHAR_STR_DIVISION));
			val1D = EasyStr(val1);
			
			t = str.indexOf(V.CHAR_STR_DIVISION) + 1;
			key = t;
			for(int j = t + 1; j < str.length(); ++j) {
				if(!Search.InArray(strChar[j], strCharP))  {
					key = j;
            } else {
					break;
            }
			}
			val2 = str.substring(str.indexOf(V.CHAR_STR_DIVISION) + 1, key + 1);
			val2D = EasyStr(val2);
			
			resultDouble = val1D / val2D;
			str = str.replace(val1 + V.CHAR_STR_DIVISION + val2,  Data.add(resultDouble));
			str = ReOperand(str);
			strChar =  str.trim().toCharArray();
		} 
		
		return str;
	}
	
	protected static String Degree(String str){
		int i, key = 0, korId = 0;
		char[] strChar =  str.trim().toCharArray();
		char[] strCharP = new char[]{'+', '-', '*', '/', '^'};
		while(str.indexOf(V.CHAR_STR_DEGREE) != -1) {
			double resQuad = 1;
			String val = "",valAll = "", step = "",stepAll = "";
			double valD = 0, stepD = 0;
			int t;
			t = str.indexOf(V.CHAR_STR_DEGREE) - 1;
			for(i = t; i >= 0; --i) {
				if(!Search.InArray(strChar[i],strCharP)) {
					key = i;
            } else {
					break;
            }
			}
			val = str.substring(key,str.indexOf(V.CHAR_STR_DEGREE) + korId);
			valAll = korId == -1 ? V.CHAR_STR_LEFT_BRACKET + val + V.CHAR_STR_RIGHT_BRACKET : val ;
			valD = EasyStr(val);
			
			t = str.indexOf(V.CHAR_STR_DEGREE) + 1;
			key = t;
            korId = 0;
			for(int j = t+1; j<str.length(); ++j) {
				if(!Search.InArray(strChar[j],strCharP)) {
					key = j;
            } else {
					break;
            }
			}
			step = str.substring(str.indexOf(V.CHAR_STR_DEGREE) + 1, key + 1);
			stepAll = korId == 1 ? V.CHAR_STR_LEFT_BRACKET + step + V.CHAR_STR_RIGHT_BRACKET : step ;
			stepD = EasyStr(step);
			
			resQuad = Math.pow(valD, stepD);
			str = str.replace(valAll + V.CHAR_STR_DEGREE + stepAll, Data.add(resQuad));
			str = ReOperand(str);
			strChar =  str.trim().toCharArray();
		}
		return str;
	}
	
	protected static double EasyStr(String str) {
      Log.d("CALCIL", "str=" + str);
		int i;
		double result = 0;
		String tmp = "";
		char[] strChar =  str.trim().toCharArray();
		char[] CharArray = new char[]{'+', '-'};
		List<Double> listVal = new ArrayList<Double>();
		List<Integer> listOperandInt = new ArrayList<Integer>();
		List<String> listOperand = new ArrayList<String>();
		
		tmp += strChar[0];
		for(i = 1; i < strChar.length; ++i) {
			if(Search.InArray(strChar[i], CharArray)){
				if(tmp.indexOf(V.STACK_TEMPLATE) != -1) {
					listVal.add(Data.get(tmp));
            } else {
					listVal.add(Double.parseDouble(tmp));
            }

				listOperand.add(Character.toString(strChar[i]));
				listOperandInt.add((int)strChar[i]);
				tmp = "";
			} else {
				tmp += strChar[i];
         }
		}    
		if(tmp.indexOf(V.STACK_TEMPLATE) != -1) {
			listVal.add(Data.get(tmp));
      } else {
			listVal.add(Double.parseDouble(tmp));
      }
		
		result += listVal.get(0);
      for(i = 0; i < listOperand.size(); ++i) {
         if(listOperandInt.get(i) == V.CHAR_INT_PLUS) {
            result = plus(result, listVal.get(i + 1));
         } else if(listOperandInt.get(i) == V.CHAR_INT_MINUS) {
            result = minus(result, listVal.get(i + 1));
         }
      }
		return result;
	}
	
	public static long Factorial(int val) {
		long result = 1L; 
		if(val > 1) {
			for(int i = 1; i <= val; ++i) {
            result *= i;
         }
      } else if(val == 0) {
			result = 0;
      }
		return result;
	}

   private static double minus(double val1, double val2) {
      return ((double) ((val1 * step) - (val2 * step))) / step;
   }

   public static double plus(double val1, double val2) {
      return ((double) ((val1 * step) + (val2 * step))) / step;
   }
}