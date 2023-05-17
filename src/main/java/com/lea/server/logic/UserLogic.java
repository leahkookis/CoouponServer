package com.lea.server.logic;

import com.lea.server.beans.SuccessfulLoginData;
import com.lea.server.beans.UserDto;
import com.lea.server.beans.UserLoginData;
import com.lea.server.constanse.Consts;
import com.lea.server.dal.IUserDal;
import com.lea.server.entity.User;
import com.lea.server.enums.ErrorType;
import com.lea.server.utils.JWTUtils;
import com.lea.server.utils.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserLogic {
  private IUserDal userDal;
  private CompanyLogic companyLogic;

  @Autowired
  public UserLogic(IUserDal userDal, CompanyLogic companyLogic) {
    this.userDal = userDal;
    this.companyLogic = companyLogic;
  }

  public long createUser(User user) throws ServerException {
    UserValidation(user);
    userDal.save(user);
    return user.getId();
  }

  public void updateUser(User user) throws ServerException {
    UserValidation(user);
    userDal.save(user);
  }

  public void removeUser(long userId) throws ServerException {
    userDal.deleteById(userId);
  }

  public UserDto getUser(long userID) throws ServerException {
      return userDal.findById(userID);
    }

    public List<UserDto> getUsersByCompanyID(long companyId, int page) throws ServerException {
      Pageable pageable = PageRequest.of(page-1, Consts.LIMITPERPAGE);
      return  userDal.getUsersByCompanyID(companyId, pageable);
    }

    void UserValidation (User user) throws ServerException {
      isEmailValid(user.getUserName());
      if (user.getPassword().length() < 6) {
        throw new ServerException(ErrorType.INVALID_PASSWORD);
      }
      if (user.getPassword().length() > 15) {
        throw new ServerException(ErrorType.INVALID_PASSWORD);
      }
      if (user.getPassword().isEmpty()) {
        throw new ServerException(ErrorType.INVALID_PASSWORD);
      }
      if (user.getCompany()!= null) {
        if (companyLogic.getCompany(user.getCompany().getId()) == null) {
          throw new ServerException(ErrorType.COMPANY_DOES_NOT_EXIST);


        }
      }

    }

    public void isEmailValid (String email) throws ServerException {
      final String regex = "^(.+)@(.+)$";
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(email);
      if (!matcher.matches()) {
        throw new ServerException(ErrorType.INVALID_EMAIL, email);
      }
    }

    public String login (UserLoginData userDetails) throws Exception {
      SuccessfulLoginData user = userDal.login(userDetails.getUserName(), userDetails.getPassword());
      if (user == null) {
        throw new Exception("Failed to login");
      }
      String token = JWTUtils.createJWT(user);
      return token;

    }
  }