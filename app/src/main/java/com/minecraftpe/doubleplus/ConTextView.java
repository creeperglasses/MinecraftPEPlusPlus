package com.minecraftpe.doubleplus;
import android.content.*;
import android.util.*;
import android.widget.*;
import android.graphics.*;

public class ConTextView extends TextView{

	public ConTextView(Context context) {
super(context);
}

	public ConTextView(Context context, AttributeSet attrs) {
super(context,attrs);
}

	public ConTextView(Context context,AttributeSet attrs,int defStyle){
super(context,attrs,defStyle);
}
	
@Override
public boolean isFocused(){
return true;
}
}
