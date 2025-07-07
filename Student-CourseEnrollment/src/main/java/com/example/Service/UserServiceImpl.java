package com.example.Service;

import com.example.Dto.UserDtlsReqDto;
import com.example.Dto.UserDtlsResDto;
import com.example.Model.Courses;
import com.example.Model.User;
import com.example.Repository.CourseRepository;
import com.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDtlsResDto convertToUserDtlsResponseDto(User user) {
        UserDtlsResDto resDto = new UserDtlsResDto(
                user.getId(),
                user.getName(),
                user.getPhNo(),
                user.getEmail(),
                user.getRole(),
                user.getAddress(),
                user.getCourses() != null ? user.getCourses().getTitle() : null
        );

        return resDto;
    }

    @Override
    public User createUser(UserDtlsReqDto reqDto) {
        Courses courses = courseRepository.findById(reqDto.getCourseId()).orElseThrow(() -> new RuntimeException("Course not found"));

        User user = new User();
        user.setName(reqDto.getName());
        user.setPhNo(reqDto.getPhNo());
        user.setEmail(reqDto.getEmail());
        user.setRole(reqDto.getRole());
        user.setPassword(passwordEncoder.encode(reqDto.getPassword()));
        user.setAddress(reqDto.getAddress());

        if(!"ROLE_ADMIN".equalsIgnoreCase(reqDto.getRole())){
            user.setCourses(courses);
        }else{
            user.setCourses(null);
        }

        return userRepository.save(user);
    }

    @Override
    public UserDtlsResDto getUserDetailsById(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        UserDtlsResDto resDto = convertToUserDtlsResponseDto(user);

        return resDto;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user;
    }

    @Override
    public List<UserDtlsResDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDtlsResDto> resDtoList = new ArrayList<>();

        for (User user : userList){
            UserDtlsResDto resDto = convertToUserDtlsResponseDto(user);
            resDtoList.add(resDto);
        }

        return resDtoList;
    }

    @Override
    public List<String> knowUserNamesByCourseId(int id){
        List<User> user = userRepository.findByCoursesId(id);
        List<String> names = new ArrayList<>();

        for(User user1 : user){
            names.add(user1.getName());
        }

        return names;

//        List<String> userNames = userRepository.findByCoursesId(id)
//                .stream().map(User::getName)
//                .collect(Collectors.toList());
//
//        return userNames;

    }

    @Override
    public String updateUserDtls(int id, String phNo) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User doesn't exists with this id"));

        user.setPhNo(phNo);

        userRepository.save(user);
        return "USer phNo updated successfully";
    }

    @Override
    public String deleteUser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);

        return "User deleted successfully";
    }
}
