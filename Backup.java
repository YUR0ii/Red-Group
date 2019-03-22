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

	static {
		createPropertiesFile();
	}

	public static ArrayList<Task> restoreList(int index) {
		createPropertiesFile();
		ArrayList<String> properties = (ArrayList<String>) deserializeObject(Backup.propertiesFile);
		String path = properties.get(index);

		ArrayList<Task> list = (ArrayList<Task>) deserializeObject(new File(path));
		return list;
	}

	public static void saveList(Component parent, ArrayList<Task> list) {
		int returnValue = fileChooser.showSaveDialog(parent);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			serializeObject(list, file);
			// Append properties only if it is unique to prevent duplicates
			if (!exists(file)) {
				appendProperties(file);
			}
		}
	}

	public static ArrayList<String> getBackupNames() {
		ArrayList<String> filePaths = getBackups();
		ArrayList<String> names = new ArrayList<String>();

		for (String path : filePaths) {
			int index = path.lastIndexOf("\\");
			names.add(path.substring(index + 1));
		}

		return names;
	}

	public static ArrayList<String> getBackups() {
		refreshProperties();
		ArrayList<String> filePaths = (ArrayList<String>) deserializeObject(Backup.propertiesFile);

		return filePaths;
	}

	private static void appendProperties(File file) {
		refreshProperties();
		ArrayList<String> properties = (ArrayList<String>) deserializeObject(Backup.propertiesFile);
		properties.add(file.getPath());

		serializeObject(properties, Backup.propertiesFile);
	}

	private static boolean exists(File file) {
		refreshProperties();
		ArrayList<String> properties = (ArrayList<String>) deserializeObject(Backup.propertiesFile);
		if (properties.contains(file.getPath())) {
			return true;
		} else {
			return false;
		}
	}

	// Searches file paths in properties file to make sure those files exist
	// Removes any file path whose file no longer exists
	public static void refreshProperties() {
		createPropertiesFile();
		ArrayList<String> properties = (ArrayList<String>) deserializeObject(Backup.propertiesFile);
		ArrayList<Integer> indicesToRemove = new ArrayList<Integer>();

		// Get the indices of nonexistent files
		for (int i = 0; i < properties.size(); i++) {
			if (!new File(properties.get(i)).exists()) {
				indicesToRemove.add(i);
			}
		}

		// Remove nonexistent files from properties file
		int numRemoves = 0;
		for (Integer index : indicesToRemove) {
			properties.remove(index - numRemoves);
			numRemoves += 1;
		}

		serializeObject(properties, Backup.propertiesFile);
	}

	// Creates a new properties file if such a file doesn't exist at
	// predetermined path
	private static void createPropertiesFile() {
		if (!propertiesFile.exists()) {
			try {
				propertiesFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void serializeObject(ArrayList object, File file) {
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(object);
			objectOut.close();
			fileOut.close();
		} catch (IOException e) {
			// Can't find file to write to
			// Or error in writing to file
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
		} catch (IOException | ClassNotFoundException e) {
			// Returns empty array is cannot find file or file is empty
			deserialized = new ArrayList();
		}

		return deserialized;
	}

}