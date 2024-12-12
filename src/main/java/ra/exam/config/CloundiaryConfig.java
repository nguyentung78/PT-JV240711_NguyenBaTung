package ra.exam.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloundiaryConfig {
    private static final String CLOUND_NAME = "dw4t9wg6k";
    private static final String API_KEY = "187735852455942";
    private static final String API_SECRET = "QpBRw1QtrMLmExqc99h2U5CbUEE";

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUND_NAME,
                "api_key", API_KEY,
                "api_secret", API_SECRET
        ));
    }
}
