import java.io.File;
public class TestMain {

	public static void main(String[] args) {
		String mypath = "";
		File folder = new File(mypath);
		MyFolder mf = new MyFolder(folder);
		
		mf.printFolderInfo();
					
		//mf.printListOfFiles();
		//mf.printListOfFolders();
		
		String newfolder = "C:\\temp";
		folder = new File(newfolder);
		mf.changeFolder(folder);
		mf.printFolderInfo();
		mf.printListOfFiles();
		
		System.out.println("DONE");
		
	}

}
