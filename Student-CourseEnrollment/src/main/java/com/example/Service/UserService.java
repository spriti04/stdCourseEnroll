package com.example.Service;

import com.example.Dto.UserDtlsReqDto;
import com.example.Dto.UserDtlsResDto;
import com.example.Model.User;

import java.util.List;

public interface UserService {

    public UserDtlsResDto convertToUserDtlsResponseDto(User user);

    public User createUser(UserDtlsReqDto reqDto);

    public UserDtlsResDto getUserDetailsById(int id);

    public User getUserByUsername(String username);

    public List<UserDtlsResDto> getAllUsers();

    public List<String> knowUserNamesByCourseId(int id);

    public String updateUserDtls(int id, String phNo);

    public String deleteUser(int id);
}
