package com.lea.server.controllers;

import com.lea.server.beans.UserDto;
import com.lea.server.beans.UserLoginData;
import com.lea.server.entity.User;
import com.lea.server.logic.UserLogic;
import com.lea.server.utils.ServerException;
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
  public void updateUser(@RequestBody User user) throws ServerException {
    userLogic.updateUser(user);
  }

  @DeleteMapping("/{userId}")
  public void removeUser(@PathVariable("userId") long userId) throws ServerException {
    userLogic.removeUser(userId);
  }

  @GetMapping("/{userId}")
  public UserDto getUserByUserID(@PathVariable("userId") int userId) throws ServerException {
    return userLogic.getUser(userId);
  }

  @GetMapping({"/byCompany"})
  public List<UserDto> getUsersByCompanyID(@RequestParam("companyId") int companyId, @RequestParam("page") int page)throws ServerException {
    return userLogic.getUsersByCompanyID(companyId, page);
  }



}
