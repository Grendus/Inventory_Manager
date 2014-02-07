package grendus.business.tools;

public class Item {
	String item_name;
	int number_of_items;
	public Item()
	{
		item_name = "";
		number_of_items = 0;
	}
	
	public Item(String name)
	{
		item_name = name;
		number_of_items = 0;
	}
	
	public Item(String name, int number)
	{
		item_name = name;
		number_of_items = number;
	}
	
	public String getName()
	{
		return item_name;
	}
	
	public int getNumberOfItems()
	{
		return number_of_items;
	}
	
	public void setNumberOfItems(int number)
	{
		number_of_items = number;
	}
	
	

}
