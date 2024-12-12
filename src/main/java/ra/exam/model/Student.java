package ra.exam.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int studentId;
    @NotBlank(message = "Tên sinh viên không được để trống")
    @Size(max = 100, message = "Tên sinh viên không vượt quá 100 kí tự")
    @Column(name = "student_name", columnDefinition = "varchar(100)", nullable = false, unique = true)
    private String studentName;
    @NotBlank(message = "Số điện thoại không được để trống")
    @Size(max = 11, message = "Số điện thoại không được vượt quá 11 ký tự")
    @Column(name = "phone_number", columnDefinition = "varchar(11)", nullable = false, unique = true)
    private String phoneNumber;
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email phải đúng định dạng")
    @Column(name = "email", columnDefinition = "varchar(100)", nullable = false, unique = true)
    private String email;
    @NotBlank(message = "Địa chỉ không được để trống")
    @Size(max = 150, message = "Địa chỉ không được vượt quá 150 ký tự")
    @Column(name = "address", columnDefinition = "varchar(150)", nullable = false, unique = true)
    private String address;
    @Column(name = "sex", columnDefinition = "bit(1)", nullable = false)
    private boolean sex;
    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "class_id", nullable = false)
    @NotNull(message = "Không được để trống lớp học")
    private Clazz studentClass;
    @Column(name = "image_url", columnDefinition = "varchar(255)")
    private String imageUrl;
    @Column(name = "status", columnDefinition = "tinyint", nullable = false)
    @Min(value = 0, message = "trạng thái không hợp lệ")
    @Max(value = 4, message = "trạng thái không hợp lệ")
    private int status;
}
