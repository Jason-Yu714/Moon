package fun.moon5.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Port {
    private Integer id;
    private Integer port;
    private String serverName;
    private String message;
    private String attackMethod;
}
