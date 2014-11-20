package org.varel.calculator;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public abstract class MessageView {

   public final static Integer LENGTH_LONG = Toast.LENGTH_LONG;
   public final static Integer LENGTH_SHORT = Toast.LENGTH_SHORT;
   public final static Integer DEFAULT_LENGTH = Toast.LENGTH_SHORT;


   private static void show(Context pContext, String pText, int pLayout) {
      MessageView.show(pContext, pText, pLayout, MessageView.DEFAULT_LENGTH);
   }

   private static void show(Context pContext, String pText, int pLayout, int length) {
      LayoutInflater inflater = LayoutInflater.from(pContext);
      View layout = inflater.inflate(pLayout, (ViewGroup) ((Activity)pContext).findViewById(R.id.toast_layout_root));

      TextView text = (TextView) layout.findViewById(R.id.text);
      text.setText(pText);

      Toast toast = new Toast(pContext.getApplicationContext());
      toast.setGravity(Gravity.CENTER_VERTICAL, 0, -100);
      toast.setDuration(length);
      toast.setView(layout);
      toast.show();
   }
   public static void showError(EditText editText, String errorText) {
      editText.setError(errorText);
   }

   public static void showError(Context pContext, String errorText) {
      MessageView.show(pContext, errorText, R.layout.custom_toast_error);
   }

   public static void showError(Context pContext, String errorText, int length) {
      MessageView.show(pContext, errorText, R.layout.custom_toast_error, length);
   }

   public static void showInfo(Context pContext, String infoText) {
      MessageView.show(pContext, infoText, R.layout.custom_toast_info);
   }

   public static void showInfo(Context pContext, String infoText, int length) {
      MessageView.show(pContext, infoText, R.layout.custom_toast_info, length);
   }
}