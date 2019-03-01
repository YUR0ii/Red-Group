import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Backup {

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
	
	public static void startUp() {
		ArrayList<String> newList = loadFile("Z:\\Backup Folder\\test1.ser");
		for (String s : newList) {
			System.out.println(s);
		}
	}
	
	public static void main(String[] args) {
		// Testing
		/*ArrayList<String> list = new ArrayList<String>();
		list.add("String 1");
		list.add("String 2");
		list.add("String 3");
		
		File backupFolder = new File("Z:\\Backup Folder");
		System.out.println(backupFolder.mkdir());
		System.out.println(backupFolder.getAbsolutePath());
		
		saveFile(backupFolder.getAbsolutePath() + "\\test.ser", list);
		ArrayList<String> newList = loadFile(backupFolder.getAbsolutePath() + "\\test.ser");
		for (String s : newList) {
			System.out.println(s);
		}*/
		
		/*ArrayList<String> list1 = new ArrayList<String>();
		list1.add("What");
		list1.add("the");
		list1.add("heck");
		saveFile("Z:\\Backup Folder\\test1.ser", list1);*/
		
		// startUp();
	}

}



