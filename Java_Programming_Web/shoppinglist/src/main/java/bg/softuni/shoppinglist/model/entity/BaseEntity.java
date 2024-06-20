package bg.softuni.shoppinglist.model.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    public BaseEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
