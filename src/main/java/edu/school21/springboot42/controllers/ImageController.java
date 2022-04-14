package edu.school21.springboot42.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@Controller
public class ImageController {
    @GetMapping("/images/{type}/{imageName}")
    @ResponseStatus(value = HttpStatus.OK)
    public void getImage(
            HttpServletResponse response,
            @PathVariable(value="type") String type,
            @PathVariable(value="imageName") String imageName
    ) throws IOException {

        int ch;
        FileInputStream fileInput;
        response.setContentType("image/jpeg");

        try {
            fileInput = new FileInputStream("./src/main/resources/" + type + "/" + imageName);
        } catch (FileNotFoundException e) {
            fileInput = new FileInputStream("./src/main/resources/" + type + "/" + "default.jpeg");
        }
        BufferedInputStream bufInput = new BufferedInputStream(fileInput);
        BufferedOutputStream bufOutput = new BufferedOutputStream(response.getOutputStream());

        while ((ch = bufInput.read()) != -1)
            bufOutput.write(ch);

        bufInput.close();
        fileInput.close();
        bufOutput.close();
    }

    public static String saveImage(String path, MultipartFile file, String type) throws IOException {

        if (path.endsWith("avatars")) {
            if (!(type.equals("image/jpeg") || type.equals("image/png") || type.equals("image/gif")))
                return null;
        } else if (path.endsWith("posters")) {
            if (!(type.equals("image/jpeg") || type.equals("image/png")))
                return null;
        }

        String uniqueName = UUID.randomUUID() + "." + type.substring(type.lastIndexOf("/") + 1);

        byte[] bytes = file.getBytes();
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(path + File.separator + uniqueName)
        );

        stream.write(bytes);
        stream.flush();
        stream.close();
        return uniqueName;
    }
}
