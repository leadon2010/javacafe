package morning;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReadWriteEx {
	public static void main(String[] args) {
		try {
			File myFile = new File("myFile.txt");
			if (myFile.createNewFile()) {
				System.out.println("file created. " + myFile.getName());
				FileWriter fileWriter = new FileWriter(myFile);
				for (int i = 0; i < 100; i++) {
					fileWriter.write(i + " Hello Java \n");
					fileWriter.flush();
				}
				fileWriter.close();
			} else {
				FileReader fileReader = new FileReader(myFile);
				int readByte;
				char[] cbuf = new char[1000];
				String str = null;
				while ((readByte = fileReader.read(cbuf)) != -1) {
					str = new String(cbuf, 0, readByte);
					System.out.println(str);
				}
//				System.out.println("file already existed.");
				fileReader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
