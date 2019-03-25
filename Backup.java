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

	private static final File propertiesFile = new File("Z:\\Properties.ser");

	private static JFileChooser fileChooser = new JFileChooser();

	static {
		createPropertiesFile();
	}

	public static ArrayList<ArrayList<Task>> restoreTasks(int index) {
		createPropertiesFile();
		ArrayList<String> properties = (ArrayList<String>) deserializeObject(propertiesFile, false);
		String path = properties.get(index);

		ArrayList<ArrayList<Task>> tasks = (ArrayList<ArrayList<Task>>) deserializeObject(new File(path), true);
		return tasks;
	}

	public static void saveTasks(Component parent, ArrayList<Task> incompleteTasks, ArrayList<Task> completeTasks) {
		int returnValue = fileChooser.showSaveDialog(parent);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			ArrayList<ArrayList<Task>> tasks = new ArrayList<ArrayList<Task>>();
			tasks.add(incompleteTasks);
			tasks.add(completeTasks);
			serializeObject(tasks, file);
			// Append properties only if it is unique to prevent duplicates
			if (!fileExists(file)) {
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
		ArrayList<String> filePaths = (ArrayList<String>) deserializeObject(propertiesFile, false);

		return filePaths;
	}

	private static void appendProperties(File file) {
		refreshProperties();
		ArrayList<String> properties = (ArrayList<String>) deserializeObject(propertiesFile, false);
		properties.add(file.getPath());

		serializeObject(properties, propertiesFile);
	}

	private static boolean fileExists(File file) {
		refreshProperties();
		ArrayList<String> properties = (ArrayList<String>) deserializeObject(propertiesFile, false);
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
		ArrayList<String> properties = (ArrayList<String>) deserializeObject(propertiesFile, false);
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

		serializeObject(properties, propertiesFile);
	}

	// Creates a new properties file if such a file doesn't exist at
	// predetermined path
	private static void createPropertiesFile() {
		if (!propertiesFile.exists()) {
			try {
				propertiesFile.createNewFile();
			} catch (IOException e) {
				// e.printStackTrace();
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
			// e.printStackTrace();
		}
	}

	private static ArrayList deserializeObject(File file, boolean restoringTasks) {
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
			if (restoringTasks) {
				deserialized.add(new ArrayList<Task>());
				deserialized.add(new ArrayList<Task>());
			}
		}

		return deserialized;
	}
}