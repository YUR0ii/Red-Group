import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

	public static ArrayList loadFile(String filePath) {
		ArrayList deserialized;

		try {
			FileInputStream fileIn = new FileInputStream(filePath);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			deserialized = (ArrayList) objectIn.readObject();
			objectIn.close();
			fileIn.close();

			return deserialized;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void saveFile(String filePath, ArrayList list) {
		try {
			FileOutputStream fileOut = new FileOutputStream(filePath);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(list);
			objectOut.close();
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
