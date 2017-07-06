package com.wujun.learning.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {//extends BaseController {

//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @RequestMapping("/transfer")
//    public ResultResponse transfer(Long idFrom, Long idTo, Double amount) {
//        return processSimple(new ResultResponse(), rr -> userService.transfer(idFrom, idTo, amount));
//    }
//
//    @RequestMapping("/modifyName")
//    public ResultResponse modifyName(Long id, String newName) {
//        return processSimple(new ResultResponse(), rr -> userService.modifyName(id, newName));
//    }
//
//    @RequestMapping("/{id}")
//    public ResultResponse queryById(@PathVariable(name = "id") Long id) {
//        return processSimple(new ResultResponse(), rr -> {
//            rr.addAttribute("user", userService.queryById(id));
//        });
//    }
}
