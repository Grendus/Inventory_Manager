package grendus.business.tools;

import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;

public class Delete extends Activity implements OnClickListener 
{
	private Button delete_button;
	private TextView output;
	private EditText input;

	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_save_menu);
        delete_button = (Button)findViewById(R.id.save_load_button);
        delete_button.setOnClickListener(this);
	    delete_button.setText("Delete");
        output = (TextView)findViewById(R.id.file_listing);
        input = (EditText)findViewById(R.id.filename_text);
        
        reloadOutput();
	}
	
	public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.save_load_button: try
										{
											deleteFile(input.getText().toString());
											reloadOutput();
										}
										catch(Exception e)
										{
											output.setText(e.getMessage());
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