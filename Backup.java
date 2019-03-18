 import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Backup {

	public static String createFolder(String folderPath) {
		// Parameter should be "Z:\\To Do List"
		File toDoListFolder = new File(folderPath);
		toDoListFolder.mkdir();

		return toDoListFolder.getAbsolutePath();
	}

	public static ArrayList<Task> loadFile(String filePath) throws ClassNotFoundException, IOException {
		ArrayList<Task> deserialized;

		FileInputStream fileIn = new FileInputStream(filePath);
		ObjectInputStream objectIn = new ObjectInputStream(fileIn);
		deserialized = (ArrayList<Task>) objectIn.readObject();
		objectIn.close();
		fileIn.close();

		return deserialized;
	}

	public static void saveFile(String filePath, ArrayList<Task> list) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(filePath);
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		objectOut.writeObject(list);
		objectOut.close();
		fileOut.close();
	}

}