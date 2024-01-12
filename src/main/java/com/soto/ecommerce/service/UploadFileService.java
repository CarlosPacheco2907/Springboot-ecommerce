package com.soto.ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFileService {

    // Folder where images will be stored
    private String folder = "images//";

    /**
     * Method to save an image to the file system.
     *
     * @param file Image file to be saved
     * @return Name of the saved image file or "default.jpg" if the file is empty.
     * @throws IOException If an error occurs while writing the file to the file system.
     */
    public String saveImage(MultipartFile file) throws IOException {
        // Check if the file is not empty
        if (!file.isEmpty()) {
            // Get the bytes of the file
            byte[] bytes = file.getBytes();

            // Get the path where the file will be saved (in the 'images' folder with the original file name)
            Path path = Paths.get(folder + file.getOriginalFilename());

            // Write the bytes to the file system
            Files.write(path, bytes);

            // Return the original name of the saved file
            return file.getOriginalFilename();
        }

        // Return "default.jpg" if the file is empty
        return "default.jpg";
    }

    /**
     * Method to delete an image from the file system.
     *
     * @param nameImage Name of the image file to be deleted.
     */
    public void deleteImage(String nameImage) {
        // Path of the folder where images are located
        String path = "images//";

        // Create a File object with the full path of the file to be deleted
        File file = new File(path + nameImage);

        // Delete the file if it exists
        if (file.exists()) {
            file.delete();
        }
    }
}
