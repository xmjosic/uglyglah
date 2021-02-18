package hr.xmjosic.uglyglah;

import hr.xmjosic.uglyglah.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class UglyglahApplication {

	public static void main(String[] args) {
		SpringApplication.run(UglyglahApplication.class, args);
	}

}
