package com.selekode.topaz.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.selekode.topaz.model.Settings;
import com.selekode.topaz.repository.SettingsRepository;
import com.selekode.topaz.service.EntryEncryptionService;
import com.selekode.topaz.service.JournalService;
import com.selekode.topaz.utils.PasswordUtils;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private SettingsRepository settingsRepo;
    
    private final EntryEncryptionService entryEncryptionService;

	public LoginController(EntryEncryptionService entryEncryptionService) {
		this.entryEncryptionService = entryEncryptionService;
	}

    @GetMapping("/login")
    public String loginPage() {
        // If no password set yet → redirect to setup page
    	System.out.println("Loading Login");
        return settingsRepo.findById("passwordHash").isEmpty() 
                ? "redirect:/setupPassword" 
                : "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String password, HttpSession session) {
        return settingsRepo.findById("passwordHash")
            .map(setting -> {
                if (PasswordUtils.checkPassword(password, setting.getValue())) {
                    session.setAttribute("authenticated", true);
                    return "redirect:/dashboard/load";
                }
                return "redirect:/login?error";
            })
            .orElse("redirect:/setupPassword");
    }

    @GetMapping("/setupPassword")
    public String setupPasswordPage() {
        return "setupPassword";
    }

    @PostMapping("/setupPassword")
    public String setupPassword(@RequestParam String password, 
                                @RequestParam String confirm) throws NoSuchAlgorithmException {
        if (!password.equals(confirm)) {
            return "redirect:/setupPassword?error";
        }
        String hash = PasswordUtils.hashPassword(password);
        settingsRepo.save(new Settings("passwordHash", hash));

        //Generate entries Encryption Key
        entryEncryptionService.generateKey();

        return "redirect:/login";
    }

    @GetMapping("/changePassword")
    public String changePasswordPage() {
        return "changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam String current,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirm) {
        return settingsRepo.findById("passwordHash")
            .map(setting -> {
                if (PasswordUtils.checkPassword(current, setting.getValue())) {
                    if (newPassword.equals(confirm)) {
                        setting.setValue(PasswordUtils.hashPassword(newPassword));
                        settingsRepo.save(setting);
                        return "redirect:/login?changed";
                    }
                    return "redirect:/changePassword?mismatch";
                }
                return "redirect:/changePassword?error";
            })
            .orElse("redirect:/setupPassword");
    }
}