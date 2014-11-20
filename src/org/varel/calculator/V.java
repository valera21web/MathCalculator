package org.varel.calculator;


import android.content.res.Resources;

/**
 * @author Valera
 * Class with Global Constant
 */
public class V extends AndriodCalculatorActivity{

   // Pages name
   public static final int PAGE_ACTIVE = 1;

   public static final String PAGE_INDEX = "index";
   public static final int PAGE_INDEX_ID = 1;
   public static final boolean PAGE_INDEX_SHOW = true;

   public static final String PAGE_FUNCTIONS = "function";
   public static final int PAGE_FUNCTIONS_ID = 0;
   public static final boolean PAGE_FUNCTIONS_SHOW = true;

   public static final String PAGE_HISTORY = "history";
   public static final int PAGE_HISTORY_ID = 2;
   public static final boolean PAGE_HISTORY_SHOW = true;

   public static final String PAGE_ABOUT_US = "about_us";
   public static final int PAGE_ABOUT_US_ID = 3;
   public static final boolean PAGE_ABOUT_US_SHOW = false;
   //- Finish pages name

   // Other
   public static final String ERROR = "ERROR";
   public static final String STACK_TEMPLATE = "0v";
   public static final String DEFAULT_RESULT = "0.0";
   public static final String EMPTY_RESULT = "";
   public static final int SIZE_HISTORY_LIST = 100;
   public static final int SIZE_HISTORY_ACTIVE_LIST = 10;
   //- Finish other

   // Types variables
   public static final String TYPE_STRING = "String";
   public static final String TYPE_INTEGER = "Integer";
   public static final String TYPE_INT = "Int";
   public static final String TYPE_LONG = "Long";
   public static final String TYPE_DOUBLE = "Double";
   public static final String TYPE_FLOAT = "Float";
   public static final String TYPE_BLOB = "Blob";
   //- Finish types variables

   // Log names
   public static final String LOG_DB_TEST = "DB_TEST";
   public static final String LOG_ANSWER = "ANS";
   //- Finish log names

   // Chars ASCII
   public static final String CHAR_STR_FACTORIAL = "!";
   public static final int CHAR_INT_FACTORIAL = 33;

   public static final String CHAR_STR_MODULO = "%";
   public static final int CHAR_INT_MODULO = 38;

   public static final String CHAR_STR_LEFT_BRACKET = "(";
   public static final int CHAR_INT_LEFT_BRACKET = 40;

   public static final String CHAR_STR_RIGHT_BRACKET = ")";
   public static final int CHAR_INT_RIGHT_BRACKET = 41;

   public static final String CHAR_STR_MULTIPLICATION = "*";
   public static final int CHAR_INT_MULTIPLICATION = 42;

   public static final String CHAR_STR_PLUS = "+";
   public static final int CHAR_INT_PLUS = 43;

   public static final String CHAR_STR_MINUS = "-";
   public static final int CHAR_INT_MINUS = 45;

   public static final String CHAR_STR_POINT = ".";
   public static final int CHAR_INT_POINT = 46;

   public static final String CHAR_STR_DIVISION = "/";
   public static final int CHAR_INT_DIVISION = 47;

   public static final String CHAR_STR_ANS = "=";
   public static final int CHAR_INT_ANS = 61;

   public static final String CHAR_STR_DEGREE = "^";
   public static final int CHAR_INT_DEGREE = 94;
   //- Finish ASCII chars



   // Mathematics functions
   public static final String FUNCTION_ASIN = "asin";
   public static final String FUNCTION_SIN = "sin";
   public static final String FUNCTION_ACOS = "acos";
   public static final String FUNCTION_COS = "cos";
   public static final String FUNCTION_ATAN = "atan";
   public static final String FUNCTION_TAN = "tan";
   public static final String FUNCTION_LOG = "log";
   public static final String FUNCTION_LG = "lg";
   public static final String FUNCTION_LN = "ln";
   public static final String FUNCTION_EXP = "exp";
   public static final String FUNCTION_ABS = "abs";
   //- Finish mathematics functions

   public static final String VAR_PI = "Pi";
   public static final String VAR_E = "E";
   //- Finish mathematics constant

}
