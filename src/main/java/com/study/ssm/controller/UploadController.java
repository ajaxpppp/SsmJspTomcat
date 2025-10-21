package com.study.ssm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 文件上传控制器
 * 目标：将文件保存到 Web 应用目录下的 /upload 目录中
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    // (1) 单文件上传
    @PostMapping("/one")
    public String uploadOne(@RequestParam("file") MultipartFile file,
                            HttpServletRequest request,
                            Model model) {
        if (file == null || file.isEmpty()) {
            model.addAttribute("message", "请选择要上传的文件");
            return "fail";
        }
        try {
            Path saved = saveFileToUploadDir(request, file, null);
            model.addAttribute("message", "上传成功");
            model.addAttribute("paths", Collections.singletonList(saved.toString()));
            return "success";
        } catch (Exception e) {
            logger.error("单文件上传失败", e);
            model.addAttribute("message", "上传失败: " + e.getMessage());
            return "fail";
        }
    }

    // (2) 单文件上传（用户名作为文件夹名）
    @PostMapping("/oneByUser")
    public String uploadOneByUser(@RequestParam("username") String username,
                                  @RequestParam("file") MultipartFile file,
                                  HttpServletRequest request,
                                  Model model) {
        if (!StringUtils.hasText(username)) {
            model.addAttribute("message", "用户名不能为空");
            return "fail";
        }
        if (file == null || file.isEmpty()) {
            model.addAttribute("message", "请选择要上传的文件");
            return "fail";
        }
        try {
            // 简单清理用户名，避免非法路径字符
            String safeUser = username.replaceAll("[^a-zA-Z0-9_-]", "_");
            Path saved = saveFileToUploadDir(request, file, safeUser);
            model.addAttribute("message", "上传成功");
            model.addAttribute("paths", Collections.singletonList(saved.toString()));
            return "success";
        } catch (Exception e) {
            logger.error("按用户名目录上传失败, username={}", username, e);
            model.addAttribute("message", "上传失败: " + e.getMessage());
            return "fail";
        }
    }

    // (3) 多文件上传
    @PostMapping("/multi")
    public String uploadMulti(@RequestParam("files") List<MultipartFile> files,
                              HttpServletRequest request,
                              Model model) {
        if (files == null || files.isEmpty()) {
            model.addAttribute("message", "请至少选择一个文件");
            return "fail";
        }
        List<String> savedPaths = new ArrayList<>();
        try {
            for (MultipartFile f : files) {
                if (f != null && !f.isEmpty()) {
                    Path saved = saveFileToUploadDir(request, f, null);
                    savedPaths.add(saved.toString());
                }
            }
            if (savedPaths.isEmpty()) {
                model.addAttribute("message", "没有有效的文件被上传");
                return "fail";
            }
            model.addAttribute("message", "上传成功");
            model.addAttribute("paths", savedPaths);
            return "success";
        } catch (Exception e) {
            logger.error("多文件上传失败", e);
            model.addAttribute("message", "上传失败: " + e.getMessage());
            return "fail";
        }
    }

    // 保存文件到 /upload 或 /upload/{subDir}
    private Path saveFileToUploadDir(HttpServletRequest request, MultipartFile file, String subDir) throws IOException {
        String root = request.getServletContext().getRealPath("/upload");
        Path uploadRoot = Paths.get(root);
        if (subDir != null) {
            uploadRoot = uploadRoot.resolve(subDir);
        }
        Files.createDirectories(uploadRoot);

        String original = file.getOriginalFilename();
        String filename = (original == null ? "file" : original.trim());
        if (filename.isEmpty()) {
            filename = "file";
        }
        // 为避免同名覆盖，加上毫秒时间戳前缀
        String savedName = System.currentTimeMillis() + "_" + filename;
        Path dest = uploadRoot.resolve(savedName);
        file.transferTo(dest.toFile());
        return dest;
    }
}
