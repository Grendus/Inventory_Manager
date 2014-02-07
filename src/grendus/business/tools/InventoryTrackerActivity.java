package grendus.business.tools;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.widget.TextView;
import java.util.ArrayList;
import android.content.Intent;

public class InventoryTrackerActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
    private TextView output;
    private EditText input;
    private Button next_item;
    private Button previous_item;
    private Button plus_1;
    private Button plus_5;
    private Button minus_1;
    private Button minus_5;
    private Button add_item;
    private Button remove_item;
    private Button save_button;
    private Button load_button;
    private Button delete_button;
    public static ArrayList<Item> items;
    int current_item;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        output = (TextView)findViewById(R.id.inventory_display);	//locate and initialize the objects from the XML sheet.
        input = (EditText)findViewById(R.id.new_item_name);
        input.setOnClickListener(this);
        next_item = (Button)findViewById(R.id.up_one);
        next_item.setOnClickListener(this);
        previous_item = (Button)findViewById(R.id.down_one);
        previous_item.setOnClickListener(this);
        plus_1 = (Button)findViewById(R.id.add_1);
        plus_1.setOnClickListener(this);
        plus_5 = (Button)findViewById(R.id.add_5);
        plus_5.setOnClickListener(this);
        minus_1 = (Button)findViewById(R.id.sell_1);
        minus_1.setOnClickListener(this);
        minus_5 = (Button)findViewById(R.id.sell_5);
        minus_5.setOnClickListener(this);
        add_item = (Button)findViewById(R.id.new_item);
        add_item.setOnClickListener(this);
        remove_item = (Button)findViewById(R.id.remove_item);
        remove_item.setOnClickListener(this);
        save_button = (Button)findViewById(R.id.save_button);
        save_button.setOnClickListener(this);
        load_button = (Button)findViewById(R.id.load_button);
        load_button.setOnClickListener(this);
        delete_button = (Button)findViewById(R.id.delete_button);
        delete_button.setOnClickListener(this);
        
        items = new ArrayList<Item>();							//initialize the internal storage
        current_item = 0;
    }
    
    public void onClick(View v)
    {
    	switch(v.getId())
    	{
    		case R.id.new_item: if(!input.getText().toString().equals(""))				//if new item button is pressed, add a new item with the text from the input field
    							{	
    								items.add(new Item(input.getText().toString(), 0));
    							}
    							break;
    		case R.id.remove_item:	if(current_item < items.size() && current_item >= 0)	//if remove item button is pressed, remove the currently selected item from the list
    								{
    									items.remove(current_item);
    								}
    								if(current_item>=items.size())
    									current_item = items.size()-1;
    								reloadOutput();
    								break;
    		case R.id.up_one:	if(current_item > 0)		//if the up arrow is pressed, decrement the counter unless it's on the first item in the list
    							{	
    								current_item--;
    							}
    							break;
    		case R.id.down_one:	if(current_item < items.size()-1)		//if the down arrow is pressed, increment the counter unless it's on the last item in the list
    							{
    								current_item++;
    							}
    							break;
    		case R.id.add_1:	if(items.get(current_item)!=null)		//if the pointer references an existing item, increase it by one
								{
									items.get(current_item).setNumberOfItems(items.get(current_item).getNumberOfItems()+1);
								}
								break;
    		case R.id.add_5:	if(items.get(current_item)!=null)		//if the pointer references an existing item, increase it by five
								{
									items.get(current_item).setNumberOfItems(items.get(current_item).getNumberOfItems()+5);
								}
								break;
    		case R.id.sell_1:	if(items.get(current_item)!=null && items.get(current_item).getNumberOfItems()>0)	//if the pointer references an existing item and we have at least one, decrease it by one
								{
									items.get(current_item).setNumberOfItems(items.get(current_item).getNumberOfItems()-1);
								}
								break;
    		case R.id.sell_5:	if(items.get(current_item)!=null && items.get(current_item).getNumberOfItems()>4) //if the pointer referenes an existing item and we have at least five, decrease it by five
								{
									items.get(current_item).setNumberOfItems(items.get(current_item).getNumberOfItems()-5);
								}
								break;
    		case R.id.save_button:Intent save_menu = new Intent(this,Save.class);
    							try
    							{
    								String save_me = "";
    								for(int i = 0; i<items.size(); i++)												//string-ize the entire inventory
    									save_me += items.get(i).getName()+"~"+items.get(i).getNumberOfItems()+"~";
    								save_menu.putExtra(Save.FILE_RECORD, save_me);									//send it to the save screen
    								startActivity(save_menu);														//open the save screen
    							}
    							catch(Exception a)
    							{
    								items.add(new Item(a.getMessage(), 999));										//an ugly hack to get the output to display the error message
    								reloadOutput();
    							}
								break;

    		case R.id.load_button:Intent load_menu = new Intent(InventoryTrackerActivity.this,Load.class);			//load
    		 					startActivity(load_menu);
    							break;
    		case R.id.delete_button:Intent delete_menu = new Intent(InventoryTrackerActivity.this,Delete.class);			//load
								startActivity(delete_menu);
								break;
    	}
    	reloadOutput();
    }
    
    private void reloadOutput()
    {
    	output.setText("");	//clear the output window
    	for(int i = 0; i<items.size(); i++)
    	{
    		if(i == current_item)																		//if this is the currently selected item
    			output.append("* "+items.get(i).getNumberOfItems()+" "+items.get(i).getName()+"\n");	//put an asterisk in front of it
    		else																						//if this isn't the currently selected item
    			output.append("   "+items.get(i).getNumberOfItems()+" "+items.get(i).getName()+"\n");	//put whitespace in front of it
    	}
    	
    }
    
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	super.onCreateOptionsMenu(menu);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.options_menu, menu);
    	output.append("menu pressed");
    	return true;
    }
}