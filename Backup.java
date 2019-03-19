import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class Backup {

	public static final File propertiesFile = new File("Z:\\Properties.ser");

	private static JFileChooser fileChooser = new JFileChooser();

	public static ArrayList<Task> restoreList(int index) {
		ArrayList<String> properties = (ArrayList<String>) deserializeObject(Backup.propertiesFile);
		String path = properties.get(index);

		ArrayList<Task> list = (ArrayList<Task>) deserializeObject(new File(path));
		return list;
	}

	public static void saveFile(Component parent, ArrayList<Task> list) {
		int returnValue = fileChooser.showSaveDialog(parent);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			serializeObject(list, file);
			appendProperties(file);
		}
	}

	public static void refreshProperties() {
		ArrayList<String> properties = (ArrayList<String>) deserializeObject(Backup.propertiesFile);
		for (String filePath : properties) {
			if (!new File(filePath).exists()) {
				properties.remove(filePath);
			}
		}

		serializeObject(properties, Backup.propertiesFile);
	}

	private static void appendProperties(File file) {
		ArrayList<String> properties = (ArrayList<String>) deserializeObject(Backup.propertiesFile);
		properties.add(file.getPath());

		serializeObject(properties, Backup.propertiesFile);
	}

	private static void serializeObject(ArrayList object, File file) {
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(object);
			objectOut.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static ArrayList deserializeObject(File file) {
		ArrayList deserialized;

		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			deserialized = (ArrayList) objectIn.readObject();
			objectIn.close();
			fileIn.close();

			return deserialized;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

}



