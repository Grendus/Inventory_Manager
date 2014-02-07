package grendus.business.tools;

import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import java.io.FileOutputStream;

public class Save extends Activity implements OnClickListener 
{
	private Button save_button;
	private TextView output;
	private EditText input;
	private String save_data;
	
	public static final String FILE_RECORD = "grendus.business.tools.savedata";
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_save_menu);
        save_button = (Button)findViewById(R.id.save_load_button);
        save_button.setOnClickListener(this);
	    save_button.setText("Save");
        output = (TextView)findViewById(R.id.file_listing);
        input = (EditText)findViewById(R.id.filename_text);
        
        
	    save_data = getIntent().getExtras().getString(FILE_RECORD);
	    
        reloadOutput();
	}
	public void onClick(View v)
	{
		switch(v.getId())
		{
		case R.id.save_load_button: if(!input.getText().equals(""))
									{
										try
										{
											String temp = input.getText().toString();
											FileOutputStream fos = openFileOutput(temp, Context.MODE_PRIVATE);
											fos.write(save_data.getBytes());
											reloadOutput();
										}
										catch(Exception e)
										{
											output.setText(e.getMessage());
										}
									}
		}
	}
	
	public void reloadOutput()
	{
		output.setText("");
		String[] files = fileList();
        for(String filename:files)
        	output.append(filename+"\n");
	}
}

