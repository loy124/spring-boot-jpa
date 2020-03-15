package com.example.study.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyAdminPage1ApplicationTests;
import com.example.study.model.User;


public class UserRepositoryTest extends StudyAdminPage1ApplicationTests {
	

    //UserRepository userRepository = new UserRepository();
    // DI = Dependency Injection 의존성주입, 직접 객체를 만들지 않더라도 스프링이 관리
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        // String sql = insert into user(%s, %s, %d) value(account, email, age);
        User user = new User();
//        user.setId(); auto increment
        String account ="Testuser06";
        String email = "TestUser03@gmail.com";
        String phoneNumber = "010-1111-1111";
        LocalDateTime createdAt = LocalDateTime.now();
        String setCreatedBy = "TestUser04";
        user.setAccount(account);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(setCreatedBy);

        //save 메소드: user를 받아서 리턴
       User newUser = userRepository.save(user);
       System.out.println("newUser" + newUser);

    }
    
    @Test
    public void read(){
    	//data table 가져오기
    	// id = 2 인데 Long type이기 때문에
    	// 옵셔널은 있을수도 있고 없을수도있다 (말그대로 옵션)
    	Optional<User> user = userRepository.findById(1L);
    	
    	//user가 존재할때만 sysout 출력 
    	user.ifPresent(selectUser -> {
    		System.out.println("user : " + selectUser.toString());
    		
    	});
    	

    }
// 컨트롤러의 경우     
//    @Test
//    public User read(@RequestParam Long id){
//    	//data table 가져오기
//    	// id = 2 인데 Long type이기 때문에
//    	// 옵셔널은 있을수도 있고 없을수도있다 (말그대로 옵션)
//    	Optional<User> user = userRepository.findById(id);
//    	
//    	//user가 존재할때만 sysout 출력 
//    	user.ifPresent(selectUSer -> {
//    		System.out.println("user:" + selectUSer.toString());
//    	});
//    	
//		return user.get();
//    }
    @Test
    public void update(){
    	Optional<User> user = userRepository.findById(2L);
    	
    	user.ifPresent(selectUSer -> {
    		selectUSer.setAccount("PPPP");
    		selectUSer.setUpdatedAt(LocalDateTime.now());
    		selectUSer.setUpdatedBy("update method()");
    		//ID값이 이미 잇을경우 save를 하게 되면 해당 id가 있는지 확인하고 존재하면
    		//해당값에 대해 save한 정보 대로 변경한다 
    		userRepository.save(selectUSer);
    	});
    }
    
    
    @Test
    @Transactional //@Transactional을 넣으면 모든 내용을 수행하되 rollback 한다 테스트에 적합
    public void delete() {
    	Optional<User> user = userRepository.findById(1L);
    	
//    	Assert.assertTrue(user.isPresent());
    	
    	//junit test 반드시 user는 존재해야한다
    	assertTrue(user.isPresent());
    	
    	user.ifPresent(selectUser -> {
    		userRepository.delete(selectUser);
    	});
    	
    	Optional<User> deleteUser = userRepository.findById(1L);
    	
    	//반드시 false여야 한다
    	assertFalse(deleteUser.isPresent());
//    	
//    	if(deleteUser.isPresent()) {
//    		System.out.println("데이터가 존재 : " + deleteUser.get());
//    	}else {
//    		System.out.println("데이터가 없습니다");
//    	}
    }
    
    
//    @DeleteMapping("/api/user")
//    public void delete(@RequestParam Long id){
//
//    }
}