package com.vladis1350.admin.controllers;

import com.vladis1350.auth.bean.UserRoles;
import com.vladis1350.auth.services.UserService;
import com.vladis1350.constants.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserManagementController {

    @Autowired
    private UserService userService;

    @PostMapping("/admin/giveUserRole")
    public ModelAndView giveUserRole(@RequestParam(name = "userId", required = false) Long userId,
                                     @RequestParam(name = "userRole", required = false) String userRole) {
        ModelAndView mod = new ModelAndView();
        userService.addRoleToUser(UserRoles.valueOf(userRole), userId);
        mod.addObject("userList", userService.findAllUser());
        mod.addObject("userRolesList", UserRoles.values());
        mod.setViewName(Pages.ADMIN_USERS);
        return mod;
    }
}
