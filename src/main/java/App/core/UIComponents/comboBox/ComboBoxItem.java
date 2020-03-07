package App.core.UIComponents.comboBox;

public class ComboBoxItem {
	
	private int value ;
	private String text;
	private String parentKey;
	
	
public ComboBoxItem(int value,String text) {
		this.text=text;
		this.value=value;

}
public ComboBoxItem(int value,String text,String parentKey) {
	this.text=text;
	this.value=value;
	this.parentKey=parentKey;

}	

	
public int getValue() {
	return value;
}



public void setValue(int value) {
	this.value = value;
}



public String getText() {
	return text;
}



public void setText(String text) {
	this.text = text;
}



@Override
public String toString() {
	// TODO Auto-generated method stub
	return this.getText();
}



public String getParentKey() {
	return parentKey;
}



public void setParentKey(String parentKey) {
	this.parentKey = parentKey;
}
}
