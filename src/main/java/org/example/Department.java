package org.example;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Generated;
import lombok.ToString;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@ToString
@Data
public class Department {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }
}
