import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MyFolder{
	/*
	 * Class for getting folder information
	 * Stores:
	 * - List of all files in folder and subfolders
	 * - List od subfolders
	 * - Size in bytes of folder
	 * - Number of files
	 * - Number of subfolders
	 * Methods:
	 * - Inititalizes, can be called to reset attributes if folder is changed
	 * - get for each attribute
	 * - get
	 * - set folder path (used to change on the fly folder)
	 * - printInfo: prints size, count of files and count of subfolders
	 * - printFileList: prints list of files in canonical path format
	 * - printFolderList: prints list of subfolders in canonical format		 
	 */
		private String folderpath;
		private long size;
		private long filecount;
		private long dircount;
		private ArrayList<File> file_list;
		private ArrayList<File> dir_list;
		private ArrayList<String> file_list_string;
		private ArrayList<String> dir_list_string;

		MyFolder(File dir) {
			this.folderpath = dir.getAbsolutePath();
			this.Initialize();
			
		}
		public void Initialize() {
			this.file_list = new ArrayList<File>();
			this.dir_list = new ArrayList<File>();
			this.file_list_string = new ArrayList<String>();
			this.dir_list_string = new ArrayList<String>();
			this.createFileFolderLists(this.folderpath);
			this.filecount = this.getFileList().size();
			this.dircount = this.getFolderList().size();
			this.size = this.getFileSize();			
		}
		
		public void createFileFolderLists(String path) {
			File dir = new File(path);			
			if (dir.isDirectory()) {
				for (File file: dir.listFiles()) {
					if (file.isFile()) {
						this.file_list.add(file);
					
					}
					else
						createFileFolderLists(file.getAbsolutePath());
				}
				// we do not include current folder
				if (path != this.folderpath) {
					this.dir_list.add(dir);
				}
			}
			else if (dir.isFile()) {
				this.file_list.add(dir);
			}
		}
		
		public void createFileFolderListsString(String path) {
			File dir = new File(path);			
			if (dir.isDirectory()) {
				for (File file: dir.listFiles()) {
					if (file.isFile()) {
						try {
							this.file_list_string.add(file.getCanonicalPath());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					}
					else
						createFileFolderLists(file.getAbsolutePath());
				}
				// we do not include current folder
				if (path != this.folderpath) {
					try {
						this.dir_list_string.add(dir.getCanonicalPath());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else if (dir.isFile()) {
				try {
					this.file_list_string.add(dir.getCanonicalPath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public long getFileCount() {
			return this.filecount;
		}
		public long getSize() {
			return this.size;
		}
		public long getFolderCount() {
			return this.dircount;
		}
		public String getFolderPath() {
			return this.folderpath;
		}
		
		public ArrayList<File> getFileList() {
			return this.file_list;
		}
		public ArrayList<File> getFolderList() {
			return this.dir_list;
		}
		public ArrayList<String> getFileListString() {
			return this.file_list_string;
		}
		public ArrayList<String> getFolderListString() {
			return this.dir_list_string;
		}
		public long getFileSize() {
			long size = 0;
			for (int i=0; i<this.getFileList().size(); i++) {
				size += this.getFileList().get(i).length();
			}
			return size;
		}
		
		public void changeFolder(File dir) {
			this.folderpath = dir.getAbsolutePath();
			this.Initialize();
		}
		public void printListOfFiles() {
			System.out.println("\nFiles list in folder " + this.getFolderPath());
			for (int i = 0;i< this.getFileList().size(); i++) {
				try {
					System.out.println(String.format("%s: %s",i+1 ,this.getFileList().get(i).getCanonicalFile()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		public void printListOfFolders() {
			System.out.println("\nFolder list in folder " + this.getFolderPath());
			for (int i = 0;i< this.getFolderList().size(); i++) {
				try {
					System.out.println(String.format("%s: %s",i+1 ,this.getFolderList().get(i).getCanonicalFile()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		public boolean doesFileFolderExists() {
			return new File(this.folderpath).exists();
			
		}
		
		public void printFolderInfo() {
			String additional_text = "";
			String additional_size = "";
			if (this.doesFileFolderExists()== false) {
				additional_text = " - Folder does NOT exists!";
			}
			if (this.getSize() > 1000) {
				additional_size = String.format("(%s KB)", (float)this.getSize()/1024);
			}
			if (this.getSize() > 1000000) {
				additional_size = String.format("(%s MB)", (float)this.getSize()/1024/1024);
			}
			if (this.getSize() > 1000000000) {
				additional_size = String.format("(%s GB)", (float)this.getSize()/1024/1024/1024);
			}
			System.out.println(String.format("\n#################################################\nCurrent folder: %s %s", this.getFolderPath(),additional_text));
			System.out.println(String.format("Total number of files: %s", this.getFileCount()));
			System.out.println(String.format("Total number of folders: %s", this.getFolderCount()));
			System.out.println(String.format("Folder Size: %s bytes %s", this.getSize(), additional_size));
		}
		
}

