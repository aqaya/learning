package com.wujun.learning.api;

import com.wujun.learning.commom.utils.BaseController;
import com.wujun.learning.commom.utils.ResultResponse;
import com.wujun.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/{idFrom}/transfer/{idTo}")
    public ResultResponse transfer(@PathVariable Long idFrom, @PathVariable Long idTo, Double amount) {
        return processSimple(new ResultResponse(), rr -> userService.transfer(idFrom, idTo, amount));
    }

    @RequestMapping("/modifyName")
    public ResultResponse modifyName(Long id, String newName) {
        return processSimple(new ResultResponse(), rr -> userService.modifyName(id, newName));
    }

    @RequestMapping("/{id}")
    public ResultResponse queryById(@PathVariable(name = "id") Long id, HttpServletResponse response) {
        //acw_tc=598580fd|a9b58f34f2dd805a825c190af3e2d00f; Path=/; Domain=.haomaiche.com; HttpOnly
        Cookie cookie = new Cookie("acw_sc","598580fd|a9b58f34f2dd805a825c190af3e2d00f");
        cookie.setPath("/");
        response.addCookie(cookie);
        return processSimple(new ResultResponse(), rr -> rr.addAttribute("user", userService.queryById(id)));
    }

    @RequestMapping("/big-integer")
    public ResultResponse bigInteger(@RequestParam(defaultValue = "1") BigInteger bigInteger) {
        return processSimple(new ResultResponse(), rr -> rr.addAttribute("bigInteger", bigInteger));
    }

}
