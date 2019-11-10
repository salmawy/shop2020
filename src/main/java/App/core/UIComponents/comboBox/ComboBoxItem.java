package App.core.UIComponents.comboBox;

public class ComboBoxItem {
	
	private int value ;
	private String text;
	
	
public ComboBoxItem(int value,String text) {
		this.text=text;
		this.value=value;

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
}
