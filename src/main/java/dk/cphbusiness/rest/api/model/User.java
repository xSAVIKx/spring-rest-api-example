package dk.cphbusiness.rest.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @Column(name = "user_name")
    private String userName;
    @Type(type = "encryptedString")
    @Column(name = "password")
    private String passWord;
    @Column(name = "role")
    private String role;
}
