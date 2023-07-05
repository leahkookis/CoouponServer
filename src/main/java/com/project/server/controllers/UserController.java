package com.project.server.controllers;

import com.project.server.beans.UserDto;
import com.project.server.beans.UserLoginData;
import com.project.server.logic.UserLogic;
import com.project.server.utils.JWTUtils;
import com.project.server.utils.ServerException;
import com.project.server.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {

  private UserLogic userLogic;

  @Autowired
  public UserController(UserLogic userLogic) {
    this.userLogic = userLogic;
  }

  @PostMapping
  public long createUser(@RequestBody User user) throws ServerException {
    return userLogic.createUser(user);
  }

  @PostMapping("/login")
  public String login(@RequestBody UserLoginData userLoginData) throws Exception {
    return userLogic.login(userLoginData);
  }

  @PutMapping
  public void updateUser(@RequestHeader String authorization, @RequestBody User user) throws Exception {
    JWTUtils.validateToken(authorization);
    userLogic.updateUser(user);
  }

  @DeleteMapping("/{userId}")
  public void removeUser(@RequestHeader String authorization, @PathVariable("userId") long userId) throws Exception {
    JWTUtils.validateToken(authorization);
    userLogic.removeUser(userId);
  }
  @GetMapping
  public List<UserDto> getAllUsers(@RequestParam("page") int page) throws ServerException {
    return userLogic.getAllUser(page);
  }
  @GetMapping("/{userId}")
  public UserDto getUserByUserID(@RequestHeader String authorization, @PathVariable("userId") int userId) throws Exception {
    JWTUtils.validateToken(authorization);
    return userLogic.getUser(userId);
  }

  @GetMapping("/bycompany")
  public List<UserDto> getUsersByCompanyID(@RequestHeader String authorization, @RequestParam("companyId") int companyId, @RequestParam("page") int page) throws Exception {
    JWTUtils.validateToken(authorization);
    return userLogic.getUsersByCompanyID(companyId, page);
  }



}
