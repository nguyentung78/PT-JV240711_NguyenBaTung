package ra.exam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDto {

    @NotBlank(message = "Mã sinh viên không được để trống")
    @Size(min = 1, max = 10, message = "Mã sinh viên phải nằm trong khoảng từ 1 đến 10 ký tự")
    private String studentId;
    @NotBlank(message = "Tên sinh viên không được để trống")
    @Size(max = 100, message = "Tên sinh viên không được vượt quá 100 ký tự")
    private String studentName;
    @NotBlank(message = "Số điện thoại không được để trống")
    @Size(max = 11, message = "Số điện thoại không được vượt quá 11 ký tự")
    private String phoneNumber;
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email phải đúng định dạng")
    private String email;
    @NotBlank(message = "Địa chỉ không được để trống")
    @Size(max = 150, message = "Địa chỉ không được vượt quá 150 ký tự")
    private String address;
    @NotNull(message = "Giới tính không được để trống")
    private Boolean sex;
    @NotNull(message = "Lớp không được để trống")
    private Integer classId;
    private MultipartFile image;
    @Min(value = 0, message = "Trạng thái không hợp lệ")
    @Max(value = 4, message = "Trạng thái không hợp lệ")
    private int status;
}
