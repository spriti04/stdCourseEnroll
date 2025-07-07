package com.example.Controller;

import com.example.Dto.UserDtlsReqDto;
import com.example.Model.Courses;
import com.example.Model.User;
import com.example.Service.CourseService;
import com.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @PostMapping("/newTeacher")
    public ResponseEntity<String> addNewUser(@RequestBody UserDtlsReqDto reqDto){
        reqDto.setRole("ROLE_TEACHER");
        User user1 = userService.createUser(reqDto);
//        UserDtlsResDto dtlsResDto = userService.convertToUserDtlsResponseDto(user);
        return new ResponseEntity<>("Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/allCourses")
    public ResponseEntity<List<Courses>> getAllCourses(){
        List<Courses> courseList = courseService.allCourses();

        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @PatchMapping("/updatePhNo/{id}/{phNo}")
    public ResponseEntity<String> updateUser(@PathVariable int id,
                                             @PathVariable String phNo){
        String str = userService.updateUserDtls(id, phNo);

        return new ResponseEntity<>(str, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> removeUser(@PathVariable int id){
        String str = userService.deleteUser(id);

        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> logINHandler(Authentication auth){
        System.out.println(auth);

        User user = userService.getUserByUsername(auth.getName());

        return ResponseEntity.ok(user.getName() + " Logged in Successfully");
    }
}
