package com.example.Controller;

import com.example.Dto.UserDtlsReqDto;
import com.example.Dto.UserDtlsResDto;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @PostMapping("/newAdmin")
    public ResponseEntity<String> addNewUser(@RequestBody UserDtlsReqDto reqDto){
        reqDto.setRole("ROLE_ADMIN");
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

    @GetMapping("/getUserDtl/{id}")
    public ResponseEntity<UserDtlsResDto> getUserDtls(@PathVariable int id){
        UserDtlsResDto resDto = userService.getUserDetailsById(id);

        return new ResponseEntity<>(resDto, HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDtlsResDto>> getAllUsers(){
        List<UserDtlsResDto> userList = userService.getAllUsers();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/byCourseId/{id}")
    public ResponseEntity<List<String>> getUsersNameByCourseID(@PathVariable int id){
        List<String> usersNames = userService.knowUserNamesByCourseId(id);

        return  new ResponseEntity<>(usersNames, HttpStatus.OK);
    }

    @PostMapping("/addCourses")
    public ResponseEntity<Courses> addNewCourses(@RequestBody Courses courses){
        Courses courses1 = courseService.addNewCourse(courses);

        return new ResponseEntity<>(courses1, HttpStatus.CREATED);
    }

    @PatchMapping("/updateCourse/{id}/{fees}")
    public ResponseEntity<String> updateCourseDtls(@PathVariable int id,
                                                   @PathVariable double fees){
        String result = courseService.updateCourse(id, fees);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<String> removeCourse(@PathVariable int id){
        String str = courseService.removeCourse(id);

        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> logINHandler(Authentication auth){
        System.out.println(auth);

        User user = userService.getUserByUsername(auth.getName());

        return ResponseEntity.ok(user.getName() + " Logged in Successfully");
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
