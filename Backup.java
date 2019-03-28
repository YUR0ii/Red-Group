import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Backup {

	private static final File PROPERTIES_FILE = new File("Z:\\Properties.ser");
	private static JFileChooser fileChooser = new JFileChooser();

	public static ArrayList[] restoreTasks(Component parent, boolean startup) {
		String filePath = (String) deserializeObject(PROPERTIES_FILE);
		// If the properties file exists and contains a string
		if (filePath instanceof String) {
			File backup = new File(filePath);
			if (backup.exists()) {
				ArrayList<Task>[] tasks = (ArrayList<Task>[]) deserializeObject(backup);
				// Should honestly include a more rigorous check that tasks is of type
				// ArrayList<Task>[]
				if (tasks != null) {
					return tasks;
				}
			}
		}

		if (!startup) {
			JOptionPane.showMessageDialog(parent, "Backup doesnt exist", "Error", JOptionPane.WARNING_MESSAGE);
		}

		return getDefaultTasks();
	}

	public static void saveTasks(Component parent, ArrayList<Task> incompleteTasks, ArrayList<Task> completeTasks) {
		boolean success = false;

		if (backupExists()) {
			ArrayList<Task>[] tasks = new ArrayList[2];
			tasks[0] = incompleteTasks;
			tasks[1] = completeTasks;
			if (serializeObject(tasks, getBackupFile())) {
				success = true;
			}
		} else {
			int returnValue = fileChooser.showSaveDialog(parent);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				ArrayList<Task>[] tasks = new ArrayList[2];
				tasks[0] = incompleteTasks;
				tasks[1] = completeTasks;
				if (serializeObject(tasks, file)) {
					success = true;
				}

				// Update propertiesFile
				String filePath = file.getPath();
				serializeObject(filePath, PROPERTIES_FILE);
			}
		}

		if (success) {
			JOptionPane.showMessageDialog(parent, "Backup saved successfully", "Backup Saved",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(parent, "Backup could not be saved", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static boolean backupExists() {
		try {
			String filePath = (String) deserializeObject(PROPERTIES_FILE);
			File backup = new File(filePath);
			if (backup.exists()) {
				return true;
			}
			// If backup file doesn't exist
		} catch (Exception e) {
			// If properties file doesn't exist
			// Backup file cannot be accessed without the properties file
		}

		return false;
	}

	private static File getBackupFile() {
		try {
			String filePath = (String) deserializeObject(PROPERTIES_FILE);
			return new File(filePath);
		} catch (Exception e) {
			// Properties file doesn't exist or is empty
			return null;
		}
	}

	private static ArrayList<Task>[] getDefaultTasks() {
		ArrayList<Task>[] tasks = new ArrayList[2];
		tasks[0] = new ArrayList<Task>();
		tasks[1] = new ArrayList<Task>();

		return tasks;
	}

	private static boolean serializeObject(Object object, File file) {
		boolean success;

		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(object);
			objectOut.close();
			fileOut.close();

			success = true;
		} catch (IOException e) {
			// File not found or cannot write to file
			success = false;
		}

		return success;
	}

	private static Object deserializeObject(File file) {
		Object deserialized = null;

		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			deserialized = objectIn.readObject();
			objectIn.close();
			fileIn.close();
		} catch (IOException | ClassNotFoundException e) {
			// File not found or file is empty
		}

		return deserialized;
	}

}
