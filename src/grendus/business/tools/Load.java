package grendus.business.tools;

import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Load extends Activity implements OnClickListener 
{
	private Button load_button;
	private TextView output;
	private EditText input;

	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_save_menu);
        load_button = (Button)findViewById(R.id.save_load_button);
        load_button.setOnClickListener(this);
	    load_button.setText("Load");
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
											FileInputStream fis = openFileInput(input.getText().toString());
											String saved_data = "";
											int readByte = fis.read();
											while(readByte != -1)		//read the file byte by byte, converting each one to a character, and store it in the string
											{
												saved_data+=(char)readByte;
												readByte = fis.read();
											}
											output.setText(saved_data);
											ArrayList<Item> tempInventory = new ArrayList<Item>();
											String[] parsedString = saved_data.split("~");
											output.append(""+parsedString.length);
											for(int i = 0; i<parsedString.length; i+=2)
											{
												tempInventory.add(new Item(parsedString[i], Integer.parseInt(parsedString[i+1])));
											}
											InventoryTrackerActivity.items = tempInventory;
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