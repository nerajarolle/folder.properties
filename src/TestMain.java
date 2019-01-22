import java.io.File;

public class TestMain {

	public static void main(String[] args) {
		String mypath = "";
		float exe_dur = 0;
		File folder = new File(mypath);
		MyFolder mf = new MyFolder(folder);
		long start_time = System.currentTimeMillis();
		long end_time;
		String time_format = "milliseconds";
		mf.printFolderInfo();		
		mf.printListOfFiles();
		mf.printListOfFolders();
		String newfolder = "C:\\temp";
		folder = new File(newfolder);
		mf.changeFolder(folder);
		mf.printFolderInfo();
		mf.printListOfFiles();
		end_time = System.currentTimeMillis();
		exe_dur = (end_time - start_time);
		if (exe_dur > 1000) {
			time_format = "seconds";
			exe_dur = (float)(exe_dur/1000);
		}
		System.out.println(String.format("DONE...Execution time: %s %s", exe_dur, time_format));
	}

}
