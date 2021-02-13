package hr.xmjosic.uglyglah.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

    private Long id;
    private String subuglyglahName;
    private String postName;
    private String url;
    private String description;
}
