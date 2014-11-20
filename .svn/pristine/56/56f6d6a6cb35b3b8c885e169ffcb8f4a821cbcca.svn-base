package org.varel.calculator.MathCalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.varel.calculator.MessageView;
import org.varel.calculator.R;
import org.varel.calculator.DB.DB;
import org.varel.calculator.DB.Read.DBRead;
import org.varel.calculator.DB.Write.DBWrite;
import org.varel.calculator.Search.Search;
import org.varel.calculator.Stack.Stack;
import android.content.ContentValues;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import org.varel.calculator.V;

public class Calculator extends RePlace  {
	private DBWrite db_write;
	private DBRead db_read;
	private Context Content;
	private Stack last_math_str = new Stack(10);

	
	public Calculator(Context C) {
		Content = C;
      db_write = new DBWrite(Content);
      db_read = new DBRead(Content);
	}
	
	public String ans(String mathFormula, EditText editText){
      if(mathFormula.length() == 0) {
         return V.DEFAULT_RESULT;
      }
      String resultString = V.DEFAULT_RESULT;

      if(mathFormula != ReOperand(mathFormula)) {
         int SelectStart = editText.getSelectionStart();
         String leftFormula = mathFormula.substring(0, SelectStart);
         String rightFormula = mathFormula.substring(SelectStart, mathFormula.length());

         leftFormula = ReOperand(leftFormula);
         rightFormula = ReOperand(rightFormula);

         mathFormula = leftFormula + rightFormula;
         editText.setText(mathFormula);
         editText.setSelection(leftFormula.length());
      }

      String[] sizeText = Content.getResources().getStringArray(R.array.sizeTextMathString);

		if(mathFormula.length() >= 40) {
			editText.setTextSize(Integer.parseInt(sizeText[0]));
		} else if(mathFormula.length() >= 35) {
			editText.setTextSize(Integer.parseInt(sizeText[1]));
		} else if(mathFormula.length() >= 28) {
			editText.setTextSize(Integer.parseInt(sizeText[2]));
		} else if(mathFormula.length() >= 22) {
			editText.setTextSize(Integer.parseInt(sizeText[3]));
		} else if(mathFormula.length() >= 16) {
			editText.setTextSize(Integer.parseInt(sizeText[4]));
		} else if(mathFormula.length() >= 1 ) {
			editText.setTextSize(Integer.parseInt(sizeText[5]));
		}
		
		switch(Valid(mathFormula)) {
         case 0:
            resultString = V.ERROR;
            break;
			case 1:
				mathFormula = Optimization(mathFormula, editText);
				try {
					mathFormula = Pi(mathFormula);
					mathFormula = E(mathFormula);

					mathFormula = MathFunction(mathFormula);                  //rePlace all Math Function [ sin(), cos(), ln() .... ]
					//mathFormula = Factorial(mathFormula);                     //rePlace all Factorial [ ! ]
					mathFormula = Modulo(mathFormula);                        //rePlace all Modulo [ % ]
					mathFormula = Brackets(mathFormula);                      //rePlace all Brackets [ ( ) ]
					mathFormula = Degree(mathFormula);                        //rePlace all Degree[ x^y ]
					mathFormula = Multiplication(mathFormula);                //rePlace all Multiplication [ * ]
					mathFormula = Division(mathFormula);                      //rePlace all Division [ / ]
               Double tm = EasyStr(mathFormula);                         //summ(mathFormula)
					BigDecimal res = new BigDecimal(tm);
					res = res.setScale(10, BigDecimal.ROUND_HALF_DOWN);       // round
               resultString = Double.toString(res.doubleValue());
				} catch(Exception ex) {
               MessageView.showError(Content, Content.getResources().getString(R.string.error_format), MessageView.LENGTH_SHORT);
               resultString = V.ERROR;
				}
				break;
			case 2:
            MessageView.showError(Content, Content.getResources().getString(R.string.error_with_operator), MessageView.LENGTH_SHORT);
            resultString = V.ERROR;
				break;
			case 3:
            MessageView.showError(Content, Content.getResources().getString(R.string.error_with_brackets), MessageView.LENGTH_SHORT);
            resultString = V.ERROR;
            break;
		}
	   return resultString;
	}
	
	public boolean saveToHistory(String math_row, Double answer) {
		if(Valid(math_row) == 1) {
			if(!Search.is_integer(math_row) && !last_math_str.inStack(math_row)) {
				db_write.Query("" +
                    "INSERT INTO " + DB.TABLE_NAME_MATH_HISTORY + " (" + DB.MATH_STR + "," + DB.MATH_ANS + ") " +
                    "VALUES('" + math_row + "', '" + answer + "')"
            );
				last_math_str.add(math_row);
				return true;
			}
		}
		return false;
	}

	public void getHistoryList(ListView historyList, int num) {
      String query = "" +
         "SELECT "+ DB.MATH_STR +"," + DB.MATH_ANS + " " +
         "FROM "+ DB.TABLE_NAME_MATH_HISTORY + " " +
         "ORDER BY " + DB.MATH_CREATE + " DESC" + " " +
         "LIMIT "+ num + "" +
      "";
      List<String> print_list = new ArrayList<String>();
		ContentValues TMP = new ContentValues();
      String res_str = "";
      List<ContentValues> list = db_read.Query(query, new String[] {DB.MATH_STR, DB.MATH_ANS}, new String[] {V.TYPE_STRING, V.TYPE_STRING});
      int count = list.size();
      for(int i = 0; i < count; ++i) {
         TMP = list.get(i);
         if(TMP.getAsString(DB.MATH_STR) != null && TMP.getAsString(DB.MATH_ANS) != null) {
            res_str = TMP.getAsString(DB.MATH_STR) + "=" + TMP.getAsString(DB.MATH_ANS);
            print_list.add(res_str);
         } else {
            print_list.add(Content.getString(R.string.emptyHistory));
         }
      }
      ArrayAdapter<String> adapter = new ArrayAdapter<String>(Content, android.R.layout.simple_list_item_1, print_list);
      historyList.setAdapter(adapter);
	}

	public void initHistoryList(int num) {
		if(last_math_str.empty()) {
			String query = "" +
				"SELECT "+ DB.MATH_STR +"," + DB.MATH_ANS + " " +
				"FROM "+ DB.TABLE_NAME_MATH_HISTORY + " " +
				"ORDER BY " + DB.MATH_CREATE + " DESC" + " " +
				"LIMIT "+ num + ""
			;
			List<ContentValues> list = db_read.Query(
                 query,
                 new String[] {DB.MATH_STR, DB.MATH_ANS},
                 new String[] {V.TYPE_STRING, V.TYPE_STRING}
         );
			int count = list.size();
			for(int i = 0; i < count; ++i) {
				last_math_str.add(list.get(i).getAsString(DB.MATH_STR));
			}
		}
	}

}
