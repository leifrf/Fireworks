import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * The purpose of this class is to provide a colour string source.
 * All colours stored as final strings in Color are given here.
 * 
 * @author Leif Raptis-Firth
 *
 */
public class NewColour {
	
	private static ArrayList<String> colors = createColorList();
	
	/**
	 * 
	 * @return generated list of colour strings from the java.awt.Color class
	 */
	private static ArrayList<String> createColorList(){
		Field[] fields = java.awt.Color.class.getDeclaredFields();
		String currentField;
		ArrayList<String> colorsList = new ArrayList<>();
		for (int i = 0; i < fields.length - 1; i++){
			currentField = fields[i].getName();
			if (currentField.toUpperCase() == currentField)
				colorsList.add(currentField);
		}
		return colorsList;
	}
	
	/**
	 * 
	 * @return a random colour from the java.awt.Color class
	 */
	public static String getRandomColour(){
		Field[] fields = java.awt.Color.class.getDeclaredFields();
		int index = (int)(colors.size() * Math.random());
		return colors.get(index);
	}
	
}
