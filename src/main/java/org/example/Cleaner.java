package org.example;

import java.io.*;

public class Cleaner {
	public static void clean(String directory) {
		File filesDirectory = new File(directory);
		File newDirectory = new File(newDirectory(directory));
		for (File file : getImagesFromFolder(filesDirectory)) {
			try {
				copyFile(file.getPath(), new File(newDirectory, file.getName()).getPath());
				System.out.println("Файл успешно копирован!");
				file.delete();
				System.out.println("Старый файл удалён.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static File[] getImagesFromFolder(File folder) {
		return folder.listFiles(dir -> {
			String name = dir.getName();
			return name.toLowerCase().endsWith(".jpg")
					|| name.toLowerCase().endsWith(".png")
					|| name.toLowerCase().endsWith(".jpeg")
					|| name.toLowerCase().endsWith(".heic");
		});
	}

	public static String newDirectory(String oldDirectory) {
		File newDirectory = new File(oldDirectory + "\\" + "Изображения");
		if (!newDirectory.exists() && newDirectory.mkdir()) {
			System.out.println("Директория успешно создана.");
		} else {
			System.out.println("Директория уже существует.");
		}
		return newDirectory.getPath();
	}

	public static void copyFile(String sourcePath, String targetPath) throws IOException {
		try (FileInputStream inputStream = new FileInputStream(sourcePath);
		     FileOutputStream outputStream = new FileOutputStream(targetPath)) {
			byte[] buffer = new byte[1024];
			int bytesRead;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
		}
	}
}
