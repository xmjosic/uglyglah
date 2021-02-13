package hr.xmjosic.uglyglah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class UglyglahApplication {

	public static void main(String[] args) {
		SpringApplication.run(UglyglahApplication.class, args);
	}

}
