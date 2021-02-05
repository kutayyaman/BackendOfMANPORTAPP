package com.kutay.MANPORT.ws;

import com.kutay.MANPORT.ws.domain.User;
import com.kutay.MANPORT.ws.dto.UserDTO;
import com.kutay.MANPORT.ws.repository.UserRepository;
import com.kutay.MANPORT.ws.service.IUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class) //suanda spring security ile alakali sadece parolayi crpyte etmek kullandigim icin diger ozellikleri exclude ettik.
public class WsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsApplication.class, args);
	}

	@Bean
//ModelMapper nesnesinden 1 tane olustur ve springin IoC containerina koy demis oluyoruz ve boylelikle dependency injection ile direkt alip kullanabilcez.
	public ModelMapper getModelMapper(){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); //ENUM'lari convert ederken sikinti vermemesi icn bu gerekliymis
		return modelMapper;
	}

	@Bean
	CommandLineRunner createInitialUsers(IUserService userService){ //Spring projesi ayaga kalktigi zaman otomatik olarak bunun run methodu çalışır ve biz bunu başlangıç verileri eklemek için kullanıcaz.
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				if(userService.findByEmail("yamankutay1@gmail.com")==null){
					UserDTO user = new UserDTO();
					user.setEmail("yamankutay1@gmail.com");
					user.setPassword("12345678");
					user.setName("kutay");
					user.setSurname("yaman");
					userService.save(user);
				}
			}
		};
	}
}
