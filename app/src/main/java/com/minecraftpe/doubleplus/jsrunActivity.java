package com.minecraftpe.doubleplus;

import android.os.*;
import android.view.*;
import android.content.*;
import android.view.View.*;
import android.graphics.*;
import java.util.*;
import android.support.v4.widget.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.support.v4.view.*;
import android.support.v7.widget.*;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import java.io.*;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import android.app.Dialog;
import android.app.DialogFragment;


public class jsrunActivity extends AppCompatActivity
{
	String met=null;
	String[] in=new String[256];
	int indat=0;
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(R.color.holo_blue_bright));}
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//			Window window = getWindow();
//// 透明状态栏
//			window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//							WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//			window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);// 透明导航栏
//		}
        setContentView(R.layout.jsrun);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

		toolbar.setTitle("M++");
		toolbar.setNavigationIcon(R.drawable.ret);
		toolbar.setTitleTextColor(0xffffffff);
		toolbar.setSubtitleTextColor(0xffffffff);
		setSupportActionBar(toolbar);

		toolbar.setNavigationOnClickListener(new View.OnClickListener() { 
				@Override 
				public void onClick(View v) { 
					finish();
				} });
	
		//jsgo.setText(runScript(jscode, "Test", new String[]{"helloworld"}));	
	}
	public String runScript(String js, String functionName, Object[] functionParams) {
		Context rhino = Context.enter();
		rhino.setOptimizationLevel(-1);
		try {
			Scriptable scope = rhino.initStandardObjects();

			ScriptableObject.putProperty(scope, "javaContext", Context.javaToJS(jsrunActivity.this, scope));
			ScriptableObject.putProperty(scope, "javaLoader", Context.javaToJS(jsrunActivity.class.getClassLoader(), scope));

			rhino.evaluateString(scope, js, "jsrunActivity", 1, null);

			Function function = (Function) scope.get(functionName, scope);

			Object result = function.call(rhino, scope, scope, functionParams);
			if (result instanceof String) {
				return (String) result;
			} else if (result instanceof NativeJavaObject) {
				return (String) ((NativeJavaObject) result).getDefaultValue(String.class);
			} else if (result instanceof NativeObject) {
				return (String) ((NativeObject) result).getDefaultValue(String.class);
			}
			return result.toString();//(String) function.call(rhino, scope, scope, functionParams);
		} finally {
			Context.exit();
		}
	}
public void addev(View view){
	
	EveDialogFragment f = new EveDialogFragment();
	f.show(getFragmentManager(), "evedialog");
	
}
	public void runj(String str){
	in[indat]=str;
		indat++;
	}
	public void goj(String str){
MppJS mj=new MppJS();
		Intent intent = getIntent();
		String jscode = intent.getStringExtra("jscode")+"\n"+mj.mainString;		
		EditText jsgo = (EditText) findViewById(R.id.jsgo);
		if(str.equals("println")){
		jsgo.setText(jsgo.getText()+runScript(jscode, str, in)+"\n");	
	}
		if(str.equals("printf")){
			jsgo.setText(jsgo.getText()+runScript(jscode, str, in));	
		}
		if(str.equals("toast")){
			Toast.makeText(this,runScript(jscode, str, in) , Toast.LENGTH_LONG).show();		
			
			}
		if(str.equals("toastBar")){
			FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
			Snackbar.make(fab, runScript(jscode, str, in), Snackbar.LENGTH_LONG).show();
			}
		else{
			jsgo.setText(jsgo.getText()+runScript(jscode, str, in));	
			
		}
			
		in=new String[256];
		indat=0;
	}
	
}
class EveDialogFragment extends DialogFragment
{
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		// Get the layout inflater
		builder.setTitle(" ");
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		final View view = inflater.inflate(R.layout.js_dialog, null);
		Button addi = (Button)view.findViewById(R.id.addi);
		final EditText mem = (EditText)view.findViewById(R.id.mem);
		
		addi.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
			 EditText dat = (EditText)view.findViewById(R.id.dat);
					jsrunActivity jsr= (jsrunActivity) getActivity();	
				jsr.runj(dat.getText().toString());	
					dat.setText("");
					}
					});
		builder.setView(view)
			// Add action buttons
			.setPositiveButton("OK",
			new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int id)
				{
					jsrunActivity jsr= (jsrunActivity) getActivity();	
					jsr.goj(mem.getText().toString());		
				}
			}).setNegativeButton("Cancel", null);
		return builder.create();
	
		}
	
}
