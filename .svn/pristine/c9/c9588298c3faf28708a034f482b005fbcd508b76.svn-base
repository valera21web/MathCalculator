package org.varel.calculator.MathCalculator;

import android.util.Log;
import android.widget.EditText;
import org.varel.calculator.Search.Search;
import org.varel.calculator.V;

public abstract class Validation {
	
	public static int Valid(String str) {
      char[] char_str = str.toCharArray();
      char[] strChar = new char[] {'+','-','*','/','^','%','!'};
      int count = char_str.length;
      if(Search.InArray(char_str[count - 1], strChar)) {
         return 0;
      }

      boolean flLastOperandChar = false;
      int i;
      int brackets = 0;

      for(i = 0; i < count; ++i){
         if((int) char_str[i] == V.CHAR_INT_LEFT_BRACKET) {
            ++brackets;
         } else if((int) char_str[i] == V.CHAR_INT_RIGHT_BRACKET) {
            --brackets;
         }

         if(Search.InArray(char_str[i], strChar)) {
            if(flLastOperandChar) {
               return 2; // two char operator
            }
            flLastOperandChar = true;
         } else {
            flLastOperandChar = false;
         }
      }

      if(brackets != 0){
         return 3; // error with brackets
      }

      return 1;
   }

	public static String Optimization(String str, EditText pole){
		int Select = pole.getSelectionStart();
		char[] strChar = new char[]{'+','-','*','/','^','%'};
		char[] char_str = ReOperand(str).toCharArray();
		
		String left_str = "";
		String right_str = "";
		
		int count_str = char_str.length;
		
		for(int i = 1; i < count_str - 1; ++i) {
			if((int)char_str[i] >= 0x41 && (int)char_str[i] <= 0x7A && (int)char_str[i] != V.CHAR_INT_DEGREE) {
            Log.d("Valid", Character.toString(char_str[i]));
				if(!Search.InArray(char_str[i - 1], strChar)) {
					if((int)char_str[i - 1] < 0x41 && (int)char_str[i - 1] != V.CHAR_INT_LEFT_BRACKET) {
						if(i < Select) {
                     ++Select;
                  }
						left_str = str.substring(0, i) + V.CHAR_STR_MULTIPLICATION;
						right_str = str.substring(i, count_str);
						str = left_str + right_str;
						char_str = str.toCharArray();
						count_str = char_str.length;
					}
				}
			} else if((int)char_str[i]  == V.CHAR_INT_LEFT_BRACKET) {
				if(!Search.InArray(char_str[i - 1], strChar)) {
					if((int)char_str[i - 1] < 0x41 && char_str[i - 1] != V.CHAR_INT_LEFT_BRACKET) {
                  if(i < Select) {
                     ++Select;
                  }
						left_str = str.substring(0, i) + V.CHAR_STR_MULTIPLICATION;
						right_str = str.substring(i, count_str);
						str = left_str + right_str;
						char_str = str.toCharArray();
						count_str = char_str.length;
					}
				}
			} else if((int)char_str[i]  == V.CHAR_INT_LEFT_BRACKET){
				if(!Search.InArray(char_str[i + 1], strChar)){
					if((int)char_str[i + 1]  != V.CHAR_INT_RIGHT_BRACKET){
                  if(i < Select) {
                     ++Select;
                  }
						left_str = str.substring(0, i + 1) + V.CHAR_STR_MULTIPLICATION;
						right_str = str.substring(i + 1, count_str);
						str = left_str + right_str;
						char_str = str.toCharArray();
						count_str = char_str.length;
					}
				}
			}
		}
		pole.setText(str);
		pole.setSelection(Select);
		return str;
	}

	protected static String ReOperand(String str){
      if(str.indexOf("--") != -1) {
         str = str.replace("--", "+");
      }
      if(str.indexOf("++") != -1) {
         str = str.replace("++", "+");
      }
      if(str.indexOf("+-") != -1) {
         str = str.replace("+-", "-");
      }
      if(str.indexOf("-+") != -1) {
         str = str.replace("-+", "-");
      }
	   return str;
	}
}
