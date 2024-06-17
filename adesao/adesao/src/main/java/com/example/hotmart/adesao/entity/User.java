package com.example.hotmart.adesao.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.io.Serial;
import java.util.List;
import java.util.Objects;

@Builder
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
        @UniqueConstraint(columnNames = { "document" }),  @UniqueConstraint(columnNames = { "email" })  })
public class User {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "username")
    private String username;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;

    @NotBlank
    @Column(name = "birthday")
    private String birthday;

    @NotBlank
    @Column(name = "document")
    private String document;

    @NotBlank
    @NaturalId
    @Email
    @Column(name = "email")
    private String email;

    @NotBlank
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_language",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "language_id", referencedColumnName = "id"))
    private List<ProgrammingLanguage> languages;

    public User(Long id, String name, String username, String password, String birthday, String document, String email, String phoneNumber,
                List<ProgrammingLanguage> languages) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.document = document;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.languages = languages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getName(), user.getName())
                && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword())
                && Objects.equals(getBirthday(), user.getBirthday()) && Objects.equals(getDocument(), user.getDocument())
                && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPhoneNumber(), user.getPhoneNumber())
                && Objects.equals(getLanguages(), user.getLanguages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUsername(), getPassword(), getBirthday(), getDocument(),
                getEmail(), getPhoneNumber(), getLanguages());
    }
}
