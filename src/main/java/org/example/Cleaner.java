package org.example;

import java.io.*;

public class Cleaner {
	public static void clean() {
		String fileExtension = "";
		String filesDir = "C:\\Users\\mrart\\Рабочий стол";
		File file = new File(filesDir);
		File newFileDir = new File(filesDir + "\\New Folder for " + fileExtension);

		if (!newFileDir.exists() && newFileDir.mkdir()) {
			System.out.println("Директория успешно создана.");
		} else {
			throw new RuntimeException("По какой то причине директория не была создана.");
		}

		try(FileInputStream reader = new FileInputStream(file);
		    FileOutputStream writer = new FileOutputStream(newFileDir)) {

			byte[] buffer = new byte[65536];

			if (getJpgFilesFromFolder(file).length != 0) {
				while (reader.available() > 0 && file.getName().endsWith("txt")) {
					int realSize = reader.read(buffer);
					writer.write(buffer, 0, realSize);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("Файл не найден.");
		}

	}

	public static File[] getJpgFilesFromFolder(File folder) {
		File[] jpgFiles = folder.listFiles(new FileFilter() {
			@Override
			public boolean accept(File dir) {
				String name = dir.getName();
				return name.toLowerCase().endsWith(".jpg");
			}
		});
		return jpgFiles;
	}
}
