package ra.exam.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "classes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private int classId;
    @NotBlank(message = "Tên lớp không được để trống")
    @Size(max = 100, message = "Tên lớp không được vượt quá 100 kí tự")
    @Column(name = "class_name", columnDefinition = "varchar(100)", nullable = false, unique = true)
    private String className;
    @NotBlank(message = "Chuyên ngành không được để trống")
    @Size(max =150, message = "Chuyên ngành không được vượt quá 150 kí tự")
    @Column(name = "majors", columnDefinition = "varchar(150)", nullable = false)
    private String majors;
}
