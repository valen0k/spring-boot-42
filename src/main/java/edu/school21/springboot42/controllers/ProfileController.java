package edu.school21.springboot42.controllers;

import edu.school21.springboot42.models.LogInfo;
import edu.school21.springboot42.models.User;
import edu.school21.springboot42.services.LogInfoService;
import edu.school21.springboot42.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private LogInfoService logInfoService;

    @GetMapping("/profile")
    public String getProfile(Model model) {
        User user = usersService.getCurrentUser();

        model.addAttribute("avatar", usersService.getPicName(user.getEmail()));
        model.addAttribute("fileInfo", getFormattingUploadedFiles());
        model.addAttribute("logInfo", getFormattingLogInfo(user));
        model.addAttribute("user", user);

        return "profile";
    }

    @PostMapping("/upload-avatar")
    public String postAvatarUpload(@RequestParam("file") MultipartFile file) throws IOException {

        String path = "./src/main/resources/avatars";
        String avatarName = ImageController.saveImage(path, file, file.getContentType());

        if (avatarName != null) {
            usersService.setPicName(usersService.getCurrentUser().getEmail(), avatarName);
        }

        return "redirect:/profile";
    }

    public List<List<String>> getFormattingLogInfo(User user) {
        List<List<String>> formattingLogInfo = new ArrayList<>();
        List<LogInfo> logInfo = logInfoService.findLogInfoByUser(user);

        Collections.reverse(logInfo);

        for (LogInfo s : logInfo) {
            DateFormat subDate = new SimpleDateFormat("LLLL dd, yyyy");
            DateFormat subTime = new SimpleDateFormat("HH:mm");
            List<String> tmp = new ArrayList<>();
            tmp.add(subDate.format(s.getSignTime()));
            tmp.add(subTime.format(s.getSignTime()));
            tmp.add(s.getIp());
            formattingLogInfo.add(tmp);
        }

        return (formattingLogInfo);
    }

    public List<List<String>> getFormattingUploadedFiles() {
        List<List<String>> formattingUploadedFilesList = new ArrayList<>();
        File[] fileList = new File("./src/main/resources/avatars").listFiles();

        assert fileList != null;
        for (File f : fileList) {
            List<String> tmp = new ArrayList<>();
            tmp.add(f.getName());
            tmp.add(formatFileSize(f.length()));
            try {
                tmp.add(Files.probeContentType(f.toPath()));
            } catch (IOException e) {
                tmp.add("");
            }
            formattingUploadedFilesList.add(tmp);
        }
        return formattingUploadedFilesList;
    }

    public String formatFileSize(long size) {
        double k = size / 1024.0;
        double m = ((size / 1000.0) / 1024.0);

        DecimalFormat dec = new DecimalFormat("0");

        return (m > 1) ? dec.format(m).concat(" MB") :
                (k > 1) ? dec.format(k).concat(" KB")
                        : dec.format(size).concat(" Bytes");
    }

}
