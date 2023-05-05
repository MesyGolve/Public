package experiments;

import java.io.*;

public class Text {

	public static void main(String[] args) {
		
		File file1 = new File("D:\\ungr.txt");
		File file2 = new File("D:\\grst.txt");
		
		if(file1.exists() && file2.exists()) {
			
		}
		else if(file1.exists() && file2.exists() == false){
			try{
				file2.createNewFile();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else if(file1.exists() == false && file2.exists()) {
			try{
				file1.createNewFile();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				file1.createNewFile();
				file2.createNewFile();
			}
			catch(IOException e) {
				e.printStackTrace();
			}		
		}

		new JInformation().setVisible(true);
		
	}

}
